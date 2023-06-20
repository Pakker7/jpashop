package jpabook.study.design.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {

        Duck mallard = new MallardDuck();
        mallard.performFly();
        mallard.performQuack();

        List<String> aa = Arrays.asList("a", "b", "c");

//        for( String aaa : aa ) {
//            System.out.println(aaa);
//        }

        aa.forEach(aaa -> System.out.println(aaa));

    }
}
