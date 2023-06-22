# 메모리 관리(3)

### 2023.06 4주차

### Swapping

- Swap-out : 프로세스 할당이 끝나고 수행 완료된 프로세스를 swap-device(보조 기억 장치)로 보내는 작업
- Swap-in : 새롭게 시작하는 프로세스를 메모리에 적재하는 작업

![ScreenCapture.png](img/ScreenCapture.png)

## Non-continuous Memory Allocation (비연속 할당)

: `하나의 사용자 프로그램`을 여러 개의 block으로 분할하고, 실행 시 필요한 block들만 메모리에 적재하는 정책

- 나머지 block들은 swap device에 존재

→ 지금까지는 하나의 프로세스를 통째로 메모리에 올려서 사용했다면, 하나의 프로세스를 분할한 block 중 일부만 올려놓고 사용 가능하다

![ScreenCapture.png](img/ScreenCapture%201.png)

### Address Mapping

: virtual address를 real address로 변환해주는 작업

- Virtual address (가상 주소) = relative address = logical address (논리 주소)

  - 연속된 메모리 할당을 가정한 주소

- Real address (실제 주소) = absolute (physical) address
  - 실제 메모리에 적재된 주소

![ScreenCapture.png](img/ScreenCapture%202.png)

- 프로세스는 실행 프로그램 전체가 메모리에 연속적으로 적재되었다고 가정하고 실행 할 수 있음

### Block Mapping

: 사용자 프로그램을 block 단위로 분할/관리

(각 block에 대한 address mappnig 정보 유지)

- Virtual address : v = (b, d)
  - b : block number
  - d : displacement(offset) in a block (block에서 얼마나 떨어져 있는지)

![ScreenCapture.png](img/ScreenCapture%203.png)

- Block Map Table (BMT)

  - Address mapping 정보 관리
  - Kernel 공간에 프로세스마다 하나의 BMT를 가짐
  - residence bit : 해당 블록이 메모리에 적재되었는지 여부 (0/1)

  ![ScreenCapture.png](img/ScreenCapture%204.png)

- Block Mapping 과정
  가상 메모리 v = (b, d) 요청
  1. 프로세스의 BMT에 접근
  2. BMT에서 block b에 대한 항목(Entry)를 찾음
  3. Residence bit 검사

     3.1 Residence bit = 0인 경우

     - swap device에서 해당 block을 메모리에 가져옴
     - BMT 업데이트 후 3.2 실행

       3.2 Residence bit = 1인 경우

     - BMT에서 b에 대한 real address 값 a 확인

  4. 실제 주소 r 계산 (r = a + d)
  5. r을 이용하여 메모리에 접근

![ScreenCapture.png](img/ScreenCapture%205.png)

## 출처

[운영체제 강의 모음 (슬라이드 & YouTube 링크)](https://hpclab.tistory.com/1?category=887083)

[[운영체제]  메모리 분할 ( Memory Partioning ) [ 비연속 메모리 할당]](https://lordofkangs.tistory.com/211)
