package org.example;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {
        try {
            AbstractProgram threadProgram = new AbstractProgram();
            Supervisor supervisor = new Supervisor(threadProgram);

            supervisor.start();

            supervisor.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}