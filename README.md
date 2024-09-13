### 스프링을 활용한 상품주문 웹사이트 구현
---
#### 방문 링크
http://eedo.shop:8080

---
#### 도식도
<img width="800" alt="스크린샷 2024-09-11 10 30 39" src="https://github.com/user-attachments/assets/8bc35cdd-5982-4c67-9d36-953fb208dbe8">

---

### 구현 기능


### 기본 메인화면
<img width="400" alt="스크린샷 2024-09-11 10 30 39" src="https://github.com/user-attachments/assets/4a2f48d6-a544-4e5f-b222-1d7dd504f444">

----

###  회원 가입 기능

##### 스프링 시큐리티 회원가입
<img width="400" alt="스크린샷 2024-09-11 10 30 57" src="https://github.com/user-attachments/assets/f5fe839c-6029-46d8-924d-be3d16d8d0c5">

##### 다음 도로명API로 도로명주소 불러오기 기능
<img width="400" alt="스크린샷 2024-09-11 10 31 14" src="https://github.com/user-attachments/assets/95465490-a5d6-4e98-9fcf-a2127809c7a8">

##### 권한에 따른 페이지 접속 가능 (User 및 Admin 권한 존재)
<img width="400" alt="스크린샷 2024-09-11 10 30 51" src="https://github.com/user-attachments/assets/cd8c04e6-f1a8-4b1b-9727-6c6ea417f8a7">

##### Oauth2 네이버 소셜로그인 기능
<img width="400" alt="스크린샷 2024-09-11 10 31 25" src="https://github.com/user-attachments/assets/f3078e9e-e2a8-42fc-8a17-48c9abcef2e9">


----

### 상품
##### 페이징 기능(10개당 1페이지) 및 재고 확인 가능
<img width="400" alt="스크린샷 2024-09-11 10 31 31" src="https://github.com/user-attachments/assets/dc6b4bf8-1f75-4abc-aef9-dc4a41551d3e">

----

### 상품 주문
##### 기본 주문 화면 창
<img width="400" alt="스크린샷 2024-09-11 10 31 47" src="https://github.com/user-attachments/assets/54dfd5af-b91d-4b9b-8a5d-609dcf644345">
<img width="400" alt="스크린샷 2024-09-11 10 31 44" src="https://github.com/user-attachments/assets/131aa085-6eb4-45ac-b385-4cfa80dd8110">
<img width="400" alt="스크린샷 2024-09-11 10 31 50" src="https://github.com/user-attachments/assets/792dd027-cba0-42be-a4c4-13093c265f15">
<img width="400" alt="스크린샷 2024-09-11 10 32 15" src="https://github.com/user-attachments/assets/cdc47908-059c-45d6-86c0-17359b302e3f">




##### 주문 시 상품 재고 감소
###### 주문 이전 재고 93개
<img width="400" alt="스크린샷 2024-09-11 10 31 31" src="https://github.com/user-attachments/assets/dc6b4bf8-1f75-4abc-aef9-dc4a41551d3e">

###### 주문 3개 
<img width="400" alt="스크린샷 2024-09-11 10 32 15" src="https://github.com/user-attachments/assets/117f939a-caac-43b6-a20f-9d7e47e08f12">

###### 주문 이후 재고 90개
<img width="400" alt="스크린샷 2024-09-11 10 32 27" src="https://github.com/user-attachments/assets/ca72a3a9-42a9-46ef-90b4-532c4f973ce8">

##### 주문 결과 화면 창
<img width="400" alt="스크린샷 2024-09-11 10 32 21" src="https://github.com/user-attachments/assets/4b80bd47-db37-49ee-b8b5-37e5370f8617">

##### 주문 취소 시 상품재고 증가
<img width="400" alt="스크린샷 2024-09-11 10 41 50" src="https://github.com/user-attachments/assets/4f441f6a-dbe0-4d13-b8a2-c9aaf9bd202c">
<img width="400" alt="스크린샷 2024-09-11 10 31 31" src="https://github.com/user-attachments/assets/dc6b4bf8-1f75-4abc-aef9-dc4a41551d3e">

----
### 카카오 페이 API 기능 추가
##### 주문 버튼 누를시 카카오페이 결제로 이동
<img width="400" alt="스크린샷 2024-09-13 12 05 01" src="https://github.com/user-attachments/assets/fe2268d4-ffff-4da6-92c0-436f4cb0cca3">

##### 주문하는 창
###### 모바일 기기에서 결제
<img width="300" alt ="스크린샷" src="https://github.com/user-attachments/assets/7a992d25-e1a6-47c0-8709-15ce3b47ea34">
<img width="300" alt="스크린샷 2024-09-13 12 05 01" src="https://github.com/user-attachments/assets/16bd695d-fd73-4f26-9ee8-cd01a016f92f">


###### 결제 완료시,주문 결과 창으로 이동
<img width="400" alt="스크린샷 2024-09-13 12 05 41" src="https://github.com/user-attachments/assets/a2e829f8-b530-4eea-a64d-e2a0b1595d6c">

---

##### 주문 취소
<img width="400" alt="스크린샷 2024-09-13 12 05 46" src="https://github.com/user-attachments/assets/dcc75cd7-f1d2-472c-84d9-ed3aa07e6ded">

###### 돈 환불
<img width="300" src="https://github.com/user-attachments/assets/5c6e001c-3db8-46a5-bd6e-555cb7e3fff4">



