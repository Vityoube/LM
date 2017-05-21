package org.vkalashnykov.nfa.state;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vkalashnykov on 5/21/17.
 */
public class NFAState {
    private boolean accept;
    private boolean begin;
    private String name;
    private List<String> symbols=new ArrayList<>();
    private List<NFAState> nextStates=new ArrayList<NFAState>();

    public NFAState(String name,List<String>symbols,boolean begin) {
        this.begin = begin;
        this.name = name;
        this.symbols.addAll(symbols);
    }

    public NFAState(String name,List<String> symbols, boolean begin, boolean accept){
        this.begin = begin;
        this.name = name;
        this.accept=accept;
        this.symbols.addAll(symbols);
    }

    public NFAState(String name,List<String> symbols) {
        this.name = name;
        this.accept=false;
        this.begin=false;
        this.symbols.addAll(symbols);
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }


    public boolean isBegin() {
        return begin;
    }

    public void setBegin(boolean begin) {
        this.begin = begin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NFAState addNextState(NFAState state){
        this.nextStates.add(state);
        return state;
    }

    public boolean containsSymbol(String symbol){
        if (symbols.contains(symbol))
            return true;
        return false;
    }

    public List<NFAState> getNextStates() {
        return nextStates;
    }

    @Override
    public String toString() {
        return name;
    }
}
