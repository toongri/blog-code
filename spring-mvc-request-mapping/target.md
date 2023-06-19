# 📖 Spring MVC 학습 테스트
## 소개
***
- 무엇을 학습하기 위한 프로젝트 저장소
  org.springframework.web.bind.annotation package에 있는 Annotation을 테스트로 실행해보며 학습하기
### 활용 방법

- Spring MVC에서 웹 요청/응답의 기본적인 내용을 주제별 자료로 구분되어있다.
- 키워드를 기반으로 학습 자료와 Spring 문서를 통해 정확한 사용 방법을 파악한다.
- 학습 테스트 활용 방법를 참고하여 학습 테스트를 수행하면서 기능을 확인한다.
- 이해한 내용이 공식 문서에는 어떻게 언급되어있는지 확인한다.
- 확인하고자 하는 기능의 학습 테스트가 없는 경우 직접 작성해서 기능을 검증한다.
## 학습 주제별 자료
***
## RequestMapping: 웹 요청을 처리하는 메서드에 매핑
### 키워드
- Path, Method
- @PathVariable
- @RequestParam
- Shortcut Annotations
### 학습 테스트
-[ ] Mapping
  -[ ] Http Method
  -[ ] Uri Pattern
  -[ ] Media Type
  -[ ] Parameter Header
### Baeldung 학습 자료
- @RequestMapping in Spring
- Spring @PathVariable Annotation
- Spring @RequestMapping New Shortcut Annotations
### 공식 문서
- Request Mapping
- URI patterns
### 기타 참고
- A
- B
***
## Method Arguments: 웹 요청 정보를 메서드 파라미터로 주입
### 키워드
- @RequestParam
- @ModelAttribute
- @RequestBody
- @RequestHeader
### 학습 테스트
- Handler
- Method Argument
- Baeldung 학습 자료
- Spring @RequestParam Annotation
- The @ModelAttribute Annotation
### 공식 문서
- Method Arguments
***
## Return Values: 웹 요청에 대한 응답 설정
### 키워드
- @ResponseBody
- ResponseEntity
### 학습 테스트
- Handler
- Return Value
### Baeldung 학습 자료
Spring’s RequestBody and ResponseBody Annotations
Using Spring @ResponseStatus to Set HTTP Status Code
### 공식 문서
- Return Values
***
## Error: 에러 처리
### 키워드
- Exception Handler
- Controller Advice 
### 학습 테스트
- [ ] Exception: 요청에 대한 예외처리 부분 학습 테스트
  - [ ] Exception
### Baeldung 학습 자료
- Error Handling for REST with Spring
### 공식 문서
- Exceptions
- Controller Advice
## 기타
Spring Web Annotations
Spring Web Annotations
The Spring @Controller and @RestController Annotations
Request Mapping
Media Types - produces
Media Types - consumes
Parameters, headers