package org.example;

import java.lang.Thread;

public class Supervisor extends Thread{
    AbstractProgram obj;

    public Supervisor(AbstractProgram obj) {this.obj = obj;}

    public void stopProgram() {
        obj.interrupt();
    }

    public void restart() {
        //program = new Program();
        obj.program.state = AbstractProgram.State.RUNNING;
    }

    @Override
    public void run() {

        obj.start();
        System.out.println(obj.program.state);
        while (true) {
            synchronized (obj.program) {

                try {
                    obj.program.wait();
                    switch (obj.program.state){
                        case STOPPING -> {
                            restart();
                            System.out.println("Supervisor's work: "+obj.program.state);
                        }
                        case FATAL_ERROR -> {
                            stopProgram();
                            System.out.println("Supervisor's work: program stopped");
                            obj.program.notify();
                            return;
                        }
                    }
                    obj.program.notify();

                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

            }

        }
    }
}
