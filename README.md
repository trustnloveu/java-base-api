# File API

## API 구성

### 실행환경

* 프로그래밍 언어   : Java 17.0.7
* 프레임워크       : Spring Boot 3.0.5
* 저장소 관리      : Gradle 7.4


### 실행변수 ( 프로퍼티 )
* API 실행 포트    : 38080
* 스토리지 경로     : /home/upchain/storage ( 베이스가 되는 디렉토리 )

### API 구조

```shell
file-api
    ├── main-api
    ├── java-module-file
    ├── java-module-file-util
    └── java-module-exception-monitoring
```

* main-api            : 컨트롤러(Controller), API In/Out 관리
* module-file         : 파일 업로드, 삭제, 다운로드 서비스 모듈 (현재 Local 스토리지만 구현)
* module-file-util    : 파일 관련 유틸 모듈 (파일 유혀성 검증, 파일명 변환)



## 서비스 ( Rest API )

### 단일 파일 업로드 (1) - 파일 자체 이름으로 저장
```shell
/file/upload-file
```

* Request
    - Method : `POST`
    - Content-type : `form-data/multipart`
    - Param
        + `param` : JSON Map 형태

            ```json
            "param" : {
                "savePath" : "파일 저장 경로 (String)",
            }
            ```

        + `file` : 첨부 파일 (MultipartFile)

* Response
    - Data : JSON Map 형태

        ```json
        {
            "orgName" : "원본 파일명 (String)",
            "saveName" : "저장명 (String)",
            "savePath" : "저장 경로 (String)",
            "size" : "용량 (Long)",
            "extType" : "파일 확장자 (String)"
        }
        ```

### 단일 파일 업로드 (2) - 사용자 지정 파일명으로 저장
```shell
/file/upload-named-file
```

* Request
    - Method : `POST`
    - Content-type : `form-data/multipart`
    - Param
        + `param` : JSON Map 형태

            ```json
            "param" : {
                "savePath" : "파일 저장 경로 (String)",
                "fileName" : "사용자 지정 저장 파일명 (String)",
            }
            ```

        + `file` : 첨부 파일 (MultipartFile)

* Response
    - Data : JSON Map 형태

        ```json
        {
            "orgName" : "원본 파일명 (String)",
            "saveName" : "저장명 (String)",
            "savePath" : "저장 경로 (String)",
            "size" : "용량 (Long)",
            "extType" : "파일 확장자 (String)"
        }
        ```


### 복수 파일 업로드
```shell
/file/upload-fils
```

* Request
    - Method : `DELETE`
    - Content-type : `form-data/multipart`
    - Param
        + `param` : JSON Map 형태

            ```json
            "param" : {
                "savePath" : "파일 저장 경로 (String)",
            }
            ```

        + `files` : 첨부 파일 리스트 (MultipartFile[])

* Response
    - Data : JSON List\<Map> 형태

        ```json
        [
            {
                "orgName" : "원본 파일명 (String)",
                "saveName" : "저장명 (String)",
                "savePath" : "저장 경로 (String)",
                "size" : "용량 (Long)",
                "extType" : "파일 확장자 (String)"
            },
            ...
        ]
        ```


### 파일 삭제
```shell
/file/delete-file
```

* Request
    - Method : `POST`
    - Content-type : `application/json`
    - Param
        + `savePath` : 파일 저장 경로 (String)

* Response
    - Data : `NULL`



### 파일 다운로드
```shell
/file/download-file
```

* Request
    - Method : `GET`
    - Content-type : `application/json`
    - Param
        + `param` : JSON Map 형태

            ```json
            "param" : {
                "savePath" : "파일 저장 경로 (String)",
            }
            ```

* Response
    - Data : `List<Byte>` ( ! 추가 작업 예정 ! )
