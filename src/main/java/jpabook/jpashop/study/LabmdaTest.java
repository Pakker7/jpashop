package jpabook.jpashop.study;

import java.util.Arrays;
import java.util.List;

public class LabmdaTest {

    //lambda 공부
    public static void main(String[] args) {

        //1.
        //Goods com = new Computer();
        //com.doSome();

        //1-1. 변형
        //Goods com = () -> System.out.println("do Operation!");
        //com.doSome();

        //2.
        //Calculator cal = (int num1, int num2) -> {return num1 + num2; };
        //System.out.println(cal.cal(1,2));

        //2-1.
        //Calculator cal = (num1, num2) -> {return num1 + num2; };
        //System.out.println(cal.cal(1,2));

        //2-2.
//            Calculator cal = () -> {
//                System.out.println("매개변수가 없는 경우");
//                return 1000; // 람다 표현식은 int 값을 반환해야 합니다. 적절한 반환 값을 추가해주세요.
//            };
//            cal.cal();


        // 2-3.
        //Calculator cal = (num1, num2) -> num1 + num2;
        //System.out.println(cal.cal(1,2));

        //2-4.
        //Calculator cal = (num1, num2) -> num1 + num2;
        //System.out.println(cal.cal(1,2));

        //2-5.
//        Calculator cal = num1 -> System.out.println(num1);
//        cal.cal(1);


        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // 람다 표현식을 사용한 정렬 및 출력
        names.sort((name1, name2) -> name1.compareTo(name2));
        names.forEach(name -> System.out.println(name));


    }

}



