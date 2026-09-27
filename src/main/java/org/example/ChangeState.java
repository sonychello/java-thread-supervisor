package org.example;
import java.util.Random;

public class ChangeState extends Thread{
    int interval;
    AbstractProgram obj;



    public ChangeState(int interval, AbstractProgram obj){
        this.interval = interval;
        this.obj = obj;
    }

    @Override
    public void run() {
        Random random = new Random();
        AbstractProgram.State[] forRandom = {
                AbstractProgram.State.RUNNING,
                AbstractProgram.State.STOPPING,
                AbstractProgram.State.FATAL_ERROR
        };
        while(true){
            try {
                sleep(interval);
                synchronized (obj.program) {
                    obj.program.state = forRandom[random.nextInt(forRandom.length)];
                    System.out.println(obj.program.state);
                    obj.program.notify();
                    obj.program.wait();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
