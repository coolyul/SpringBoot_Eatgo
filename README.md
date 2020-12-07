# Delivery


#### 시스템 설명

사용자, 가게 주인, 관리자, 로그인 설정 총 4개의 Api로 나누어져 있는 시스템입니다.  
각 Api 는 Localhost:8080/restaurants 통해 접속하실 수 있습니다.

Back-end 부분의 구현 코드만 포함이 되어 있으며,   
Front-end 부분을 함께 구현하면 보일 부분을 스크린샷으로 첨부합니다.

# Technologies

* Java
* SpringBoot
* Rest API
* JPA
* H2 DATABASE
* JWT
* Git


# Features

## 1. Delivery-Admin-Api

 * 관리자 Api
 
 
 
    - 레스토랑 조회, 수정 및 레스토랑 메뉴 조회와 수정  
        
        
    
    ![image](https://user-images.githubusercontent.com/65394344/101315252-86f29d80-389d-11eb-9f3d-aa65d91c392f.png)  
    
        
    - 지역 조회 및 수정  
        
        
        
    ![image](https://user-images.githubusercontent.com/65394344/101315627-421b3680-389e-11eb-93ad-b8e7d5e7d85c.png)  
    
    

    - 카테고리 조회 및 수정  
        
        
        
    ![image](https://user-images.githubusercontent.com/65394344/101314975-0338b100-389d-11eb-9d1d-fc308c1cb231.png)  
    
    
    
    - 사용자 조회
        
        
    ![image](https://user-images.githubusercontent.com/65394344/101315713-7262d500-389e-11eb-8b93-0d5773c1a70e.png)
    
    
    
    - 레스토랑 id 별 리뷰 전체 조회
        
        
        
    ![image](https://user-images.githubusercontent.com/65394344/101315775-96beb180-389e-11eb-8e74-3db502d1c9b0.png)
       
   

## 2. Delivery-Customer-Api

* 고객 사용 Api


    - 카테고리 별 조회 및 레스토랑 정보 확인
        
        
    ![image](https://user-images.githubusercontent.com/65394344/101317149-44cb5b00-38a1-11eb-8e9a-aeeda402a40a.png)
       
    
    - 레스토랑 별 리뷰 작성 및 조회
    
  
    ![image](https://user-images.githubusercontent.com/65394344/101317492-f5d1f580-38a1-11eb-8148-833190d13a90.png)
    
    
    - 예약 서비스
    
  
    ![image](https://user-images.githubusercontent.com/65394344/101317602-2023b300-38a2-11eb-9370-6aac79c47db5.png)


        

## 3. Delivery-Restaurant-Api

 * 가게 주인의 예약 확인
 
    - 예약 리스트를 GetMapping으로 가져와 예약 목록 조회
    
    
    ![image](https://user-images.githubusercontent.com/65394344/101315940-e7cea580-389e-11eb-9180-5a7153e8726e.png)
    
    

## 4. Delivery-Login-Api

 * UserService
 
    - Authenticate 를 통해 email, password로 유저 검증
  
 * Session Controller 
 
    - email, password로 유효성 검사
    - 고유 accessToken 생성
    - User 생성시 패스워드 암호화
    - 리뷰 추가시 이름 입력 없이 토큰 인증



