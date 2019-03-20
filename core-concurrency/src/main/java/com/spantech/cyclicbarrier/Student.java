package com.spantech.cyclicbarrier;

import java.util.Random;

/**
 *
 * Created by Panyaprach Tularak on Feb 28, 2019
 */
public class Student {

    private int travelMinutes = new Random()
            .ints(1, 30)
            .findFirst()
            .getAsInt();
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void traveling() {
        for (int i = travelMinutes; i > 0; i--) {
            System.out.println("On the road " + Thread.currentThread().getId() + " " + name + " arrive in " + i + " minutes");
        }
        System.out.println(name + " arrived");
    }

}
