package org.vkalashnykov.tm;

/**
 * Created by vkalashnykov on 5/27/17.
 */
public class State {
    private String name;

    public State() {
    }

    public State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
