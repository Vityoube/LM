package org.vkalashnykov.tm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vkalashnykov on 5/27/17.
 */
public class Tape {
    private ArrayList<String> tapeSymbols;
    private int currentPosition;
    private boolean writing;
    private String symbolToWrite;
    private String symbolToRead;

    public Tape() {
    }

    public Tape(ArrayList<String> tapeSymbols) {
        this.tapeSymbols = tapeSymbols;
        currentPosition=0;
    }

    public ArrayList<String> getTapeSymbols() {
        return tapeSymbols;
    }

    public void setTapeSymbols(ArrayList<String> tapeSymbols) {
        this.tapeSymbols = tapeSymbols;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String writeSymbol(String symbol){
        if (currentPosition<tapeSymbols.size())
            tapeSymbols.set(currentPosition,symbol);
        else
            tapeSymbols.add(symbol);
        writing=true;
        symbolToWrite=symbol;
        return symbol;
    }

    public String readSymbol(){
        writing=false;
        if (currentPosition<tapeSymbols.size())
            symbolToRead=tapeSymbols.get(currentPosition);
        else {
            symbolToRead = "'empty'";
            return null;
        }
        return tapeSymbols.get(currentPosition);
    }

    public boolean isWriting() {
        return writing;
    }

    public void setWriting(boolean writing) {
        this.writing = writing;
    }

    public void goNext(){
        currentPosition++;
    }

    @Override
    public String toString() {
        return "Tape current position "+currentPosition+(isWriting() ? " and writes symbol "+symbolToWrite :
        " reads symbol: "+symbolToRead);
    }
}
