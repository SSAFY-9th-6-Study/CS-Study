# GIT Flow

## 개요

---

> Git을 사용함에 있어 약속인 방법론
> 

## GIt Repository 구성 살펴보기

---

### 들어가기에 앞서

- **Upstream Repository :** 
개발자들이 공유하는 저장소로 최신 소스코드가 저장되어 있는 원격 저장소
- **Origin Repository :** 
Upstream Repo를 [Fork](https://www.notion.so/GIT-Flow-e885e6b8cebc41ab8927c99154f5d24a)한 원격 개인 저장소
- **Local Repository :** 
Local Repo는 내 컴퓨터에 저장되어 있는 저장소

![https://techblog.woowahan.com/wp-content/uploads/img/2017-10-30/github-flow_repository_structure.png](https://techblog.woowahan.com/wp-content/uploads/img/2017-10-30/github-flow_repository_structure.png)

1. Local에서 작업 처리 → origin으로 push
2. Origin에서 pull request → 코드리뷰 후 Upstream과 merge
3. 새로운 작업 시작 시, Local에서 pull

## 작업 시 지켜야할 약속

---

1. 작업을 시작할 때 JIRA 티켓을 생성 - Log 작성
2. 1 커밋 1 티켓
3. 커밋 그래프는 최대한 단순하게
4. 서로에게 공유하는 브랜치 및 커밋 그래프는 변경하지 않게 신중하게 작성
5. 리뷰어에게 반드시 리뷰 받기
6. 자신의 Pull request는 스스로 Merge하기(Origin에서 Upstream으로)

## Git-flow 전략

---

**Git Flow**는  총 5개의 브랜치를 사용해서 운용한다,

- `master` : 기준이 되는 브랜치로 제품을 배포하는 브랜치
- `develop` : 개발 브랜치로 개발자들이 이 브랜치를 기준으로 각자 작업한 기능들을 합(`Merge`)칩니다.
- `feature` : 단위 기능을 개발하는 브랜치로 기능 개발이 완료되면 develop 브랜치에 합칩니다.
- `release` : 배포를 위해 master 브랜치로 보내기 전에 먼저 QA(품질검사)를 위한 브랜치
- `hotfix` : master 브랜치로 배포를 했는데 버그 발생 시 긴급 수정하는 브랜치

> `master` 와 `develop` 이 **메인 브랜치**이고 나머지는 필요에 의해서 운영되는 브랜치
> 

## Git Flow 흐름도

---

![Untitled](GIT%20Flow%20e885e6b8cebc41ab8927c99154f5d24a/Untitled.png)

1. `master` 브랜치에서 시작
2. 동일한 브랜치를 `develop`에도 생성한다. 개발자들은 `develop`브랜치에서 개발 진행
3. 개발을 진행하다 회원가입, 장바구니 등의 기능 구현이 개발자는 develop 브랜치에서 `feature` 브랜치 생성
→ 개발자 A 장바구니 기능을 구현하기로했음 : `feature/장바구니` 브랜치 생성
→ 개발자 B 회원가입 기능을 구현하기로했음 : `feature/회원가입` 브랜치 생성
4. 기능 구현이 완료된 브랜치는 검토를 거쳐 `develop` 브랜치에 병합(Merge)
5. 요구사항의 기능이 모두 구현된 경우 develop브랜치를 `release/v버전` 브랜치로 만든다.
→ 이후 QA를 하며 버그발견
→ 버그 발견시 `release` 브랜치에서 버그 수정
6. 완료되면 `release` 브랜치를 `master/v버전` 와 `develop/v버전` 브랜치로 보낸다. `master` 브랜치에서 버전 추가를 위해 태그를 하나 생성하고 배포를 합니다.
7. 배포 후 버그 발생 시 `hotfixes` 브랜치를 만들어 긴급 수정 후 배포(6번과정 반복)

# GitHub Flow

---

- Git Flow에 `feature`, `hotfixes` 브랜치가 사라지고 **자동화 개념**이 들어가있음
- 흐름이 단순 한 만큼 `master`에 대한 role을 엄격히 관리해야한다.
- `pull request` 기능 사용을 권장

## 특징

---

- GitHub 특성상 `release` 를 필요로 하지 않음
    - public된 repository 접근해서 얼마든지 사용할 수 있으므로

## 어떻게 사용할 것인가?

---

### 1. `master` 브랜치를 통해 어떤 때든 배포 가능

- stable 상태로 배포하는 만큼 엄격한 관리 필요

### 2. 새로운 작업을 위해 브런치를 `master` 에서 딸 경우 이름을 명확히

- `feature`이나 `develop` 이 없는 만큼 기능 추가, 버그 해결을 위한 브랜치명을 자세하게, 어떤 일을 하고 있는지 작성하자

### 3. 원격 브랜치로 수시로 push

### 4. 피드백이나 도움이 필요할 때, 머징 완료된 상태일 때만 `pull request` 생성

- 코드리뷰를 도와주는 시스템인 만큼 코드 공유 후 리뷰 받기. merge 완료되면 `master` 로 반영하기

### 6. `master` 로 병합된 후 push → 즉시 배포

- **GitHub Flow**의 핵심인 만큼 `hubot` 을 이용해 자동으로 배포가 되게 설정하기

## 부록

---

- `Fork` : 다른 사람의 Repo에서 내가 어떤 부분을 수정하거나 추가 기능을 넣고 싶을 때 나의 Repo로 복제하는 기능
    - `Fork` 한 저장소는 원본과 연결되어 있기에 commit시 forked된 Repo에 반영된다.
    → `Fetch` 나 `rebase` 를 통해 해결하자
    
    ![Untitled](GIT%20Flow%20e885e6b8cebc41ab8927c99154f5d24a/Untitled%201.png)
    
- `Clone` : 특정 Repo를 내 Local 저장소에 복사해 새로운 저장소를 만드는 것
    - `clone` 한 원본 Repo를 remote 저장소 `**origin`** 으로 가지고 있다. 권한이 없으면 push 불가
    
    > 소규모 팀 활용 예시
    > 
    1. A와 B가 팀 프로젝트 적용하기 위해 새로운 Repo를 만들, 각자 컴퓨터에 Clone해 작업 시작.
    2. 변경사항을 commit하고 B가 먼저 github remote에 저장소를 push
    3. 이후 A가 push → conplict 충돌 발생
        
        3-1. 해결하기 위해서는 A의 로컬에 적용하기 위해 B가 수정한 커밋을 Fetch하고 Merge해야한다.
        

## Flow를 통한 협업하기

---

### 팀장

- 새로운 Repository 만들기
- 코드 리뷰, 팀원 Pull Request 체크

### 팀원

- 팀장 Repository에서 fork 하기
- Issue 등록하기
- Develop 브랜치에서 코드 작성하기
- 팀장 Repository로 보내기

## Versioning 규칙 - v${메이저}.${마이너}.${핫픽스}

### 참고

---

- [https://ujuc.github.io/2015/12/16/git-flow-github-flow-gitlab-flow/](https://ujuc.github.io/2015/12/16/git-flow-github-flow-gitlab-flow/)
- [https://velog.io/@sweet_pumpkin/Megabyte-School-Git을-활용한-버전관리3-Git-flow-알아보기](https://velog.io/@sweet_pumpkin/Megabyte-School-Git%EC%9D%84-%ED%99%9C%EC%9A%A9%ED%95%9C-%EB%B2%84%EC%A0%84%EA%B4%80%EB%A6%AC3-Git-flow-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0)
- [https://shinsunyoung.tistory.com/127](https://shinsunyoung.tistory.com/127)
- [https://ux.stories.pe.kr/183](https://ux.stories.pe.kr/183)
