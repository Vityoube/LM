package org.vkalashnykov.nfa;

import org.vkalashnykov.nfa.state.NFAState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vkalashnykov on 5/21/17.
 */
public class NFAAutomata {
    private List<NFAState> currentStates=new ArrayList<NFAState>();


    public static void main(String[] args) {
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



    }

    public void transitionFunction(String input){
        for (NFAState currentState )
    }

    public NFAState addCurrentState(NFAState state){
        currentStates.add(state);
        return state;
    }

    public List<NFAState> getCurrentStates() {
        return currentStates;
    }
}
