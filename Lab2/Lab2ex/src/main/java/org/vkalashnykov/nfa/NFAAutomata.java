package org.vkalashnykov.nfa;

import com.google.common.base.Joiner;
import org.vkalashnykov.nfa.state.NFAState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by vkalashnykov on 5/21/17.
 */
public class NFAAutomata {
    private List<NFAState> currentStates=new ArrayList<NFAState>();

    private String message="";

    public static void main(String[] args) throws IOException {
        NFAState qb=new NFAState("qb",Arrays.asList("0","1","2","3","4","5","6","7","8","9"),true),
                q0=new NFAState("q0", Arrays.asList("0")),
                q1=new NFAState("q1",Arrays.asList("1")),
                q2=new NFAState("q2",Arrays.asList("2")),
                q3=new NFAState("q3",Arrays.asList("3")),
                q4=new NFAState("q4",Arrays.asList("4")),
                q5=new NFAState("q5",Arrays.asList("5")),
                q6=new NFAState("q6",Arrays.asList("6")),
                q7=new NFAState("q7",Arrays.asList("7")),
                q8= new NFAState("q8",Arrays.asList("8")),
                q9=new NFAState("q9",Arrays.asList("9")),
                qe=new NFAState("qe",Arrays.asList("0","1","2","3","4","5","6","7","8","9"),
                        false,true);
        qb.addNextState(q0);
        qb.addNextState(q1);
        qb.addNextState(q2);
        qb.addNextState(q3);
        qb.addNextState(q4);
        qb.addNextState(q5);
        qb.addNextState(q6);
        qb.addNextState(q7);
        qb.addNextState(q8);
        qb.addNextState(q9);
        qb.addNextState(qb);
        q0.addNextState(qe);
        q1.addNextState(qe);
        q2.addNextState(qe);
        q3.addNextState(qe);
        q4.addNextState(qe);
        q5.addNextState(qe);
        q6.addNextState(qe);
        q7.addNextState(qe);
        q8.addNextState(qe);
        q9.addNextState(qe);
        qe.addNextState(qe);
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the string for automata: ");
        String line=reader.readLine();
        NFAAutomata automata=new NFAAutomata();
        automata.addCurrentState(qb);
        while (!"e".equals(line)){
            if ("c".equals(line)){
                automata.printCurrentStates();
                line=reader.readLine();
                continue;
            }
            for (String symbol : line.split("")) {
                if (Pattern.matches("[0-9]", symbol))
                    automata.transitionFunction(symbol);
                else {
                    System.out.println("Wrong input");
                    break;
                }
            }
            automata.printPath();
            line=reader.readLine();
        }


    }

    public void printPath() {
        System.out.println(message);
    }

    public void printCurrentStates() {
        System.out.println("Current states: "+ Joiner.on(", ").join(currentStates));
    }

    public void transitionFunction(String symbol){
        List<NFAState> temporalCurrentStates=new ArrayList<NFAState>();
        for (int i=0;i<currentStates.size();i++){
            NFAState state=currentStates.get(i);
            if (state.containsSymbol(symbol)){
                if (state.isBegin()){
                    temporalCurrentStates.add(state);
                    message+="Going from state: "+state+" to state: "+state+ " through symbol "+symbol+"\n";
                    for (NFAState nextState : state.getNextStates()){
                        if (!(nextState.getName().equals(state.getName())) && nextState.containsSymbol(symbol)) {
                            currentStates.set(i, nextState);
                            message += "Going from state: " + state +
                                    " to state: " + nextState + " through symbol " + symbol+"\n";
                        }
                    }

                } else if(state.isAccept()){
                    message+="Going from state: "+state+" to state: "+state+ " through symbol "+symbol+"\n";
                } else {
                    NFAState nextState=state.getNextStates().get(0);
                    currentStates.set(i,nextState);
                    message+="Going from state: "+state+" to state: "+nextState+ " through symbol "+symbol+"\n";
                }
            }
        }
        currentStates.addAll(temporalCurrentStates);
        printCurrentStates();
    }

    public NFAState addCurrentState(NFAState state){
        currentStates.add(state);
        return state;
    }

    public List<NFAState> getCurrentStates() {
        return currentStates;
    }
}
