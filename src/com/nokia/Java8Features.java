package com.nokia;

import java.security.Key;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Created by abexa on 3/15/2017.
 */
public class Java8Features {
    public static void main(String [] args) {
        //Java8Overview();
        //predicates();
        //consumers();
        //functions();
        //suppliers();

        biFunctionalInterfaces();
        //typedFunctionalInterfaces();

    }

    private static void typedFunctionalInterfaces() {
        LongPredicate longPredicate = it -> it > 0;
        longPredicate.test(15L);
    }

    private static void biFunctionalInterfaces() {
        BiPredicate<String, Integer> predicate = (first, second) -> first.length() > 0 && second > 0;

        BiConsumer<String, Integer> consumer = (x, y) ->System.out.println(x + " " + y);

       // BiFunction<Integer, String, Product> productBuilder = (price, name) -> new Product (10, name, price);

       // final Map<Integer, String> map = new HashMap<>();
       // map.put(1,"Jan");
       // map.out(2,"Feb")

        //map.forEach((key, value)) -> System.out.println(key + "->" + value));
        //map.computeIfAbsent(3, key -> "Mar");

        List<String> strings = new ArrayList<>(Arrays.asList("fun","interfaces"));
        strings.forEach(System.out::println);
        strings.removeIf(item -> item.length() < 5);

    }

    private static void suppliers() {
        Supplier<Integer> integerSupplier = () -> new Random(10).nextInt(10);
        System.out.println(integerSupplier.get());
    }

    private static void Java8Overview(){
        final List<String> strings = Arrays.asList("Java 8 rules!".split(" "));

        for(String string : strings){
            System.out.println(string);
        }
        strings.forEach(string -> System.out.println(string));
        strings.forEach(System.out::println);

        final Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            String element = iterator.next();
            if (element.length() < 5){
            iterator.remove();;
            }

        }
        strings.removeIf(string -> string.length() < 5);
        strings.removeIf(String::isEmpty);

        final Set<String> lessThan5Chars = strings.stream().filter(it -> it.length() < 5).collect(Collectors.toSet());

        final Set<String> emptyChars = strings.stream().filter(String::isEmpty).collect(Collectors.toSet());

        List<String> integers = Arrays.asList("1", "2", "5");

        Set<Integer> evenNumbers = integers.stream()
                            .filter(it -> Integer.parseInt(it) %2 == 0)
                            .map(Integer::parseInt)
                            .collect(Collectors.toSet());


    }
    private static void predicates(){
        Predicate<Integer> isEven = value ->value %2 == 0;
        Predicate<Integer> isBiggerThan100 = it -> it > 100;

        System.out.println(isEven.test(12));
        System.out.println(isEven.test(13));

        System.out.println(isEven.and(isBiggerThan100).test(140));
    }

    private static void inferredFunctionInterfaces(){
        Runnable runnable = () -> System.out.println("It works");
        runnable.run();;

        OurGunctionalInterface ourGunctionalInterface = String::length;
        System.out.println(ourGunctionalInterface.computeValue("something"));
    }

    private static void consumers(){
        Consumer<String> display = value -> System.out.println("The value is: " + value);
        display.accept("Java 8, FTW");
    }
    private static void functions(){
        Function<String, Integer> converter = Integer::parseInt;
        System.out.println(converter.apply("asd"));

    }


}