package org.vkalashnykov.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by vkalashnykov on 5/8/17.
 */
public class Lab1Automata {
    private Lab1State currentState;

    public static void main(String[] args) throws IOException {
        Lab1State q0=new Lab1State(0,"q0");
        Lab1State q1=new Lab1State(1,"q1");
        Lab1State q2=new Lab1State(2,"q2");
        Lab1State q3=new Lab1State(3,"q3");
        Lab1State q4=new Lab1State(4,"q4");
        Lab1State q5=new Lab1State(5,"q5");
        Lab1State q6=new Lab1State(6,"q6");
        Lab1State q7=new Lab1State(7,"q7");
        Lab1State finalState=new Lab1State(7,"final");
        q0.setNextStates(q1,q2,q5,q0);
        q1.setNextStates(q2,q3,q6,q1);
        q2.setNextStates(q3,q4,q0,q2);
        q3.setNextStates(q4,q5,q0,q3);
        q4.setNextStates(q5,q6,q0,q4);
        q5.setNextStates(q6,q7,q0,q5);
        q6.setNextStates(q7,q0,q0,q6);
        q7.setNextStates(q1,q2,q5,finalState);
        finalState.setAccept(true);
        Lab1Automata automata=new Lab1Automata(q0);
        BufferedReader coinReader=new BufferedReader(new InputStreamReader(System.in));
        String coinInsert=coinReader.readLine();
        while (!"e".equals(coinInsert)) {
            automata.goingFunction(coinInsert);
            if (automata.isCurrentStateAccept()){
                System.out.println("Got to accept state. Take your ticket! Returning to state \"q0\"");
                automata.setCurrentState(q0);
            }
            coinInsert=coinReader.readLine();
        }

    }

    public Lab1State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Lab1State currentState) {
        this.currentState = currentState;
    }

    public Lab1Automata(Lab1State currentState) {
        this.currentState = currentState;
    }

    public void goingFunction(String coinInsert){
        Lab1State temporal =currentState;

        switch (coinInsert){
            case "1":
                this.currentState=this.currentState.getNext1State();
                System.out.println(coinInsert+" zł inserted");
                break;
            case "2":
                this.currentState=this.currentState.getNext2State();
                System.out.println(coinInsert+" zł inserted");
                break;
            case "5":
                this.currentState=this.currentState.getNext5State();
                System.out.println(coinInsert+" zł inserted");
                break;
            case "":
                this.currentState=this.currentState.getNextEmptyState();
                if (currentState.getStateName().equals(temporal.getStateName()))
                    System.out.println("Current state is not accept. Cannot print a ticket.");
                return;
            case "c":
                System.out.println("Current state: "+currentState.getStateName());
                return;
            default:
                System.out.println("Error in input! Try again...");
                return;
        }
        System.out.println("Going from state: \""+temporal.getStateName()+
                "\" to state: \""+currentState.getStateName()+"\"");

    }

    public boolean isCurrentStateAccept() {
        return currentState.isAccept();
    }
}
