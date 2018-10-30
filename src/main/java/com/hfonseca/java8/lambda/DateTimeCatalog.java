package com.hfonseca.java8.lambda;

import java.time.Duration;
import java.time.Instant;

public class DateTimeCatalog {

    static DateTimeCatalog instance = new DateTimeCatalog();

    private DateTimeCatalog(){

    }

    public static DateTimeCatalog getInstance(){
        return instance;
    }

    public class ExecutionTime {
        private Instant start;
        private Instant end;

        public long elapsedTime() {
            return Duration.between(getStart(),getEnd()).toMillis();
        }

        public void start(){
            this.start = Instant.now();
        }

        public void stop(){
            this.end = Instant.now();
        }

        public void mark() {
            System.out.println(Instant.now());
        }

        public void printElapsedTime(){
            System.out.println("ELAPSED TIME: " + elapsedTime());
        }

        public void reset() {
            this.start();
        }

        private Instant getStart(){
            return this.start!=null?this.start:Instant.now();
        }
        private Instant getEnd(){
            return this.end!=null?this.end:Instant.now();
        }
    }

    static ExecutionTime measure(){
        return instance.new ExecutionTime();
    }
}
