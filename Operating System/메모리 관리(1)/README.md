# 메모리 관리 (1)

### 2023년 04월 3주차

### 메모리 관리의 목적

![스크린샷 2023-04-23 오후 8.41.19.png](img/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-23_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_8.41.19.png)

- 프로그래밍에 추상화 지원
- 적은 오버헤드로 최고의 성능을 내도록 프로세스들 사이에서 부족한 메모리 자원을 할당
- 프로세스 사이의 분리 지원 (메모리 보호)

### Loading

: 디스크에 있는 데이터를 메모리로 옮기는 것

![스크린샷 2023-04-23 오후 8.49.26.png](img/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-23_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_8.49.26.png)

## 논리적 주소와 물리적 주소

### 논리적 주소 (가상 주소, Logical Address)

: 프로세스가 메모리에 올라갈 때, 그 프로세스를 위한 독립적인 주소 공간

→ 논리주소를 프로세스가 자체적으로 설정 (0번지부터 시작)

→ 논리주소는 프로세스 내부에서 식별

### 물리적 주소 (Physical Address)

: 메모리에 실제로 올라가는 위치

![스크린샷 2023-04-23 오후 5.51.30.png](img/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-23_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_5.51.30.png)

예) 책 A, B, C, D… 등이 있다 (각 책은 100 page씩이라 가정)

A의 논리주소는 0 ~ 100까지, A의 물리주소는 0~100까지

B의 논리주소는 0 ~ 100까지, B의 물리주소는 A의 끝에서 시작 (101~201까지)


- OS는 메모리 기법(가상 메모리)으로 논리주소를 물리주소로 변환하여 실제 데이터를 읽거나 쓰는 등의 작업에 사용


### 왜 논리적 주소를 사용하는가?

- 프로세스마다 물리 메모리 주소 공간을 할당하는 것은 복잡하고 어려운 일
- 여러 개의 프로세스가 실행 중일 때, 메모리 사용의 최적와와 충돌 방지를 위해


## 주소의 바인딩 (**Address Binding)**

: 프로세스의 논리적 주소를 물리적 주소로 연결하는 작업

: 프로그램이 적재되는 물리적 메모리의 주소가 결정되는 시기에 따라 세가지로 분류


### 컴파일 타임 바인딩 (**Compile Time Binding)**

: 프로그램을 컴파일할 때, 물리적 메모리 주소가 결정되는 방식

- 절대적 주소를 가짐 (물리적 주소 = 논리적 주소)
- 물리적 주소를 옮기려면 다시 컴파일 해야 함
  → 비 현실적이고 현대의 시분할 컴퓨팅 환경에서는 잘 사용X


![스크린샷 2023-04-23 오후 7.36.19.png](img/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-23_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_7.36.19.png)


### 로드 타임 바인딩 (적재 시간 바인딩, **Load Time Binding)**

: 프로그램이 주 메모리에 적재될 때, 물리적 주소가 결정되는 방식

- 물리적 주소와 논리적 주소가 다름
- loader의 책임하에 물리적 메모리 주소가 부여되며, 프로그램이 종료될 때까지 물리적 메모리 상의 위치 고정
    - loader : 사용자 프로그램을 메모리에 적재시키는 프로그램
- 프로그램이 종료될 때까지 물리적 주소가 고정되며 물리적 주소를 바꾸려면 재적재 해야 함
- 컴파일 시 물리 메모리 위치를 알 수 없다면, 컴파일러는 일단 relocatable(재배치 가능한 코드)로 만들고 심볼과 번지수 할당은 실제 메모리에 적재될 때
- Compiler translates symbolic code into relocation code
  - relocation code : 메모리의 어느 위치에서도 실행할 수 있는 기계어
- 참조하는 명령어들이 많아 이들의 주소를 모두 바꿔줘야해서 로딩 시간이 매우 커질 수 있는 단점
  → 실제로 잘 안쓰임


![스크린샷 2023-04-23 오후 7.36.49.png](img/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-23_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_7.36.49.png)

### 실행 시간 바인딩 (**Execution Time Binding/RunTime Binding)**

: 프로그램이 실행을 시작한 후에도 프로그램이 위치한 물리적 메모리상의 주소가 변경될 수 있는 방식

- CPU가 논리적 주소를 참조해서 물리적 주소의 어디에 있는지 주소 매핑 테이블을 이용

  (주소의 덧셈 ex. 98000(논리 주소) + 100000 → 198000번지(물리 주소))
- 이런 연산을 도와주는 메모리 관리 장치 (MMU, Memory management Unit)이라는 하드웨어적 지원이 필요


![스크린샷 2023-04-23 오후 7.37.11.png](img/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-23_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_7.37.11.png)

### 로드 타임 바인딩과 실행 타임 바인딩의 차이

- 로드 타임 바인딩은 loading할 때 주소 변환 작업을 미리 다 해놓지만 실행 타임 바인딩은 실행할 때
- 즉, 로드 타임 바인딩은 한 번만 바꿔 놓으면 똑같은 해당 주소로 접근
- 실행 시간 바인딩의 경우 runtime에 변환 작업을 수행하고 메모리에 접근

### 메모리 관리 장치 (MMU)

: CPU 안에 탑재되어 논리적 주소를 물리적 주소로 매핑해주는 하드웨어 장치

- 논리적 주소에 기준 레지스터의 값(Base Register)을 더해 물리적 주소값을 얻어냄
  - 기준 레지스터 : 재배치 레지스터 (Relocation Register)라고도 하며, 프로세스의 물리적 메모리 시작 주소를 가지고 있음


![스크린샷 2023-04-23 오후 8.18.09.png](img/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-23_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_8.18.09.png)

- 한계 레지스터 (Limit Register)
  - CPU가 해당 프로세스의 주소 공간을 넘어서는 메모리 참조를 하려고 하는지 체크하는 용도
  - 현재 CPU에서 수행 중인 프로세스의 논리적 주소의 최댓값 (프로세스의 크기)


![스크린샷 2023-04-23 오후 8.15.40.png](img/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-23_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_8.15.40.png)

### 실행과정


![스크린샷 2023-04-24 오후 9.28.13.png](img/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2023-04-24_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_9.28.13.png)

1. CPU가 현재 실행 중인 process p1의 논리 주소 346을 요청
2. process p1은 물리적 주소 14000번부터 올라가 있음
3. process p1이 혹여 자신의 메모리 범위(3000)가 아닌 주소를 요청할 경우를 막기 위해 limit register의 값과 비교
4. limit register 보다 요청 주소가 크면 trap 발생 후 해당 프로세스 종료
5. limit register 보다 요청 주소가 작으면 base register에 논리 주소를 더해 접근

## 출처

[[운영체제OS]Memory Management 메모리 시스템(메모리 관리 목표)](https://jhnyang.tistory.com/39)

[[운영체제 10편] 메모리 접근](https://baebalja.tistory.com/390)

[주소 바인딩](https://techvu.dev/103)

[[OS] 운영체제 9. Memory Management : 논리/물리 주소, MMU(Memory Management Unit)](https://developyo.tistory.com/210)
