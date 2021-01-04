# DI (Dependency Injection)

> 번역 : 종속성 주입 , 부품조립

## Dependency 개념

### 일체형

Composition has a

```java
  class A
  {
    private B b;    // B는 A의 종속되어 있다. B는 A의 부품이다.

    public A(){
      b = new B();   // 직접 만들어 끼우는 일체형 부품
    }
  }
```

사용자 입장

```java
  A a = new A();
```

이렇게 사용 가능
일체형이기 때문에 A만 만들면 B는 자동으로 생성되고 사용자 입장에서는 부품이 어떤게 들어있는지 알 수 없음.

### 조립형

Association has a

- 일체형에 비해 느슨한 결합

```java
  class A
  {
    private B b;   // B는 A의 종속되어 있다. B는 A의 부품이다.

    public A(B b){
      this.b = b;
    }

    public void setB(B b){    // 세팅해서 조립하여 사용하는 부품
      this.b = b;
    }
  }
```

사용자 입장

```java
  B b = new B();  // Dependency - 부품
  A a = new A();

  a.setB(b);  // Injection - 부품을 주입하는 것
```

- 장점 : 부품을 쉽게 바꿀 수 있다
- 단점 : 부품을 생성해서 조립해야 하는 번거로움

## Injection 개념

### Setter Injection

```java
  B b = new B();  // Dependency - 부품
  A a = new A();

  a.setB(b);  // Injection - 부품을 주입하는 것
```

### Construction Injection

```java
  B b = new B();  // Dependency - 부품
  A a = new A(b);  // Injection - 부품을 주입하는 것
```

## ▶ 스프링이 이러한 부품을 조립해 주는 역할을 해줌, DI의 역할을 해줌.
