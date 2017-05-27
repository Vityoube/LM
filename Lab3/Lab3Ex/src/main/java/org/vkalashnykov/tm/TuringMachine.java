package org.vkalashnykov.tm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by vkalashnykov on 5/27/17.
 */
public class TuringMachine {
    private State currentState;
    private Tape tape;
    private String message="";

    public static void main(String[] args) throws IOException {
        new TuringMachine().run();
    }

    public void run() throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter string or type q to quit");
        String line=reader.readLine();
        while(!"q".equals(line)){
            if (Pattern.matches("[\\s]*[0|1]+[\\s]*[0|1]*[\\s]*",line)){
                State q0=new State("q0"),q1=new State("q1"), q2=new State("q2");
                setCurrentState(q0);
                ArrayList<String> symbols= new ArrayList<String>(Arrays.asList(line.split("")));
                Collections.reverse(symbols);
                for (int i=0; i<symbols.size();i++){
                    if (Pattern.matches("[\\s]{1}",symbols.get(i)))
                        symbols.set(i,"'empty'");
                }
                tape=new Tape(symbols);
                while ("'empty'".equals(tape.readSymbol())){
                        currentState=q0;
                        message+=tape+"\nCurrent state "+currentState+"\n";
                        tape.goNext();
                }
                if ("0".equals(tape.readSymbol())){
                    tape.writeSymbol("1");
                    currentState=q0;
                    message+=tape+"\nCurrent state "+currentState+"\n";
                    tape.goNext();
                    while (!"'empty'".equals(tape.readSymbol()) && !"0".equals(tape.readSymbol())){
                        tape.writeSymbol("0");
                        currentState=q1;
                        message+=tape+"\nCurrent state "+currentState+"\n";
                        tape.goNext();
                    }
                    tape.writeSymbol("1");
                    currentState=q2;
                    message+=tape+"\nCurrent state "+currentState+"\n";
                } else {
                    tape.writeSymbol("0");
                    currentState=q1;
                    message+=tape+"\nCurrent state "+currentState+"\n";
                    tape.goNext();
                    if("'empty'".equals(tape.readSymbol()) || "0".equals(tape.readSymbol())){
                        tape.writeSymbol("0");
                        currentState=q1;
                        message+=tape+"\nCurrent state "+currentState+"\n";
                        tape.goNext();
                    } else {
                        tape.writeSymbol("1");
                        currentState=q1;
                        message+=tape+"\nCurrent state "+currentState+"\n";
                        tape.goNext();
                    }
                    while (!"'empty'".equals(tape.readSymbol()) && !"0".equals(tape.readSymbol())){
                        tape.writeSymbol("0");
                        currentState=q1;
                        message+=tape+"\nCurrent state "+currentState+"\n";
                        tape.goNext();
                    }
                    tape.writeSymbol("1");
                    currentState=q2;
                    message+=tape+"\nCurrent state "+currentState+"\n";
                    if (!"'empty'".equals(tape.readSymbol())){
                        currentState=q0;
                    }
                }
                if (currentState.equals(q2)){
                    tape.setWriting(false);
                    List<String> result=tape.getTapeSymbols();
                    Collections.reverse(result);
                    message+="Operation successfull. Result: " +
                            result.stream().map(Object::toString).filter(element -> !element.equals("'empty'"))
                                    .collect(Collectors.joining())+
                            ". Current State: "+currentState+"\nEnter new string " +
                            "or type q t o quit";
                } else {
                    message+="Wrong string! Try another or type q to quit";
                }
            } else
                System.out.println("Wrong input! Try again.");
            printTuringMachineState();
            line=reader.readLine();
        }
    }


    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void printTuringMachineState(){
        System.out.println(message);
    }
}
