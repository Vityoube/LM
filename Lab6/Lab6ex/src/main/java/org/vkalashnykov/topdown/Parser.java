package org.vkalashnykov.topdown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by vkalashnykov on 6/16/17.
 */
public class Parser {
    private String[] input;
    private int currentPosition;
    private int inputLength;
    private boolean right=false;

    public void inputString(String inputString){
        this.input=inputString.split("\\s*");
        currentPosition=0;
        inputLength=input.length-1;
    }

    public void S(){
        W();
        if (currentPosition<=inputLength && ";".equals(input[currentPosition])){
            System.out.println("Found symbol: "+input[currentPosition]);
            right=true;
            next();
            S();
        }
//        while(";".equals(input[currentPosition])){
//            System.out.println("Found symbol: "+input[currentPosition]);
//            next();
//            W();
//            if (currentPosition==inputLength && right){
//                System.out.println();
//                System.out.println("Input is right");
//                System.out.println();
//                return;
//            }
//        }
        if (currentPosition==inputLength+1 && ";".equals(input[currentPosition-1]))
            printSuccess();
        wrongInput();
    }

    private void printSuccess() {
        System.out.println();
        System.out.println("Input is right");
        System.out.println();
        System.exit(0);
    }

    private void W() {
        if (currentPosition<=inputLength){
            switch (input[currentPosition]){
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    number();
                    operation();
                    break;
                case "(":
                    System.out.println("Found symbol: "+input[currentPosition]);
                    next();
                    W();
                    if (")".equals(input[currentPosition])) {
                        System.out.println("Found symbol: "+input[currentPosition]);
                        next();
                        operation();
                    }else {
                        printError();
                    }
                    break;
                default:
                    return;
            }
        }
    }

    private void wrongInput() {
        System.out.println();
        System.out.println("Wrong symbol "+input[inputLength]+" on position "+inputLength);
        System.out.println();
        System.exit(1);
    }

    private void next() {
        currentPosition++;
    }


    private void number() {
        if (currentPosition<=inputLength){
            switch (input[currentPosition]){
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    System.out.println("Found symbol: "+input[currentPosition]);
                    next();
                    number();
                    break;
                case ".":
                    System.out.println("Found symbol: "+input[currentPosition]);
                    next();
                    dotnumber();
                    break;
                default:
                    return;
            }

        }

    }

    private void operation() {
        if (currentPosition<=inputLength){
            switch (input[currentPosition]){
                case "*":
                case ":":
                case "+":
                case "-":
                case "^":
                    System.out.println("Found symbol: "+input[currentPosition]);
                    next();
                    W();
                    break;
                default:
                        return;
            }
        }
    }

    private void dotnumber() {
        if (currentPosition<=inputLength){
            switch (input[currentPosition]) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    System.out.println("Found symbol: "+input[currentPosition]);
                    next();
                    dotnumber();
                    break;
                default:
                    return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Parser parser=new Parser();
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input string: ");
        String line=reader.readLine();
        while(!"exit".equals(line)){
            parser.inputString(line);
            parser.S();
            System.out.println("Input string again: ");
            line=reader.readLine();
        }
    }

    public void printError(){
        System.out.println();
        System.out.println("Wrong symbol "+input[currentPosition]+" on position "+currentPosition);
        System.out.println();
        System.exit(1);
    }

}
