package com.nokia;

import com.sun.xml.internal.ws.resources.StreamingMessages;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by abexa on 3/16/2017.
 */
public class OptionalAndStreamsTests {

    public static void main(String[] args){
        //optional();
        //optionalSample();
        //streams();
        generatingStreams();
        parallelStreams();
        mapTest();

    }

    private static void mapTest() {
        final Map<Integer, String> months = new HashMap<>();
        months.put(1,"Jan");
        months.put(2,"Feb");

        months.keySet().stream();
        months.values().stream();
        months.entrySet().stream();

        months.forEach((key, value) -> System.out.println(key + value));
    }

    private static void parallelStreams() {
        final List<String> strings = Arrays.asList("Java 8 features and benefits".split(" "));

        strings.parallelStream().forEach(item -> System.out.println(Thread.currentThread().getName() + " -> " + item));
    }

    private static void generatingStreams() {
        Stream<Integer> zeroToTen = IntStream.rangeClosed(0,10).boxed();
        //zeroToTen.forEach(System.out::print);

        IntStream.rangeClosed(0,5).forEach(System.out::println);

        List<String> cleanDisplayedValues = zeroToTen.map(item -> item + " ").collect(Collectors.toList());
        System.out.println(cleanDisplayedValues);
    }

    private static void streams() {
        final List<String> strings = Arrays.asList("Java 8 features and benefits".split(" "));

        strings.forEach(System.out::println);

        final Set<String> upperCased = strings.stream().filter(item -> item.length() > 1)
                                                        .map(String::toUpperCase).collect(Collectors.toSet());
        System.out.println(upperCased);

        final List<Integer> values = Arrays.asList(4, 6, 10, 12, 15);
        final long sum = values.stream().reduce((first, second) -> first + second).orElse(0);

        final int anotherSum = values.stream().mapToInt(value -> value).sum();

        System.out.println(sum + ", " + anotherSum);

        List<String> someRandomWords = Arrays.asList("the class of the objects in the array list".split(" "));

        final String firstTwoLetterWord = someRandomWords.stream().filter(item -> item.length() ==2)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No two letter word found"));
        System.out.print(firstTwoLetterWord);

        final boolean anyThreeLettersWord = someRandomWords.stream().anyMatch(item -> item.length() == 3);
        System.out.println(anyThreeLettersWord);

        final Predicate<String> isThreeLettersLong =item -> item.length() == 3;
        final Set<String> allUniqueWordsWithRule = someRandomWords.stream().filter(isThreeLettersLong).collect(Collectors.toSet());

        final List<String> allWordsWithRule = someRandomWords.stream().filter(isThreeLettersLong).collect(Collectors.toList());

    }

    private static void optionalSample() {
        final String price = "10";
        final int priceValue = Optional.ofNullable(price).map(Integer::parseInt).orElse(0);
        System.out.println(priceValue);
    }

    private static void optional() {
        final String name = null;

        final Optional<String> optionalName = Optional.ofNullable(name);
        final String upperCased = optionalName.map(value -> value.toUpperCase())
                                                .orElseThrow(() -> new IllegalArgumentException("Null name"));
        System.out.println(upperCased);

        final String lowerCased = optionalName.map(String::toLowerCase).orElse("");

        System.out.println(lowerCased);

        optionalName.ifPresent(System.out::println);

        if (optionalName.isPresent()) {
            String value = optionalName.get();
        }
        String dontDoThis = optionalName.get();

        List<String> potentiallyNullstringList =new ArrayList<>();
        List<String> safeNonNullValues = potentiallyNullstringList.stream().filter(Objects::nonNull).collect(Collectors.toList());

        Optional<String> empty = Optional.empty();

        if (empty.isPresent()){
            //when there's a longer processing
        }

        empty.ifPresent(item -> System.out.println(item)); //for shorter processing
    }
}
