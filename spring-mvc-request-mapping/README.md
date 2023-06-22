Spring의 @RequestMapping

Spring MVC의 annotation 중 하나인 @RequestMapping과 그 외의 관련된 annotation들을 살펴보려고 합니다.

# @RequestMapping
***
## value, method
```java
@RequestMapping(value = "/ex/foos", method = RequestMethod.GET)
@ResponseBody
public String getFoosBySimplePath() {
    return "Get some Foos";
}
```
이 매핑을 테스트하려면 아래 command를 실행해야 합니다.
>curl -i http://localhost:8080/ex/foos

위의 예제에서 @RequestMapping은 value와 method를 가지고 있습니다.
value는 요청을 처리할 URI를 나타내고, method는 요청을 처리할 HTTP method를 나타냅니다.

method 속성은 default 값이 존재하지 않으며, 때문에 값을 명시하지 않으면 모든 HTTP method를 처리합니다.

## headers
```java
@RequestMapping(value = "/ex/foos", headers = "key=val", method = GET)
@ResponseBody
public String getFoosWithHeader() {
    return "Get some Foos with Header";
}
```
이 매핑을 테스트하려면 아래 command를 실행해야 합니다.

>curl -i -H "key:val" http://localhost:8080/ex/foos

위의 예제에서 @RequestMapping은 headers를 가지고 있습니다.
headers는 요청을 처리할 HTTP header를 나타냅니다.
headers는 배열로도 표현할 수 있습니다.

```java
@RequestMapping(
  value = "/ex/foos", 
  headers = { "key1=val1", "key2=val2" }, method = GET)
@ResponseBody
public String getFoosWithHeaders() {
    return "Get some Foos with Header";
}
```

## produces, consumes

Spring 3.1부터 @RequestMapping은 다음과 같은 특성을 생성하고 사용합니다

```java
@RequestMapping(
  value = "/ex/foos", 
  method = RequestMethod.GET, 
  produces = "application/json"
)
@ResponseBody
public String getFoosAsJsonFromREST() {
    return "Get some Foos with Header New";
}
```

produces속성과 consumes속성은 HTTP header인 Accept와 Content-Type을 나타냅니다.
produces은 client가 server에게 특정 형식을 요청하는 Accept header를 나타냅니다.
consumes는 client가 server에게 보내는 contents의 Content-Type header를 나타냅니다.

# RequestMapping With PathVariables
***
URI의 일부를 @PathVariable을 이용하여 변수에 바인딩할 수 있습니다.

## @PathVariable
```java
@RequestMapping(value = "/ex/foos/{id}", method = GET)
@ResponseBody
public String getFoosBySimplePathWithPathVariable(
  @PathVariable("id") long id) {
    return "Get a specific Foo with id=" + id;
}
```

이 매핑을 테스트하려면 아래 command를 실행해야 합니다.
>curl http://localhost:8080/ex/foos/1

메서드 매개 변수의 이름이 경로 변수의 이름과 정확히 일치하는 경우 값이 없는 @PathVariable을 사용하여 이 작업을 단순화할 수 있습니다:

method의 parameter의 이름과 uri의 path variable의 이름이 같다면 @PathVariable의 value설정을 생략할 수 있습니다.

```java
@RequestMapping(value = "/ex/foos/{id}", method = GET)
@ResponseBody
public String getFoosBySimplePathWithPathVariable(
  @PathVariable String id) {
    return "Get a specific Foo with id=" + id;
}
```

## Multiple @PathVariable

@PathVariable은 여러개 사용할 수 있습니다.
```java
@RequestMapping(value = "/ex/foos/{fooid}/bar/{barid}", method = GET)
@ResponseBody
public String getFoosBySimplePathWithPathVariables
  (@PathVariable long fooid, @PathVariable long barid) {
    return "Get a specific Bar with id=" + barid + 
      " from a Foo with id=" + fooid;
}
```

## @PathVariable With Regex

@PathVariable은 정규식을 사용할 수 있습니다.

```java
@RequestMapping(value = "/ex/bars/{numericId:[\\d]+}", method = GET)
@ResponseBody
public String getBarsBySimplePathWithPathVariable(
  @PathVariable long numericId) {
    return "Get a specific Bar with id=" + numericId;
}
```

다음과 같이 사용하여 path variable의 형식을 제한할 수 있습니다.

# RequestMapping with Request Parameters
***

@RequestMapping은 url parameter를 @RequestParam을 이용하여 쉽게 매핑할 수 있도록 해줍니다.
## @RequestParam
```java
@RequestMapping(value = "/ex/bars", method = GET)
@ResponseBody
public String getBarBySimplePathWithRequestParam(
  @RequestParam("id") long id) {
    return "Get a specific Bar with id=" + id;
}
```

이 매핑을 테스트하려면 아래 command를 실행해야 합니다.
> curl -i -d id=100 http://localhost:8080/spring-rest/ex/bars

## RequestMapping define Request Parameters
@RequestMapping은 request parameter를 정의할 수 있습니다.

```java
@RequestMapping(value = "/ex/bars", params = "id", method = GET)
@ResponseBody
public String getBarBySimplePathWithExplicitRequestParam(
  @RequestParam("id") long id) {
    return "Get a specific Bar with id=" + id;
}
```

좀더 유연하게도 사용합니다.
정의한 값을 모두 사용하지 않아도 가능합니다.

```java
@RequestMapping(
  value = "/ex/bars", 
  params = { "id", "second" }, 
  method = GET)
@ResponseBody
public String getBarBySimplePathWithExplicitRequestParams(
  @RequestParam("id") long id) {
    return "Narrow Get a specific Bar with id=" + id;
}
```
항상 @RequestMapping에서 정의한 값 중 가장 구체적으로 정의된 것이 사용됩니다.


# RequestMapping Corner Cases
***

## @RequestMapping with Multiple Paths or HTTP Methods
```java
@RequestMapping(
    value = { "/ex/advanced/bars", "/ex/advanced/foos" },
    method = GET)
@ResponseBody
public String getFoosOrBarsByPath() {
    return "Advanced - Get some Foos or Bars";
}

@RequestMapping(
  value = "/ex/foos/multiple", 
  method = { RequestMethod.PUT, RequestMethod.POST }
)
@ResponseBody
public String putAndPostFoos() {
    return "Advanced - PUT and POST within single method";
}
```

## Mapping Error Cases

### Ambiguous Mapping
HTTP 메서드, URL, 매개 변수, 헤더 및 미디어 유형이 동일한 경우 에러가 발생합니다.

```java
@GetMapping(value = "/foos/duplicate" )
public String duplicate() {
    return "Duplicate";
}

@GetMapping(value = "/foos/duplicate" )
public String duplicateEx() {
    return "Duplicate";
}
```

이 경우에 에러가 발생합니다.
>Caused by: java.lang.IllegalStateException: Ambiguous mapping.

