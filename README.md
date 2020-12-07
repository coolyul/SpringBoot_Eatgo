# Delivery


#### 시스템 설명

사용자, 가게 주인, 관리자, 로그인 설정 총 4개의 Api로 나누어져 있는 시스템입니다.

Back-end 부분의 구현 코드만 포함이 되어 있으며, 
Front-end 부분을 함께 구현하면 보일 부분을 스크린샷으로 첨부합니다.

## Technologies

* Java
* SpringBoot
* Rest API
* JPA
* H2 DATABASE
* JWT
* Git


## Features

### Delivery-Admin-Api

 * 관리자 Api
    - 카테고리, 지역, 레스토랑, 레스토랑 별 메뉴, 리뷰, 유저 조회 및 수정

### Delivery-Customer-Api

* 고객 사용 Api
    - 카테고리 별 조회
    - 지역 별 조회
    - 레스토랑 별 리뷰 작성 및 조회
    - 예약 서비스
    - 유저 생성 및 비밀번호 암호화

### Delivery-Restaurant-Api

 * 가게 주인의 예약 확인
    - 예약 리스트를 GetMapping으로 가져와 예약 목록을 보여줌 

### Delivery-Login-Api

 * UserService
    - Authenticate 를 통해 email, password로 유저 검증
  
 * Session Controller 
    - email, password로 유효성 검사
    - 고유 accessToken 생성
    - User 생성시 패스워드 암호화
    - 리뷰 추가시 이름 입력 없이 토큰 인증



