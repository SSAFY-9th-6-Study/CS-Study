# 컴퓨터 기초 구조

<img height="450px" align="center" src="https://user-images.githubusercontent.com/70866410/229150830-6aefdb0e-cc83-4ac3-922b-2d04a5878e92.jpg">

<br>

## <b>소프트웨어</b>
<img height="300px" align="right" src="https://user-images.githubusercontent.com/70866410/229504802-58f95a51-d1a9-4f13-9e07-437dcd54360b.png">

- ### 시스템 소프트웨어
    - 하드웨어와 소프트웨어를 관리 및 제어하는 프로그램
      ex) OS, 컴파일러, 펌웨어 등..
    - 시스템 리소스에 접근이 가능하다.
    - 어셈블리어처럼 매우 Low-level의 언어로 작성 및 제어된다. 
- ### 응용 소프트웨어
    - 넓은 의미 : 운영체제 위에서 실행되는 모든 소프트웨어
    - 좁은 의미 : 사용자가 원하는 기능을 수행하는 소프트웨어 
     ex) 게임, 메모장, 문서작성 프로그램 등..
    - 시스템 리소스에 직접 접근이 불가하다. 
    - High-Level 언어로 작성 및 제어된다. (C, Java, Python 등..)
- ### 소프트웨어 실행 구조
    <img height="300px" align="center" src="https://user-images.githubusercontent.com/70866410/229505780-3e9bff2e-ac9e-4598-b64f-43232ed37796.png"><br>

    - 전처리 → 컴파일(어셈블리어) → 어셈블러(기계어) → 링커 → 로더
    - 전처리(Pre-Processing) : 주석제거, 헤더 파일 적용, 전처리 내용 치환(#define)
    - 컴파일(Compile) : 고수준 언어를 어셈블리어로 번역(변환)하는 작업
    - 어셈블리(Assembly) : 어셈블리어를 기계가 이해할 수 있는 기계어로 번역
    - 링커(Linker) : 파일이 참조할 함수, 라이브러리 등을 모아 하나의 실행파일을 생성
    - 로더(Loader) : 프로그램이 실행될 수 있도록 메인 메모리에 적재


---
## <b>하드웨어</b>
    PC의 하드웨어는 크게 중앙처리장치(CPU), 기억장치, 입출력장치(I/O)로 나뉘며 버스(Bus)를 통해 정보를 교환한다. 이를 '폰노이만 구조'라고 한다.
    * 폰 노이만 구조에서 모든 프로그램은 메모리에 적재되어야 실행할 수 있다.
- ### 중앙 처리 장치 (CPU)
    - CU (Control Unit) : 입력된 명령어를 해독하여 CPU 내 움직임을 관리한다.
    - ALU (Arithmetic Logical Unit) : 두 숫자의 산술연산과 논리연산을 계산하는 장치이다.
    - 레지스터 (Register) : CPU 내부의 저장장치로 매우 빠른 속도로 정보를 제공한다. 아래는 대표적인 몇 개의 레지스터이며 많은 종류가 존재한다.

        - PC (Program Counter) : 메모리에 있는 명령어 주소를 저장
        - AC (Accumulator) : ALU의 연산 결과를 임시로 저장한다.
        - IR (Instruction Register) : 현재 실행되는 명령어를 기억한다.
        - [더 보기..](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=techref&logNo=222249216316)
- ### 기억장치
    - 주 기억장치
        - 실행중인 프로세스와 필요한 데이터들을 '일시적'으로 저장한다. 
        - 속도가 빠르다.
        - 바이트(Byte) 단위로 구분되며 할당된 공간을 '주소'로 관리한다.
    - 보조 기억장치
        - 휘발성인 주 기억장치와 달리 반영구적으로 데이터를 저장한다.
        - 주 기억장치에 비해 속도가 느리다.
- ### 입/출력 장치
    - 입력 장치 : 컴퓨터 외부에 있는 데이터를 컴퓨터에 입력하는 장치<br> 
    ex) 키보드, 마우스, 마이크 등..
    - 출력 장치 : 컴퓨터의 처리 결과를 나타내주는 장치<br>
    ex) 모니터, 스피커 등..

- ### 시스템 버스 (System Bus)
    <img height="450px" align="center" src="https://user-images.githubusercontent.com/70866410/229502945-7fbb9f22-07d0-4695-9d00-8d55d45b2c4a.png"><br>

    - 컴퓨터의 각 구성요소를 연결하고 데이터의 교환을 위한 통로
    - 제어 버스(Control Bus)
        - 제어 신호(Read, Write)가 전달된다. 
        - CPU, 메모리, 입출력장치 모두 양방향으로 전달이 가능하다.
    - 주소 버스(Address Bus)
        - 메모리의 주소나 입출력 장치의 포트번호를 전달한다.
        - CPU ↔ 메모리 사이에서는 CPU에서 메모리로 단방향 통신만 가능하다.
        - CPU와 입출력 장치, 메모리와 입출력 장치는 각각 양방향 통신이 가능하다.
    - 데이터 버스(Data Bus)
        - 데이터를 전달한다.
        - CPU, 메모리, 입출력장치 모두 양방향으로 전달이 가능하다.



---

### 참고자료 
- [컴퓨터의 구성](https://github.com/gyoogle/tech-interview-for-developer/blob/master/Computer%20Science/Computer%20Architecture/%EC%BB%B4%ED%93%A8%ED%84%B0%EC%9D%98%20%EA%B5%AC%EC%84%B1.md)
- [컴퓨터 기본 구조](https://gamedevlog.tistory.com/65)
- [소프트웨어 실행 구조](https://blog.naver.com/kore2758_/221379127602)