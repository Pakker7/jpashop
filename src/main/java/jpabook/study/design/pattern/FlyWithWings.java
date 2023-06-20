package jpabook.study.design.pattern;

public class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("난다");
    }
}
