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
            if (Pattern.matches("[0|1|e]+",line)){
                State q00=new State("q00"),q01=new State("q01"), qf=new State("qf"),
                        q10=new State("q10"),q11=new State("q11"),q21=new State("q21"),
                        qe=new State("qe");
                setCurrentState(q00);
                ArrayList<String> symbols= new ArrayList<String>(Arrays.asList(line.split("")));
                Collections.reverse(symbols);
                tape=new Tape(symbols);
                while ("e".equals(tape.readSymbol())){
                        currentState=q00;
                        message+=tape+"\nCurrent state "+currentState+"\n";
                        tape.goNext();
                }
                if ("0".equals(tape.readSymbol())){
                    tape.writeSymbol("1");
                    currentState=q10;
                    message+=tape+"\nCurrent state "+currentState+"\n";
                    tape.goNext();
                    while (!"e".equals(tape.readSymbol()) && !"0".equals(tape.readSymbol())){
                        tape.writeSymbol("0");
                        currentState=q10;
                        message+=tape+"\nCurrent state "+currentState+"\n";
                        tape.goNext();
                    }
                    tape.writeSymbol("1");
                    currentState=qf;
                    message+=tape+"\nCurrent state "+currentState+"\n";
                } else if ("1".equals(tape.readSymbol())){
                    tape.writeSymbol("0");
                    currentState=q01;
                    message+=tape+"\nCurrent state "+currentState+"\n";
                    tape.goNext();
                    if ("e".equals(tape.readSymbol())){
                        tape.writeSymbol("0");
                        currentState=q11;
                        message+=tape+"\nCurrent state "+currentState+"\n";
                        tape.goNext();
                        if ("e".equals(tape.readSymbol())){
                            tape.writeSymbol("1");
                            currentState=qf;
                            message+=tape+"\nCurrent state "+currentState+"\n";
                        } else{
                            currentState=qe;
                        }
                    }
                    else if("0".equals(tape.readSymbol())){
                        tape.writeSymbol("0");
                        currentState=q21;
                        message+=tape+"\nCurrent state "+currentState+"\n";
                        tape.goNext();
                        while (!"e".equals(tape.readSymbol()) && !"0".equals(tape.readSymbol())){
                            tape.writeSymbol("0");
                            currentState=q11;
                            message+=tape+"\nCurrent state "+currentState+"\n";
                            tape.goNext();
                        }
                        tape.writeSymbol("1");
                        currentState=qf;
                        message+=tape+"\nCurrent state "+currentState+"\n";
                        continue;
                    } else if ("1".equals(tape.readSymbol())){
                        tape.writeSymbol("1");
                        currentState=q21;
                        message+=tape+"\nCurrent state "+currentState+"\n";
                        tape.goNext();
                        while (!"e".equals(tape.readSymbol()) && !"0".equals(tape.readSymbol())){
                            tape.writeSymbol("0");
                            currentState=q21;
                            message+=tape+"\nCurrent state "+currentState+"\n";
                            tape.goNext();
                        }
                        tape.writeSymbol("1");
                        currentState=qf;
                        message+=tape+"\nCurrent state "+currentState+"\n";
                    }
                }
                if (currentState==qf){
                    List<String> results=tape.getTapeSymbols();
                    Collections.reverse(results);
                    message+="Operation successful. Result: "+results.stream().map(Object::toString).
                            filter(result -> !result.equals("e")).collect(Collectors.joining())
                            +". Current State: "+currentState;
                } else{
                    message+="Wrong string! Try again";
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
