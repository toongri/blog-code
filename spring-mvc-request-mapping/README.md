Spring의 @RequestMapping

# 개요
Spring MVC의 annotation 중 하나인 @RequestMapping과 그 외의 관련된 annotation들을 살펴보려고 합니다.

# 본문

## @RequestMapping
```java
@RequestMapping(value = "/ex/foos", method = RequestMethod.GET)
@ResponseBody
public String getFoosBySimplePath() {
    return "Get some Foos";
}
```
이 매핑을 테스트하려면 아래 command를 실행해야 합니다.
>curl -i http://localhost:8080/spring-rest/ex/foos

위의 예제에서 @RequestMapping은 value와 method를 가지고 있습니다.
value는 요청을 처리할 URI를 나타내고, method는 요청을 처리할 HTTP method를 나타냅니다.
method는 배열로도 표현할 수 있습니다.

method 속성은 default 값이 존재하지 않으며, 때문에 값을 명시하지 않으면 모든 HTTP method를 처리합니다.

```java
@RequestMapping(value = "/ex/foos", headers = "key=val", method = GET)
@ResponseBody
public String getFoosWithHeader() {
    return "Get some Foos with Header";
}
```
이 매핑을 테스트하려면 아래 command를 실행해야 합니다.

>curl -i -H "key:val" http://localhost:8080/spring-rest/ex/foos

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