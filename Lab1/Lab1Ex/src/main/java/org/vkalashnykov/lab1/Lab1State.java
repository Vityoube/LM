package org.vkalashnykov.lab1;

/**
 * Created by vkalashnykov on 5/8/17.
 */
public class Lab1State {
    private int currentCoinCount;
    private Lab1State next1State;
    private Lab1State next2State;
    private Lab1State next5State;

    public String getStateName() {
        return stateName;
    }

    private String stateName;

    private Lab1State nextEmptyState;
    private boolean accept;

    public Lab1State(int currentCoinCount,String stateName) {
        this.currentCoinCount = currentCoinCount;
        this.stateName=stateName;
        accept=false;
    }

    public void setNextStates(Lab1State next1State, Lab1State next2State,
                              Lab1State next5State, Lab1State nextEmptyState){
        this.next1State=next1State;
        this.next2State=next2State;
        this.next5State=next5State;
        this.nextEmptyState=nextEmptyState;
    }

    public Lab1State() {
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public Lab1State getNext1State() {
        return next1State;
    }

    public void setNext1State(Lab1State next1State) {
        this.next1State = next1State;
    }

    public Lab1State getNext2State() {
        return next2State;
    }

    public void setNext2State(Lab1State next2State) {
        this.next2State = next2State;
    }

    public Lab1State getNext5State() {
        return next5State;
    }

    public void setNext5State(Lab1State next5State) {
        this.next5State = next5State;
    }

    public Lab1State getNextEmptyState() {
        return nextEmptyState;
    }

    public void setNextEmptyState(Lab1State nextEmptyState) {
        this.nextEmptyState = nextEmptyState;
    }
}
