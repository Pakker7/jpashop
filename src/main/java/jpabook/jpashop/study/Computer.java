package jpabook.jpashop.study;

public class Computer implements Goods{

    @Override
    public void doSome() {
        System.out.println("do Operation");
    }
}
