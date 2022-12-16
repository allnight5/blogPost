# blogPost
spring 입문주차 과제
강제 푸시하면서.. Readme도 같이 사라져서........ 다시 쓰기 힘들어서 우선은 우선은..
이것으로

# 입문
---
<details>
    <summary>입문요구사항 </summary>
    
1. 아래의 요구사항을 기반으로 Use Case 그려보기
    - 손으로 그려도 됩니다. 
    ![UML 유스케이스 다이어그램](https://user-images.githubusercontent.com/45612782/206932352-111139f8-e48d-47f2-ad5a-04ce412723d3.png)

2. 전체 게시글 목록 조회 API
    - 제목, 작성자명, 작성 내용, 작성 날짜를 조회하기
    - 작성 날짜 기준 내림차순으로 정렬하기
3. 게시글 작성 API
    - 제목, 작성자명, 비밀번호, 작성 내용을 저장하고
    - 저장된 게시글을 Client 로 반환하기
4. 선택한 게시글 조회 API
    - 선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기 
    (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
5. 선택한 게시글 수정 API
    - 수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    - 제목, 작성자명, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
6. 선택한 게시글 삭제 API
    - 삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    - 선택한 게시글을 삭제하고 Client 로 성공했다는 표시 반환하기
</details>


# 숙련1
----
<details>
    <summary>숙련요구사항 </summary>
    
    1. 회원 가입 API
        - username, password를 Client에서 전달받기
        - username은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
        - password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성되어야 한다.
        - DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기  
            
    2. 로그인 API
        - username, password를 Client에서 전달받기
        - DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
        - 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고, 
        발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기
</details>

<details>
    <summary>숙련 요구사항에 따른 API</summary>
    
    1. 전체 게시글 목록 조회 API
        - 제목, 작성자명(username), 작성 내용, 작성 날짜를 조회하기
        - 작성 날짜 기준 내림차순으로 정렬하기
    2. 게시글 작성 API
        - 토큰을 검사하여, 유효한 토큰일 경우에만 게시글 작성 가능
        - 제목, 작성자명(username), 작성 내용을 저장하고
        - 저장된 게시글을 Client 로 반환하기
    3. 선택한 게시글 조회 API
        - 선택한 게시글의 제목, 작성자명(username), 작성 날짜, 작성 내용을 조회하기 
        (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
    4. 선택한 게시글 수정 API
        - ~~수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후~~
        - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 수정 가능
        - 제목, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
    5. 선택한 게시글 삭제 API  
        - ~~삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후~~
        - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 삭제 가능
        - 선택한 게시글을 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기
</details>

<details>
    <summary>ERD설계</summary>
    
![ERD](https://user-images.githubusercontent.com/45612782/208002381-635a21da-9b87-4602-962c-409724f9a33a.JPG)
    
    처음에는 게시글 id로 했다가. 중복 처리된것이 username이니 username를 oneToMnay형식으로 ERD를 만들어주긴했는데..
    아직 직접 oneToMany어노테이션을 쓰지는 않았다.
</details>

# 숙련 2
----
<details>
    <summary>숙련2 요구사항 </summary>
    
    1. 회원 가입 API
    - username, password를 Client에서 전달받기
    - username은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
    - password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자`로 구성되어야 한다.
    - DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기
    - 회원 권한 부여하기 (ADMIN, USER) - ADMIN 회원은 모든 게시글, 댓글 수정 / 삭제 가능
    - 참고자료
        1. [https://mangkyu.tistory.com/174](https://mangkyu.tistory.com/174)
        2. [https://ko.wikipedia.org/wiki/정규_표현식](https://ko.wikipedia.org/wiki/%EC%A0%95%EA%B7%9C_%ED%91%9C%ED%98%84%EC%8B%9D)
        3. [https://bamdule.tistory.com/35](https://bamdule.tistory.com/35)
        
    2. 로그인 API
        - username, password를 Client에서 전달받기
        - DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
        - 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고, 
        발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기
    3. 댓글 작성 API
        - 토큰을 검사하여, 유효한 토큰일 경우에만 댓글 작성 가능
        - 선택한 게시글의 DB 저장 유무를 확인하기
        - 선택한 게시글이 있다면 댓글을 등록하고 등록된 댓글 반환하기
    4. 댓글 수정 API
        - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 댓글만 수정 가능
        - 선택한 댓글의 DB 저장 유무를 확인하기
        - 선택한 댓글이 있다면 댓글 수정하고 수정된 댓글 반환하기
    5. 댓글 삭제 API
        - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 댓글만 삭제 가능
        - 선택한 댓글의 DB 저장 유무를 확인하기
        - 선택한 댓글이 있다면 댓글 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기
    6. 예외 처리
        - 토큰이 필요한 API 요청에서 토큰을 전달하지 않았거나 정상 토큰이 아닐 때는 "토큰이 유효하지 않습니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기
        - 토큰이 있고, 유효한 토큰이지만 해당 사용자가 작성한 게시글/댓글이 아닌 경우에는 “작성자만 삭제/수정할 수 있습니다.”라는 에러메시지와 statusCode: 400을 Client에 반환하기
        - DB에 이미 존재하는 username으로 회원가입을 요청한 경우 "중복된 username 입니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기
        - 로그인 시, 전달된 username과 password 중 맞지 않는 정보가 있다면 "회원을 찾을 수 없습니다."라는 에러메시지와 statusCode: 400을 Client에 반환하기
    
</details>
<details>
    <summary>숙련2 요구사항에 따른 API</summary>
    
    1. 전체 게시글 목록 조회 API
        - 제목, 작성자명(username), 작성 내용, 작성 날짜를 조회하기
        - 작성 날짜 기준 내림차순으로 정렬하기
        - 각각의 게시글에 등록된 모든 댓글을 게시글과 같이 Client에 반환하기
        - 댓글은 작성 날짜 기준 내림차순으로 정렬하기
    2. 게시글 작성 API
        - 토큰을 검사하여, 유효한 토큰일 경우에만 게시글 작성 가능
        - 제목, 작성자명(username), 작성 내용을 저장하고
        - 저장된 게시글을 Client 로 반환하기
    3. 선택한 게시글 조회 API
        - 선택한 게시글의 제목, 작성자명(username), 작성 날짜, 작성 내용을 조회하기 
        (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
        - 선택한 게시글에 등록된 모든 댓글을 선택한 게시글과 같이 Client에 반환하기
        - 댓글은 작성 날짜 기준 내림차순으로 정렬하기
    4. 선택한 게시글 수정 API
        - ~~수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후~~
        - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 수정 가능
        - 제목, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
    5. 선택한 게시글 삭제 API
        - ~~삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후~~
        - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 삭제 가능
        - 선택한 게시글을 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기
</details>

# api명세서(숙련)
---

<details>
    <summary>API명세서펼쳐서보기</summary>
    
API 명세서
---  
| 기능 | API URL | Method | Request Header | Request | Response | Response header |
|---|---|---|---|---|---|---|
| 회원가입 | /api/signup | POST |  | {<br> "username": "test1122",<br> "password": "Test12345",<br> } | {<br> "msg": "회원가입 완료",<br>"statusCode": 200<br> } | 
로그인 | /api/login | POS |  | {<br>"username": "test1122",<br>"password": "Test12345"<br>} | {<br>"msg": "로그인 성공",<br>"statusCode": 200 } | Authorization:Bearer <br>eyJhbGciOiJIUzI1NiJ9<br>.eyJzdWIiOiJ0amd1cnRuMSIsImF1<br>dGgiOiJVU0VSIiwiZXhwIjoxNjcwND<br>g0OTk0LCJpYXQiOjE2NzA0ODEzO<br>TR9.xwyHOpdM2Zgld1tJHGmrtVvJj<br>JRWcOF6OJM9j_f57WE | 
| 게시글 작성 | /api/boards | POST |Authorization:Bearer <br>eyJhbGciOiJIUzI1NiJ9<br>.eyJzdWIiOiJ0amd1cnRuMSIsImF1<br>dGgiOiJVU0VSIiwiZXhwIjoxNjcwND<br>g0OTk0LCJpYXQiOjE2NzA0ODEzO<br>TR9.xwyHOpdM2Zgld1tJHGmrtVvJj<br>JRWcOF6OJM9j_f57WE | {<br>"boardName": "글 제목",<br>"contents": "글 내용"<br>} | {<br>"createdAt": "2022-12-08T15:49:11.5919215",<br>"modifiedAt": "2022-12-08T15:49:11.5919215",<br>"id": 1,<br>"boardName": "글 제목",<br>"username": "test1122",<br>"contents": "글 내용"<br>} 
| 게시글 목록 조회 | /api/boards | POST | {<br>"createdAt": "2022-12-08T15:49:11.5919215",<br>"modifiedAt": "2022-12-08T15:49:11.5919215",<br>"id": 1,<br>"boardName": "글 제목",<br>"username": "test1122",<br>"contents": "글 내용"<br>}<br>{<br>"createdAt": "2022-12-08T15:49:11.5919215",<br>"modifiedAt": "2022-12-08T15:49:11.5919215",<br>"id": 2,<br>"boardName": "글 제목",<br>"username": "test1122",<br>"contents": "글 내용"<br>}
| 게시글 수정 | /api/boards | PUT | Authorization:Bearer <br>eyJhbGciOiJIUzI1NiJ9<br>.eyJzdWIiOiJ0amd1cnRuMSIsImF1<br>dGgiOiJVU0VSIiwiZXhwIjoxNjcwND<br>g0OTk0LCJpYXQiOjE2NzA0ODEzO<br>TR9.xwyHOpdM2Zgld1tJHGmrtVvJj<br>JRWcOF6OJM9j_f57WE | {<br>"boardName": "제목 수정",<br>"contents": "내용 수정"<br>} | {<br>"createdAt": "2022-12-08T15:49:11.5919215",<br>"modifiedAt": "2022-12-08T15:49:11.5919215",<br>"id": 1,<br>"boardName": "제목 수정",<br>"username": "test1122",<br>"contents": "내용 수정"<br>}
| 게시글 수정 | /api/boards | DELETE | Authorization:Bearer <br>eyJhbGciOiJIUzI1NiJ9<br>.eyJzdWIiOiJ0amd1cnRuMSIsImF1<br>dGgiOiJVU0VSIiwiZXhwIjoxNjcwND<br>g0OTk0LCJpYXQiOjE2NzA0ODEzO<br>TR9.xwyHOpdM2Zgld1tJHGmrtVvJj<br>JRWcOF6OJM9j_f57WE | | {<br>"msg": "게시글 삭제 성공",<br>"statusCode": 200<br>}|
</details>

# 고민해보기
---
<details>
<summary>입문과제제출시에는아래질문을고민해보고답변을함께제출해주세요.</summary> 
Why: 과제 제출시에는 아래 질문을 고민해보고 답변을 함께 제출해주세요.

1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body) 
@PathVariable을 이용하여 url의 일부경로를 파라미터로 이용하였고 그파라미터를
requestDto로 비밀번호만을 가져와서 비교하고 삭제하는데 @RequestBody를 사용했습니다.

2. 어떤 상황에 어떤 방식의 request를 써야하나요?

- @PathVariable
url 경로의 일부를 파라미터로 이용하는 방법
- @RequestBody
객체를 반환해주기 때문에 response 로 받아올 때 JSON 객체로 받아와
다른 형태변환을 해주지 않아도 되기때문에 간편합니다.

3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
예외처리 메시지가 아직 미숙해서 다완성은 안되었지만
나머지는 REST 규칙을 지키고 CRUD를 완성했으니 예외 메시지를 제외하고는 RESTful하게 설계된것 같습니다.

4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)
- Controller
클라이언트에게 요청을 받아 요청을 Service에게 리퀘스트 형태로 보내고
리스폰스 형태로 받아서 클라이언트에게 되돌려주면서 컨트롤에는 보내고 받는것 외에는 로직이 없으니
적절히 분리 된것 같습니다.

- Repository
entity와 dto를 활용하여 저장을 하면서 통신을 하였고 dto는 setter없이 getter로 하였습니다.
데이터베이스와 통신하여 service에 DB정보를 보내습니다.
- Service
받은 요청으로 DB에 요청을 보내고 정보를 받아 요청을 오류처리를 완벽히 못했지만
오류가 없다면 정보를 돌려줍니다.
</details>

<details>
<summary>숙련과제제출시에는아래질문을고민해보고답변을함께제출해주세요.</summary> 

1. 처음 설계한 API 명세서에 변경사항이 있었나요? 
   변경 되었다면 어떤 점 때문 일까요? 첫 설계의 중요성에 대해 작성해 주세요!
   
    -   내가 설계한것은 아니고.. 있는것 대로 만들긴했는데. 기준이 있다는 것이
        내가 DTO를 어떻게 만들면 될것이다 변수를 몇개 반환값은 무엇으로 받으면 되겠다는
        것을 알수있는데 설계가 잘못되면 자신이 원하는 값이 안나올 수도 있거나
        안되는 설계로 해둔다면 오류가 날수 밖에 없을것 같았다.

2. ERD를 먼저 설계한 후 Entity를 개발했을 때 어떤 점이 도움이 되셨나요?

    - 어떠한 컬럼과 어느 컬럼을 연결해야 하는 것을 생각하고 넘어가기 때문에 나중에 잘못된 방법으로 연결하는 것을 방지 할수 있다.


3. JWT를 사용하여 인증/인가를 구현 했을 때의 장점은 무엇일까요?
    
    - 사용자 인증 정보는 토큰이 가지고 있어 별도의 DB가 필요 하지 않아 인증시 DB에 의존 하지 않아도 된다. 
    - 트래픽에 대한 부담이 적다.

4. 반대로 JWT를 사용한 인증/인가의 한계점은 무엇일까요?

    - 토큰 자체에 정보를 담고 있으므로 이를 탈취당했을때 파기시간이 되기 전까지 매우 위험한 일이 발생할수있다.
    - 토큰 페이로드에 정보가 많아질수록 토큰의 길이가 늘어나 네트워크에 부하를 줄 수 있다.  
    
5. 만약 댓글 기능이 있는 블로그에서 댓글이 달려있는 게시글을 삭제하려고 한다면 무슨 문제가 발생할까요? Database 테이블 관점에서 해결방법이 무엇일까요? 
    - 그 게시글과 연관된 모든 정보를 삭제해야 하는데 방법으로는  
    - 게시글에 달리는 댓글의 외래키 옵션에 ON DELETE CASCADE를 설정해 주어야 한다.

6. IoC(Inversion of Contorol:제어의 역전) / DI(Dependency Injection: 의존성 주입) 에 대해 간략하게 설명해 주세요!
    - 스프링을 사용하기 전에는 개발자가 프로그램의 흐름(애플리케이션 코드)을 제어하는 주체였었다. 그러나 스프링에서는 프로그램의 흐름을 프레임 워크가 주도하게 된다. 객체의 생성 주기 관리를 컨테이너가 도맡아서 하게되어 제어권이 컨테이너로 넘어가게 되고 이것을 제어권의 흐름이 바뀌었다고 하여 IoC라고 한다.
    - 의존성주입 DI는 어떠한 클래스의 이름을 바꾸었을때 다른곳에서도 바껴야 한다면 바껴야하는곳은 이름을 바꾼 클래스에 의존성을 가지고 있다고한다. 

    - 스프링의 경우 xml을 사용한다면 의존성 주입을 하는데 환경설정 만으로도 프로그램을 제어할수 있게 되는데 이것이 가능한 이유가 스프링 컨테이너가 관리하는 IoC개념이 있기 때문이다.

    - bean 에 들어가는 값이 MG였는데
      컨테이너에서 <bean id ="Mg" class = "test1.MgEnd"/> or <bean id ="Mg" class = "test1.MgStart"/>
      이런식으로 값을 바꿔서 넣어 줄수있다

</deails>


