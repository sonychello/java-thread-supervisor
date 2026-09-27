# Java Thread Supervisor

A Java project demonstrating **multithreading, thread synchronization, and the `wait()` / `notify()` mechanisms**.

The project implements a supervisor that controls the execution of an abstract program running in a separate thread.

## Task

The abstract program has a state represented by the following enumeration:

* `UNKNOWN` — the initial state before the first launch;
* `STOPPING` — the program is stopped;
* `RUNNING` — the program is running;
* `FATAL_ERROR` — a critical error has occurred.

The abstract program runs in a separate thread and has an additional daemon thread that periodically changes its state to a randomly selected state.

The supervisor monitors the abstract program and reacts to its state changes.

## Project Structure

The project consists of four main classes:

* `Main` — creates the abstract program and supervisor and starts the supervisor;
* `AbstractProgram` — represents the controlled program and contains its current state;
* `ChangeState` — a daemon thread that periodically changes the program state;
* `Supervisor` — monitors the program and controls its execution.

## Abstract Program

The `AbstractProgram` class extends `Thread` and contains the `Program` object with its current state.

When the program starts, its state changes from `UNKNOWN` to `RUNNING`.

A separate daemon thread (`ChangeState`) is then started. It periodically changes the program state to one of the following values:

```text
RUNNING
STOPPING
FATAL_ERROR
```

The state is selected randomly.

## Supervisor

The `Supervisor` class also extends `Thread` and controls the `AbstractProgram`.

The supervisor:

* starts the abstract program;
* waits for state changes;
* restarts the program when its state becomes `STOPPING`;
* stops the program when its state becomes `FATAL_ERROR`.

When a fatal error occurs, the supervisor interrupts the abstract program and terminates its own execution.

## Thread Synchronization

The project uses the Java monitor mechanism together with:

* `synchronized`;
* `wait()`;
* `notify()`.

The `ChangeState` thread synchronizes on the shared `Program` object, changes its state, notifies the supervisor, and waits for further interaction.

The `Supervisor` synchronizes on the same object and waits for notifications from the state-changing thread.

This demonstrates communication between threads through a shared object and Java monitor methods.

## Daemon Thread

`ChangeState` is configured as a daemon thread:

```java
changeState.setDaemon(true);
```

It periodically changes the state of the abstract program while the main program is running.

## Program Workflow

1. `Main` creates an `AbstractProgram`.
2. `Main` creates a `Supervisor` for the program.
3. The supervisor starts.
4. The supervisor starts the abstract program.
5. The abstract program changes its initial state to `RUNNING`.
6. A daemon `ChangeState` thread starts.
7. `ChangeState` periodically selects a random state.
8. The supervisor receives the state change and reacts to it:

   * `STOPPING` → the program is restarted;
   * `FATAL_ERROR` → the program is interrupted and the supervisor finishes its work.
9. State changes and supervisor actions are displayed in the console.

## Technologies

* Java
* Multithreading
* `Thread`
* Daemon threads
* `synchronized`
* `wait()` / `notify()`
* Enumerations
* Thread interruption

## Example Console Output

The exact output is random because the state is selected randomly by the `ChangeState` thread.

Example:

```text
RUNNING
STOPPING
Supervisor's work: RUNNING
FATAL_ERROR
Supervisor's work: program stopped
```

## Author

**Sofya Pozneeva**

Saint Petersburg Polytechnic University
Institute of Computer Science and Cybersecurity
Higher School of Software Engineering

Course: **Algorithms and Data Structures**

Year: **2025**
