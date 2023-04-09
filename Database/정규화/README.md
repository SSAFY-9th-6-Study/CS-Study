# 정규화

    데이터의 중복과 이상 현상을 최소화 하기 위해 정규형에 따라 Relation DB를 다시 구성하는 과정

- 정규형의 단계 : 1NF > 2NF > 3NF > BCNF ~~> 4NF > 5NF > 6NF~~
    - 1NF부터 순차적으로 재구성하며 앞선 정규형을 만족해야 다음 단계 정규형을 만족할 수 있다.
    - 1NF ~ BCNF는 [함수 종속성](#함수-종속)과 Key 만으로 정의된다. <br> 보통 (실무에서는 BCNF까지만 진행한다고 함)

### 이상 현상(Anomaly)
- 삽입(Insertion) 이상 : 어떤 정보를 저장하기 위해 불필요한 다른 정보가 필요하다.
<img src="https://user-images.githubusercontent.com/70866410/230763197-3c44a0dd-bfc0-44f0-9c1c-4cea8e550499.png"><br>
    - 제주 `캠퍼스` 추가를 위해 다른 `학생` 정보가 필요함
    
- 갱신(Update) 이상 : 반복되는 데이터 중 일부만 삭제하면 불일치가 발생한다.
<img src="https://user-images.githubusercontent.com/70866410/230763499-0d4b42e0-2bf1-4dcb-b324-76a144126662.png"><br>
    - 캠퍼스를 대전에서 유성으로 수정하려는데, 김디비 학생의 캠퍼스만 수정이 되었다.<br>전준영 학생과 김디비 학생의 캠퍼스 데이터가 불일치
    
- 삭제(Delete) 이상 : 어떤 정보를 삭제하기 위해 유용한 정보를 함께 삭제해야 한다.
<img src="https://user-images.githubusercontent.com/70866410/230763332-d6891f63-da22-4769-a984-2070df1757e5.png"><br>
    - 최백준 `학생`을 삭제하면 제주 `캠퍼스`에 대한 정보도 삭제됨

### 함수 종속
- 테이블에서 어떠한 속성 `X`에 따라 다른 속성 `Y`가 유일하게 결정될 때, 
    <br>　　`X`가 `Y`를 함수적으로 결정한다. 
    <br>　　`Y`가 `X`에 함수적으로 의존한다.<br>
라고 하며 이를 `함수 종속성 (Functional Dependency)` 이라고 한다. ( `X → Y` 와 `Y → X`는 필요 충분조건이 아니다! )
- 기호로는 `X → Y` 로 표시한다.
    - X : Left-hand Side
    - Y : Right-hand Side
    - `{} → Y` 로 표기하기도 하는데, 이는 해당 속성이 항상 단 하나의 값만을 가진다는 의미이다.
- 함수의 종속성은 단순히 테이블의 값으로 판별하는게 아니라, 스키마를 보고 판별해야 한다.
<img src="https://user-images.githubusercontent.com/70866410/230568988-413131ab-2b75-41a0-bc46-109a45d80c1b.png"><br>
    ❌ 동명이인이 등장할 수 있으므로 유일하게 결정되지 않는다.

- 예시
    - 테이블 정의

    <img src="https://user-images.githubusercontent.com/70866410/230568179-731c7f22-9abf-4140-b1db-b5764fc1f84e.png"><br>
    
    - Case 1

    <img src="https://user-images.githubusercontent.com/70866410/230570939-18faafb5-ac5c-4461-83cc-34e96b4a18c0.png"><br>
    ⭕ 학번에 따라 이름, 트랙, 반이 모두 결정될 수 있다.

    - Case 2
    
    <img src="https://user-images.githubusercontent.com/70866410/230571137-ac43f890-c759-4c1e-89f1-40f189081f2f.png"><br>
    ⭕ 학생 한명 당, 스터디를 단 하나만 가입해야 한다면 결정할 수 있다.<br>
    ❌ 학생 한명이 여러 스터디를 가입할 수 있다면, 결정할 수 없다.
    
    - Case 3

    <img src="https://user-images.githubusercontent.com/70866410/230571965-72b40ee9-0854-49e7-8faf-2160b4ee6551.png"><br>
    ⭕ ID 하나로만 결정할 수 없다! 여러번 시험을 보기 때문에 과목도 알아야 점수를 결정할 수 있다.<br>

- 종류
    - 자명한 함수 종속성 (Trivial FD) : X → Y 일 때, Y가 X의 부분집합이다.<br>
    `{a, b, c} → {c}, {a, b, c} → {a, b, c}`
    - 자명하지 않은 함수 종속성 (Non Trivial FD) : X → Y 일 때, Y가 X의 부분집합이 아니다.<br>
    `{a, b, c} → {c, d}`<br>
    `{a, b, c} → {d, e}` 의 경우 완전하게 자명하지 않은 함수 종속성 이라고 한다. (Completely ... )

    - 부분 함수 종속성 (Partial FD) : `X`의 진부분집합중 하나라도 `Y`를 결정할 수 있다.
    <img src="https://user-images.githubusercontent.com/70866410/230574792-24382b72-715d-491b-bb15-5155eacc4473.png"><br>
    ➡ `SSAFY_id` 만으로도 `track`을 결정할 수 있다.
    - 완전 함수 종속성 (Full FD) : `X`의 모든 진부분집합이 `Y`를 결정하지 못한다.
    <img src="https://user-images.githubusercontent.com/70866410/230575085-8c0cb3fd-5062-4775-bee6-65347d4f3351.png"><br>
    ➡ `SSAFY_id`, `subject` 만으로는 `score`를 결정할 수 없다.


### 제 1 정규화 
    모든 Attribute의 값은 반드시 나누어질 수 없는 단일의 값(원자값)이어야 한다.
- 예시<br><br>
    <img src="https://user-images.githubusercontent.com/70866410/230764892-0a228cf8-f791-497c-94d6-37bccda80ff7.png">
    - 만약 `CS 스터디` 가입자를 찾아야 한다면?<br> 
    ```
    WHERE study = 'CS' ❌
    WHERE study LIKE '%CS%' ⭕
    ```
- 제 1 `정규화`를 만족하는 제 1 `정규형` 테이블
    <img src="https://user-images.githubusercontent.com/70866410/230765115-26449a9c-ccec-46f4-a3cc-bd1b8455868a.png">

### 제 2 정규화 
    - 모든 Non-prime attribute는 모든 Key에 대해 완전 함수 종속(Full FD)을 만족해야 한다. 
    - 모든 Partial FD를 제거한 테이블
    - 쉽게 말하면 현재 테이블에 관련 없는 주제는 별도의 테이블로 분리하는 것!
- 예시<br><br>
    <img src="https://user-images.githubusercontent.com/70866410/230765717-f90c1a37-2696-4884-9d9f-03920df5fec3.png">
    - 만약 Query DSL 강의의 가격이 79,000 원이 되었다면 ?<br>
      프로그램이 Query DSL인 행을 모두 찾아 79,000원으로 수정해야 한다!<br>
    - 이 테이블에서 Primary KEY는 `{회원 번호, 프로그램}` 이다.<br>
      정확하게 이야기하면 `Composite Primary KEY` 이고 테이블에 PK 역할의 KEY가 없을 경우 여러 열을 합쳐서 PK 역할을 할 수 있을 때 `Composite PK`라고 칭한다.
    - 위의 테이블에서 `가격` 속성은 `프로그램`에 의해 결정될 수 있다. 즉 PK에 대해 부분적으로 함수 종속성인 셈!<br>
      ➡ 따라서 별도의 테이블을 만들어 부분 함수 종속성을 제거해버린다.
    
    
- 제 2 `정규화`를 만족하는 제 2 `정규형` 테이블
    <img src="https://user-images.githubusercontent.com/70866410/230765838-c8120e98-57a6-462a-9469-41c17868792c.png">
    - 단, 데이터 조회 시 테이블을 두개 사용해야 한다.