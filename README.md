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
    
</deails>

