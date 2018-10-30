package com.hfonseca.java8;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/*
Testing some Stream methods and parallel stream
A stream cannot be re-used so you have to call the method stream() to pass through it again
 */
public class StreamCatalog {

    //some predicates to filter the stream
    final Predicate<Integer> predicateEven = n -> n.intValue() % 2 == 0;
    final Predicate<Integer> predicateOdd = n -> n.intValue() % 2 != 0;

    //service to filter Even values only
    StreamService evenService = (Stream<Integer> stream) -> {
        printHeader("Even");
        stream.filter(predicateEven).forEach(p -> printItem(p));
        printLine();
    };

    //service to filter Odd numbers only
    StreamService oddService = (Stream<Integer> stream) -> {
        printHeader("Odd");
        stream.filter(predicateOdd).forEach(p -> printItem(p));
        printLine();
    };

    //service to sort values
    StreamService sortedService = (Stream<Integer> stream) -> {
        printHeader("Sorted");
        stream.sorted().forEach(p -> printItem(p));
        printLine();
    };

    //service to show numeric data statistics
    StreamService summaryService = (Stream<Integer> stream) -> {
        printHeader("Summary");
        IntSummaryStatistics statistics =
        stream.collect(Collectors.summarizingInt(Integer::intValue));
        printItem(statistics.toString());
        printLine();
    };




    public static void main(String[] args) {

        StreamCatalog streamCatalog = new StreamCatalog();
        List<Integer> data = streamCatalog.getData();

        //sequential stream analysis
        DateTimeCatalog.ExecutionTime measure = DateTimeCatalog.measure();
        measure.start();
        streamCatalog.sequentialDataAnalysis(data);
        measure.printElapsedTime();

        //parallel stream analysis
        measure.reset();
        streamCatalog.parallelDataAnalysis(data);
        measure.printElapsedTime();
    }

    /*

     */
    public void sequentialDataAnalysis(List<Integer> data) {

        printHeader(" SEQUENTIAL ");

        //process even stream
        evenService.consumeData(data.stream());

        //process odd stream
        oddService.consumeData(data.stream());

        //process ordered
        sortedService.consumeData(data.stream());

        //summarise stream
        summaryService.consumeData(data.stream());

    }

    public void parallelDataAnalysis(List<Integer> data) {

        printHeader(" PARALLEL ");

        //process even stream
        evenService.consumeData(data.parallelStream());

        //process odd stream
        oddService.consumeData(data.parallelStream());

        //process ordered
        sortedService.consumeData(data.parallelStream());

        //summarise stream
        summaryService.consumeData(data.parallelStream());
    }

    /*
    How I was increasing the number of services to execute, the difference between sequential
    and parallel execution was getting shorter, and parallel becoming more effective
     */


    private List<Integer> getData() {
        final int lower = 0;
        final int upper = 1000;
        //using stream generate method to create a random list of numbers
        Stream<Integer> dataStream = Stream.generate(() -> (int) (Math.random() * (upper - lower)) + lower).limit(100);
        return dataStream.collect(Collectors.toList());
    }

    interface StreamService {
        void consumeData(Stream<Integer> stream);
    }

    static void printHeader(String header) {
        System.out.println("******* " + header + " *******");
    }

    static void printItem(Object item) {
        System.out.print(item + " ");
    }

    static void printLine() {
        System.out.println();
        System.out.println();
    }
}
