package jpabook.study.design.pattern;

public class Quack implements QuackBehavior{

    @Override
    public void quack() {
        System.out.println("꽥");
    }
}
