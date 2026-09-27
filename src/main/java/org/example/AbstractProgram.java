package org.example;

public class AbstractProgram extends Thread{
    enum State {UNKNOWN, STOPPING, RUNNING, FATAL_ERROR};
    public class Program {
        State state = State.UNKNOWN;

    }

    final Program program;

    public AbstractProgram(){program = new Program();}

    @Override
    public void run() {
        program.state = State.RUNNING;
        ChangeState changeState = new ChangeState(500, this);
        changeState.setDaemon(true);
        changeState.start();
        while (!this.isInterrupted()) {

        }

    }

}
