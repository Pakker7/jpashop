package jpabook.jpashop.study;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("a","b","c");
        Stream<String> stream = list.stream();

        String[] array = new String[]{"a","b","c"};
        Stream<String> stream1 = Arrays.stream(array);
        Stream<String> stream2 = Arrays.stream(array,1,2);

        //stream1.forEach(System.out::println);
        //stream2.forEach(System.out::println);

        Stream.Builder<Object> stream3 = Stream.builder()
                .add("a")
                .add("b")
                .add("c");

        Stream<String> stream4 = Stream.generate(()->"HELLO").limit(5);
        //stream4.forEach(System.out::println);


        Stream<Integer> stream5 = Stream.iterate(100, n -> n+10).limit(10);
        stream5.forEach(System.out::println);

    }
}
