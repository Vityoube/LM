package org.vkalashnykov.tm;

import java.util.List;

/**
 * Created by vkalashnykov on 5/27/17.
 */
public class Tape {
    private List<String> tapeSymbols;
    private int currentPosition;

    public Tape() {
    }

    public Tape(List<String> tapeSymbols) {
        this.tapeSymbols = tapeSymbols;
        currentPosition=0;
    }

    public List<String> getTapeSymbols() {
        return tapeSymbols;
    }

    public void setTapeSymbols(List<String> tapeSymbols) {
        this.tapeSymbols = tapeSymbols;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String addSymbol(String symbol){
        tapeSymbols.add(symbol);
        return symbol;
    }
}
