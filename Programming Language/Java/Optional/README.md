# Optional

### Optional이란?
Java 8 버전에서 추가된 ```타입```으로 값의 유무를 표현한다. ```null``` 값을 대체할 수 있다.   
<img src="https://github.com/meoldae/Algorithm/assets/70866410/3ef28aeb-52f8-4f81-96d5-647405e37622"/>

### Optional 객체의 생성
- ```Optional.of()``` : ```null```가능성이 없는 값을 전달할 때 사용한다.
    ```java
    T t1 = new T();
    Optional<T> optExample1 = Optional.of(t1);

    T t2;
    Optional<T> optExample2 = Optional.of(t2); // NullPointerException 발생
    ```

- ```Optional.ofNullable()``` : ```null```가능성이 있는 값을 전달할 때 사용한다.
    ```java
    T t;
    Optional<T> optExample = Optional.ofNullable(t);
    ```

- ```Optional.empty()``` : 값이 없는(```null```) ```Optional``` 객체를 생성하고 싶을 때 사용한다.
    ```java
    Optional<T> emptyOpt1 = Optional.empty();

    T t;
    Optional<T> emptyOpt2 = Optional.ofNullable(t);
    ```

### Optional 객체의 값 획득
- ```get()``` : 가장 단순하게 Optional 객체의 값을 획득할 때 사용한다. 그러나 빈 Optional 객체에 접근하면 Exception이 발생한다.
    ```java
    Optional<String> neOpt = Optional.of("not empty");
    String neStr = neOpt.get();

    Optional<String> eOpt = Optional.empty();
    String eStr = eOpt.get(); // NoSuchElementException 발생
    ```
- ```isPresent(), isEmpty()``` : 값의 유무를 확인할 때 사용한다. ```isEmpty()```의 경우 Java11부터 추가된 함수이다.
    ```java
    Optional<String> neOpt = Optional.of("not Empty");
    neOpt.isPresent();  // true
    neOpt.isEmpty();    // false

    Optional<String> eOpt = Optional.empty();
    eOpt.isPresent();  // false
    eOpt.isEmpty();    // true
    ```
- ```ifPresent(), ifPresentOrElse()``` : Optional객체가 값이 있다면, 다음 행동을 지시하기 위해 사용한다.
    ```java
    Optional<String> opt = Optional.ofNullable("not empty");
    
    opt.ifPresent(value -> doNext(value));

    opt.ifPresentOrElse(
        value -> doNext(value),
        () -> doEmpty()
    );
    ```
    기존의 if문을 사용한다면?
    ```java
    String value = getString(); //  값을 받아오는 임의의 함수
    if (value != null) {
        doNext(value);
    } else {
        doEmpty();
    }
   ```
- ```orElse(), orElseGet(), or()``` : Optional객체에 값이 없을 때 다른 값을 사용하기 위해 사용한다.
    ```java
    String optValue1 = opt.orElse("default");

    String optValue2 = opt.orElseGet(() -> getDefaultValue());
    // String optValue2 = opt.orElseGet(() -> "defalut"); 값 반환도 가능
    ```
    ```or()```의 경우는 특이하게 Optional 객체를 반환한다.
    ```java
    Optional<String> optValue3 = opt.or(() -> Optional.of("defalut"));
    ```
- ```orElseThrow()``` : Optional객체에 값이 없다면 Exception, 값이 있으면 해당 값을 반환한다.
    ```java
    Optional<String> eOpt = Optional.empty();
    String optValue1 = eOpt.orElseThrow(() -> new MyException()); 

    Optional<String> neOpt = Optional.of("not Empty");
    String optValue2 = eOpt.orElseThrow(() -> new MyException());  // "not Empty"
    ```

### Optional객체의 중간연산
- map : 함수를 실행해서 값을 변환한 Optional 객체를 반환한다.
    ```java
    Optional<Member> memberOpt = getMember(); // 임의의 Member 객체 획득 함수
    Optional<String> memberBirth = memberOpt.map(mem -> mem.getBirth());
    Optional<Integer> memberAge = memberBirth.map(birth -> birth.getAge());

    Optional<Integer> memberAge = memberOpt.map(mem -> mem.getBirth())
                                            .map(birth -> birth.getAge());
    ```
- flatMap : ```map()```과 거의 동일하지만, 인자로 전달받은 함수의 반환값이 Optional일 때 사용한다.
    ```java
    Optional<Member> memberOpt = getMember(); // 임의의 Member 객체 획득 함수
    Optional<String> memberBirth = memberOpt.map(mem -> Optional.of(mem.getBirth()));
    ```
    만약 ```map()```을 사용했을 경우엔 아래와 같이 두 번 감싸진 Optional 객체를 반환한다.
    ```java
    Optional<Member> memberOpt = getMember(); // 임의의 Member 객체 획득 함수
    Optional<Optional<String>> memberBirth = memberOpt.map(mem -> Optional.of(mem.getBirth()));
    ```
> map과 flatMap 모두 빈 Optional 객체를 반환한다.

- filter : 조건이 참이면 해당 값을 반환하고 거짓이면 빈 Optional 객체를 반환한다.
    ```java
    Optional<Integer> intOpt = getValue(); // 임의의 정수 획득 함수
    Optional<Integer> filteredIntOpt = intOpt.filter(value -> value > 100)
                                              .ifPresent(value -> System.out.println(value));
    ```

### Optional객체의 조합
- 다음 예시와 같이 두 객체 모두 값이 있을때만 사용하고 싶다면 ?
    ```java
    // 기존 if 구문 사용
    Member member = getMember(); // 임의의 Member 객체 획득 함수
    if (member == null) return null;
    String grade = getGrade(member);
    if (grade == null) return null;
    T result = doSomething(member, grade);   
    return result;
    ```
    ```java
    // Optional map, flatMap 사용
    Optional<Member> memberOpt = getMember(); // 임의의 Member 객체 획득 함수
    Optional<T> result = memberOpt.flatMap(member -> {
        Optional<String> grade = getGrade(member);
        return grade.map(grade -> doSomething(member, grade));
    });
    ```
    ```memberOpt```가 값이 있을때만 ```flatMap```에 진입하고, 진입해도 ```grade```가 있을때만 ```doSomething()```이 실행된다.   

- 분기에 따라 특정 값만 사용하거나 둘 다 사용하고 싶다면?
    ```java
    // 기존 if 구문 사용
    String value1 = getStr1();
    String value2 = getStr2();

    if (value1 == null && value2 == null) return null;

    if (value1 == null) return value2;
    if (value2 == null) return value1;

    return value1.length > value2.length ? value1 : value2;
    ```
    ```java
    // Optional map, flatMap 사용
    Optional<String> opt1 = getValue1(); // 임의의 Optional 객체 획득 함수
    Optional<String> opt2 = getValue2(); // 임의의 Optional 객체 획득 함수

    Optional<String> result = opt1.flatMap(o1 -> { // opt1이 값이 있다면 진입
        return opt2.map(o2 -> {
            return o1.length > o2.length ? o1 : o2;
        }).orElse(o1); // opt2가 null이면 opt 1 사용
    }).or(() -> o2);   // flatMap 결과가 없다면 opt2 사용

    return result.orElse(null); // 둘 모두 없을 때 null 반환
    ```
### 정리
무작정 모든 ```if```구문을 Optional로 바꾸는 것은 옳지 않다. 상황에 맞춰서 사용해야 하며    
Optional을 사용하더라도 ```isPresent()```의 사용보다는 ```map, flatMap, filter, orElse, ifPresent```등의 사용을 지향하여 Optional 도입 의도에 맞게 사용하도록 하자!


### 참고자료
---
[[Youtube] 자바 Optional 기초 - 최범균](https://youtu.be/RsUTolCVm_E)