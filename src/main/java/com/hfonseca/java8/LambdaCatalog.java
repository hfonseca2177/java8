package com.hfonseca.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaCatalog {

    /*
    Lambda is a FunctionalInterface type object that gives an optional new way to write anonymous class easier to read and write
     */


    public static void main(String[] args) {

        //Regular anonymous class
        Action newAction = new Action() {
            public void doSomething(String actionDescription) {
                System.out.println(actionDescription);
            }
        };

        newAction.doSomething("My action");

        //using lambda to do same interface use
        Action lambdaAction = (String actionDescription) -> System.out.println(actionDescription);

        lambdaAction.doSomething("My lambda action");

        //also we can resume more the syntax called  **Method Reference**
        Action referenceLambda = System.out::println;
        referenceLambda.doSomething("Method Reference lambda");

        //we can work with collections using for each
        List<String> stringList = Arrays.asList("A", "B", "C");
        Consumer<String> consumer = System.out::println;
        stringList.forEach(consumer);
    }

    //My Interface for the anonymous class
    public interface Action {
        void doSomething(String actionDescription);
    }


}
