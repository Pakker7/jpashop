package jpabook.study.design.pattern;


public abstract class Duck {

    public Duck() {}

    public QuackBehavior quackBehavior; // 행동변수는 행동 인터페이스 형식으로 선언됩니다.
    public FlyBehavior flyBehavior; // 행동변수는 행동 인터페이스 형식으로 선언됩니다.

    public void performQuack() {
        quackBehavior.quack(); // 꽥꽥거리는 행동을 직접 처리하는 대신, quackBehavior로 참조되는 객체에 그 행동을 위임합니다.
        //꽥꽥거리는 행동을 하고 싶을 땐 quackBehavior에 의해 참조되는 객체에서 꽥꽥거리도록 하면 됩니다. 객체의 종류에는 전혀 신경 쓸 필요없이 quack()>을 실행할 줄만 알면 됩니다.
    }

    public void performFly() {
        flyBehavior.fly();
    }

    public void swim() {
        System.out.println("모든 물오리는 물에 뜹니다. 가짜 오리도 뜨죠");
    };

    public void display() {
        System.out.println("display");
    }

}
