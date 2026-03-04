# 상속과 합성
## 상속 (Inheritance): is-a관계 , A는 B다
- Dog is Animal
```java
class Animal {
    void eat() {}
}

class Dog extends Animal {
    void bark() {}
}
```
- 부모 변경 -> 자식 영향 
- 런타임에 구조 변경 어려움 

## 합성 (Composition): has-a관계, A는 B를 가진다 
- Car has an Engine
```java
interface Engine {
    void start();
}
class Bull implements Engine {
    void start(){
        System.out.println("황소처럼 빠른 엔진 기동중");
    }
}

class Car {
    private final Engine engine; 
    private Seat seat;
    Car(Engine engine){ //런타임에 교체 가능 
        this.engine = engine;
    }
    public changeSeat(Seat seat) {// 런타임에 교체 가능 
        this.seat = seat;
    }
}
```
- 필드로 포함, 느슨한 결합 
- 변경에 강함 

## 언제 상속 / 합성 사용
- 상속 : is-a관계가 명확할 떄, 부모가 변경 거의 없는 추상개념일 때
- 합성 : 기능 조합이 필요한 경우, 정책/전략 교체, 테스트 확장

## 자바 라이브러리에서 상속의 문제점 
### 예시1
> Java 라이브러리는 내부 구현 변경 가능성이 높아서 상속시 캡슐화가 깨진다. 대표적으로 ArrayList 상속시 addAll()이 add()를 호출하는 내부 구현에 의존한다.

- 목표 : add가 호출될 때마다 카운트 증가하게 하고 싶어!
```java
class MyList<E> extends ArrayList<E> {
    int addCount = 0;

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }
}
```
- 아래 코드는 어떻게 동작하지 ? 
    - addAll의 구현이 내부에서 add를 호출함을 알아야 addCount가 정상동작함을 보장할 수 있음
        - 만약 JDK가 add() 안 쓰고 내부 배열에 바로 복사하는 구현으로 바뀐다면 ? Fragile Base Class 문제 발생 : 컴파일 타임 oK,런타임버그 
```java
list.addAll(List.of("A", "B", "C"));
```


- 합성으로 바꾼다면?
    - addCount라는 책임만 내가 통제하면 됨 
```java
class MyList<E> implements List<E> {
    private final List<E> delegate;

    @Override
    public boolean add(E e) {
        addCount++;
        return delegate.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return delegate.addAll(c);
    }
}
```

### 예시2
```java
class MyThread extends Thread {
    @Override
    public void run() {
        // ...
    }
}
```
- Thread 생명주기와 강하게 결합됨
- ExecutorService로 교체 어려움

그래서 요즘 Runnable/Callable + 합성 사용 

### 대안 
#### 1) 인터페이스 + 합성
```java
class ForwardingList<E> implements List<E> {
    private final List<E> list;
}
```
#### 2) Decorator / Wrapper 패턴
```java
List<E> list = Collections.synchronizedList(new ArrayList<>());
```
#### 3) 상속 허용 시 명확한 규칙

- `@implSpec`
- 상속용 메서드 명시
- final 적극 사용

## 상속과 합성에 대한 생각 
상속은 상위 클래스의 책임과 변경 리스크까지 함께 받는 구조이다.
그래서 책임 관점에서는 상속보다 합성으로 책임을 분리하는 설계가 더 안전하다.
합성된 객체를 교체하는 방식으로 대응할 수 있다. 


# 인터페이스와 추상클래스 
- 인터페이스 : 역할을 표현, 계약 목적
- 추상클래스 : 공통 책임(기본 구현)을 표현, 구현 재사용하는 방식으로 책임의 일부를 공통화 목적
