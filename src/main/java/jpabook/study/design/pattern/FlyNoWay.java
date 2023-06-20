package jpabook.study.design.pattern;

public class FlyNoWay implements FlyBehavior{

    @Override
    public void fly() {
        System.out.println("안난다");
    }
}
