package com.spantech.cyclicbarrier;

import java.util.concurrent.*;

/**
 *
 * Created by Panyaprach Tularak on Feb 28, 2019
 */
public class SturentTourism {

    private static CyclicBarrier gang = new CyclicBarrier(4);

    public static void main(String[] args) throws Exception{
        Student jenny = new Student("Jenny");
        Student yii = new Student("Yii");
        Student jax = new Student("Jax");
        Student marry = new Student("Marry");
        travel(jax).start();
        travel(jenny).start();
        travel(marry).start();
        travel(yii).start();
    }

    private static Thread travel(Student student) {
        Runnable travel = () -> {
            try {
                student.traveling();
                gang.await();
                System.out.println(student.getName() + " Says Hi");
            } catch (Exception e) {
            }
        };
        return new Thread(travel);
    }
}
