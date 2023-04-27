# [JAVA 1강]
# 컴파일 및 Call By ____ 
---
## 사전 지식
> 자바는 OS에 독립적인 특징을 가지고 있다.  
`JVM`이라는 가상머신 때문!
어떤 이유에서 독립적인지 컴파일 과정을 통해 알아보자

### JVM 목적
---
1. 자바 프로그램이 어느 기기나 운영체제 상에서도 실행 될 수 있도록 하는 것
2. 프로그램 메모리를 관리하고 최적화 하는 것

### 컴파일 순서
---
![JVM구조](https://t1.daumcdn.net/cfile/tistory/991D064B5AE999D512)
1. 자바의 구동 : OS위에 JVM이 실행된다.
2. 개발자(사용자)가 .JAVA코드 작성
3. 컴파일러가 .JAVA 소스파일을 컴파일 => .class 파일(byte code)로 변환된다.
    - 모든 application의 시작지점은 main()
4. .class파일을 클래스 로더가 JVM에게 전달
5. 클래스 로더는 동적로딩(Dynamic Loading)을 통해 필요한 클래스들 로딩 및 링크 하여 런타임 데이터 영역(Runtime Data area), 즉 JVM메모리에 올린다.
    - ###### 클래스 로더 세부 동작
        1. `로드` : .class파일을 JVM 메모리에 로드
        2. `검증` : JVM명세 및 자바 언어 명세을 참고로 로드 데이터가 구성되었는지 검사
        3. `준비` : 클래스가 필요로 하는 메모리 할당
            - 필드, 메서드, 인터페이스, 클래스 등
        4. `분석` : 클래스의 상수 풀 내 모든 레퍼런스를 다이렉트 레퍼런스로 변겅
        5. `초기화` : 클래스 변수들을 적절한 값으로 초기화(Static)
5. JVM 메모리에 올라온 바이트 코드들은 명령어 단위로 하나씩 가져와서 실행엔진(Execution engine)이 실행.
    - 인터프리터 : 바이트 코드 명령어를 하나씩 읽어서 해석 및 실행. 하나하나는 빠르지만 전체적인 속도가 느린편
    - JIT(Just-In-Time)컴파일러 : 인터프리터의 단점을 보완하기 위해 도입된 방식으로 바이트 코드 전체 컴파일 -> 바이너리 코드로 변경. 바이너리 코드로 변경된 메서드는 바이너리 코드로 직접 실행. 전체실행 속도는 빠름


### 클래스 로더
---
![classloader](https://velog.velcdn.com/images/minseojo/post/484309b5-98d3-4f6c-82c2-dc7ab556a173/image.PNG)
- JAVA는 동적으로 클래스를 읽어옴 -> 프로그램이 실행 중인 `런타임`에서야 모든 코드가 JVM과 연결
- 이 과정을 수행하는 것이 클래스 로더 역할
- 컴파일러 : .java -> .class
- 클래스로더 : .class -> Runtime Data Area로 적재
- Runtime Data Area는 JVM이 OS로 부터 할당 받은 메모리 영역
- 

### 실행엔진(Execution Engine)
---
클래스 로더가 적재 시킨 .class파일(바이트 코드)들은 Runtime Data Area의 method Area에 배치됨 -> 
- JVM은 Method Area에 적재된 .class파일들을 실행엔진에게 제공
- 바이트 코드를 실행하는 런타임 모듈 = execution engine

### 가비지 컬렉터(Garbage Collector)
---
![GC](https://velog.velcdn.com/images/minseojo/post/e70fc65d-b98c-4724-bfe7-0aef723ac1a2/image.PNG)
- 더는 사용하지 않는 메모리를 자동으로 회수
- Heap Area에 생성(적재)된 객체 중 참조되지 않은 객체들을 탐색 후 제거.
- 실행 시간은 정확히 언제인지 알 수 없음
- GC를 수행하는 스레드를 제외한 나머지 스레드는 일시정지됨

    #### 동작 순서 - Mark And Sweep
    ![GC2](https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fuser-images.githubusercontent.com%2F51393021%2F132425744-523d4cf4-3b2f-4b52-8b48-9899477fe56b.png&blockId=c42936e2-82f4-451e-ab5b-33615a06e912)
    ![GCList](https://velog.velcdn.com/images/whitebear/post/bf4a9283-c91c-4171-b87d-1cb9ebbc6c26/image.png)
    1. GC가 Stack의 모든 변수를 스캔하며 어떤 객체를 참조하는지 Marking
    2. Reachable Object가 참고하고 있는 객체도 찾아서 Marking
        - Reachable Object(리스트 안에 객체, 스택 -> 힙(리스트) -> 힙(객체) 순서로 : 참조가 유효한 경우
        - 즉 unreachable, 참조되지 않는 객체를 GC라고 인식한다.
    3. Sweep 실행 :  마킹되지 않는 객체를  Heap에서 제거
    4. 경우에 따라 단편화 된 메모리 정리

### 런타임 데이터 영역
##### JVM의 메모리 영역으로 자바 애플리케이션을 실행 할 때 사용되는 데이터 적재 영역
---
![runtime](https://velog.velcdn.com/images/minseojo/post/1fa3cd1a-a0af-42fd-b4d3-93cc9f980602/image.png)
##### 모든 스레드가 공유해서 사용(GC의 대상)
 - ###### Heap
    - new 키워드로 생성된 객체와 배열이 생성되는 영역
    - 주기적으로 GC가 제거하는 영역
 - ###### Method
    - 클래스 멤버 변수의 이름, 데이터 타입, 접근 제어자 정보와 같은 각종 필드 정보와 메서드, 데이터 Type, Constant Pool, Static 변수, final class 등이 생성되는 영역
##### 스레드(Thread)마다 하나씩 생성
 - Stack Area : 지역변수, 파라미터, 리턴 값, 연산에 사용되는 임시 값등이 생성되는 영역
 - PC register : Thread가 생성될 때 마다 생성되는 영역, 현재 스레드가 실행되는 부분의 주소와 명령을 저장
 - Native Method Stack : 자바 외의 언어(C, C++, 어셈블리)로 작성된 네이티브 코드를 실행할 떄 사용되는 메모리 영역, 일반적인 C 스택
 -


#### 자료 출처
---
  [velog] : <https://velog.io/@minseojo/Java-%EC%9E%90%EB%B0%94-%EC%BB%B4%ED%8C%8C%EC%9D%BC-%EA%B3%BC%EC%A0%95-JVM-%EB%82%B4%EB%B6%80-%EA%B5%AC%EC%A1%B0>
  [blog]: <https://gyoogle.dev/blog> 

