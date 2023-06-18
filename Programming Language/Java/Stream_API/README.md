# Stream

### Stream이란?
Java에서 다양한 데이터 소스(컬렉션, 배열)을 표준화된 방법으로 다루기 위한 방법
```java
map.put("key", "value");
list.add("value");
set.add("value");
```
Stream으로 만들고, 중간 연산을 실행하고 최종 연산으로 마무리된다.

### Stream 생성(변환)

Stream 생성에는 여러 방법이 있다.

```java
String<Integer> intStream = list.stream(); // Collection -> Stream
Stream<Integer> intStream = Stream.of(new int[]{1, 2, 3, 4, 5}); // Array -> Stream
Stream<Integer> intStream = Stream.iterate(0, n->n+2);
Stream<Double> doubleStream = Stream.generate(Math::random) // 람다식 지원
IntStream intStream = new Random().ints(size); 
IntStream intStream = IntStream.range(n, m); // n ~ m-1까지의 int Stream
```
- 중요한 점은 원본 데이터를 ```복사```하여 Stream으로 변환하는것이기때문에 기존의 데이터는 변하지 않는다.
- Stream은 일회용이므로 필요하면 재생성해야한다. (최종연산 이후)
- 최종 연산이 수행되기 전까지 중간연산이 수행되지 않는다.

### 중간연산
- 연산 결과가 Stream인 연산
- 계속해서 반복적으로 N번 사용할 수 있다.
- 종류
    - ```distinct()``` : 중복된 요소를 제거한다.
    - ```filter()``` : 조건에 맞지 않는 요소를 제거한다.
    - ```limit()``` : 지정된 갯수 이외의 요소를 제외한다.
    - ```skip()``` : ```limit()```와 반대로 지정된 갯수만큼 건너뛴다.
    - ```peek()``` : 요소에 작업을 수행한다. (≒ ```forEach()```)
    - ```sorted()``` : Stream을 정렬한다.
    - ```map()``` : Stream의 요소가 객체일 때 원하는 필드만 뽑아내거나, 원하는 형태로 변환한다.
        - ```mapToInt(), mapToLong(), mapToDouble()```등 다양한 하위 메서드가 존재
        - 기본형 Stream을 객체로 바꾸기 위한 ```mapToObject()``` 메서드도 존재한다.
    - ```flatMap()``` : 여러개의 Stream 혹은 데이터를 하나의 Stream으로 반환한다.
        ```java
        Stream<String[]> stream1 = Stream.of(
            new String[]{"hello", "java", "world"},
            new String[]{"hong", "gildong"});

        stream1.flatMap(Arrays::stream).forEach(System.out::println);

        Stream<String[]> stream2 = Stream.of(
            new String[]{"hello", "java", "world"},
            new String[]{"hong", "gildong"});

        stream2.map(Arrays::stream).forEach(System.out::println);
        ```
        - 출력 결과 <br>![image](https://github.com/SSAFY-9th-6-Study/CS-Study/assets/70866410/124ad0a6-d7cc-4e92-8d37-78b4545dd294)


### 최종연산
연산의 결과가 Stream이 아니며 Stream의 요소를 소모해 ```단 한번```만 실행할 수 있다.
- ```forEach()``` : 요소를 순회하며 작업을 수행
- ```match()```  : Stream의 요소들이 조건을 충족하는지 검사하는 연산
    - ```allMatch()``` : 모든 요소들이 주어진 조건을 만족하는지 검사
    - ```anyMatch()``` : 단 하나의 요소라도 주어진 조건을 만족하는지 검사
    - ```noneMatch()``` : 모든 요소가 주어진 조건에 불만족하는지 검사
- 집계 관련 연산
    - ```sum()``` : 요소의 합을 계산
    - ```count()``` : 요소의 개수를 계산
    - ```average()``` : 요소의 평균을 계산
    - ```max()``` : 요소들 중 최댓값을 반환
    - ```min()``` : 요소들 중 최솟값을 반환
- 하나의 요소 반환
    - ```findAny()``` : Stream의 요소 중 아무거나 하나 반환
    - ```findFirst()``` : Stream의 요소 중 첫 번째 요소 반환
- ```toArray()``` : 배열로 만들어 반환
- ```collect()``` : Stream의 요소를 수집
    - ```Collectors.toList()``` : Stream의 결과를 List로 만들어 반환
        ```java
        List<T> list = stream.collect(Collectors.toList());
        ```
    - ```Collectors.toMap()``` : Stream의 결과를 Map으로 변환
        ```java
        Stream<String> strStream = Stream.of("1", "2", "3", "4", "5");
        Map<K, V> map = strStream.collect(Collectors.toMap(i -> Integer.parseInt(i), i));
        // 람다식 가능
        ```
    - ```Collectors.toCollection(lambda)``` : 위 2개 이외의 Collection으로 변환
        ```java
        ArrayList<T> arrayList = stream.collect(Collectors.
        toCollection(() -> new ArrayList<>()));

        Set<T> set = stream.collect(Collectors.toCollection(HashSet::new));
        ```
- ```reduce(identity, accumulator(필수), combiner)``` : 요소를 하나씩 줄여나가면서 연산을 수행하고 최종 결과 반환
    - ```identity``` : 초기 값으로 Stream이 텅 비어있더라도 초기값을 반환할 수 있음
    - ```accumulator``` : 각 요소를 연산하기 위한 로직 - 2개의 파라미터를 받아 하나의 결과를 반환하는 ```BinaryOperator```이어야 한다.
    - ```combiner``` : Stream이 병렬일경우 각 쓰레드의 결과를 합치는 함수
    ```java
    Stream<Integer> stream = Stream.of(new int[]{1, 2, 3, 4, 5});
    stream.reduce((x, y) -> x + y); // 25
    ```

### Etc..
- ```parallel()```을 통해 Stream의 작업을 병렬로 처리할 수 있다.
- 기본형 스트림 (IntStream, LongStream, DoubleStream)
    - 기존에 일반 정수배열을 Stream으로 만들기 위해서는 ```Integer``` 클래스로 오토박싱을 거치고 중간에 계산할 때는 다시 ```int```로 언박싱 되는 과정을 거쳐야 했다.   
    이런 오버헤드를 피하기 위해 등장한 개념으로 Stream보다 더 많은 메서드를 제공한다.
- Stream을 사용할 경우 가독성은 좋지만 일반적으로 for-loop 보다는 속도가 매우 느리다. 이 점에 유의하며 사용하자.   
 [Stream은 왜 for-loop 보다 느린가?](https://sigridjin.medium.com/java-stream-api%EB%8A%94-%EC%99%9C-for-loop%EB%B3%B4%EB%8B%A4-%EB%8A%90%EB%A6%B4%EA%B9%8C-50dec4b9974b) 


### 참고자료
---
[[자바의 정석 - 기초편] ch14-15,16 스트림, 스트림의 특징](https://youtu.be/7Kyf4mMjbTQ)