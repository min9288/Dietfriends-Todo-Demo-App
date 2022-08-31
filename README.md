bㅠ# Dietfriends-Todo-Demo-App
> TODO 서비스를 제공하는 애플리케이션 서버입니다.


</br>

## 기술 스택
- Spring Boot (API Server)
- Spring Security (Security)
- JPA & QueryDSL (ORM)
- MySQL (8.0 / Google Cloud SQL) (RDB)
- AWS EC2 (Infra)
- AWS S3 (Build Store)
- Git Action & AWS Codedeploy (CI/CD)
- Nginx (Reverse Proxy Server)
- Postman (Documentation)
- DBeaver (Database tool)
- IntelliJ (Development tool)

</br>

## 서비스 주요기능
- 유저
    - 회원가입, 로그인, 토큰재발행 기능이 있습니다.
        - 회원정보는 이름, 나이, 암호, 가입일로 구성되어 있습니다. 
        - 유저PK는 UUID로 구성되며, 고유성을 가집니다.
        - 로그인시 토큰이 발급되며, 일부 API를 제외하고는 토큰이 사용해서 API에 접근할 수 있습니다.
        - 토큰이 만료되면, 토큰을 재발행하여 사용할 수 있습니다.

</br>

- TODO
  - TODO 등록 / 수정 / 삭제 / 전체조회 / 선택조회 기능이 있습니다.
    - 등록은 name, completed 값을 이용합니다. (POST)
    - 수정은 name, completed 값을 이용합니다. (PUT)
    - 삭제는 TODO의 pk값(UUID)을 이용합니다. (DELETE)
    - 전체조회는 로그인한 유저정보를 사용합니다 (GET)
    - 선택조회는 TODO의 pk값(UUID)을 이용합니다. (GET)


</br>

## 도메인 명세

</br>

- USER

|이름|타입|설명|필수/선택|
|------|---|---|---|
|userUUID|UUID|PK|필수|
|userName|String|유저이름|필수|
|password|String|유저 패스워드|필수|
|age|Integer|유저 나이|필수|
|userEnrollDate|LocalDate|가입일|필수|
|refreshToken|String|재발행 토큰|선택|


</br>

- TODO

|이름|타입|설명|필수/선택|
|------|---|---|---|
|todoUUID|UUID|PK|필수|
|name|String|todo 내용|필수|
|completed|Boolean|완료 여부|선택|
|createdAt|LocalDateTime|생성일|필수|
|updatedAt|LocalDateTime|수정일|선택|
|completedAt|LocalDateTime|완료일|선택|
|userUUID|userUUID|완료일|필수|

 </br>

## 가용서버 및 인증 설명
- 15.164.152.198
    - 위 서버에서 테스트 가능합니다.

    </br>

- 토큰 인증은 회원가입/로그인/토큰재발급 (/signs/**) 를 제외한 API에서는 HTTP 헤더의 토큰값 인증 후 사용할 수 있습니다.
```bash
HTTP 헤더
key : X-AUTH-TOKEN
value : 엑세스 토큰값 (로그인시 Response 값으로 확인할 수 있습니다.)

```

 </br>

## API 명세
> signs - 토큰 인증 불필요
>
> todos - 토큰 인증 필요


### signs - 회원가입
```bash
Post
15.164.152.198/signs/register

{
    "userName" : "String",
    "password: : "String",
    "age" : "Integer"
}

Response
{
    "success": true,
    "code": 200,
    "msg": "성공",
    "data":{
        "userName" : "String",
        "age" : "Integer"
    }
}

Result (POST 후 생성되는 값)
{
    "userUUID" : "UUID",
    "userName" : "String",
    "password: : "String",
    "age" : "Integer",
    "userEnrollDate" : "LocalDate",
}
```

<br/>


### signs - 로그인
```bash
Post
15.164.152.198/signs/login

{
    "userName" : "String",
    "password: : "String",
}

Response
{
    "success": true,
    "code": 200,
    "msg": "성공",
    "data": {
        "userUUID": "UUID",
        "token": "String",
        "refreshToken": "String"
    }
}

Result (POST 후 생성되는 값)
{
    "token" : "String",
    "refreshToken" : "String"
}
```

<br/>

### signs - 토큰재발행
```bash
Post
15.164.152.198/signs/reissue

{
    "accessToken" : "String",
    "refreshToken" : "String"
}

Response
{
    "success": true,
    "code": 200,
    "msg": "성공",
    "data": {
        "accessToken": "String",
        "refreshToken": "String"
    }
}

Result (POST 후 생성되는 값)
{
    "token" : "String",
    "refreshToken" : "String"
}
```

<br/>

### todos - todo 등록
```bash
Post
15.164.152.198/todos

{
    "name" : "String",
    "completed" : "false/true"
}

Response
{
    "success": true,
    "code": 200,
    "msg": "성공",
    "data": {
        "todoUUID": "UUID",
        "name": "String",
        "completed": false/true,
        "completed_at": null,
        "createdAt": LocalDateTime
        "updated_at": "LocalDateTime"
    }
}

Result (POST 후 생성되는 값)
{
    "todoUUID": "UUID",
    "name": "String",
    "completed": false/true,
    "completed_at": null,
    "createdAt": LocalDateTime
    "updated_at": "LocalDateTime"
}
```
