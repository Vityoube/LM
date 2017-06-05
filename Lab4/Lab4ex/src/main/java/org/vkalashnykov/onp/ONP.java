package org.vkalashnykov.onp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by vkalashnykov on 6/4/17.
 */
public class ONP extends Application{

    public TextField input;
    public TextField output;

    public String convertToONP(String input) throws IOException {
        Stack<String> firstLineOperationsStack=new Stack<>();
        Stack<String> secondLineOperationsStack=new Stack<>();
        Stack<String> operationBeforeParenthesisStack = new Stack<>();
        Stack<String> stack = new Stack<>();
        String[] symbols=input.split("[ |\t]");
        String output="";
        for (String symbol : symbols){
            switch (symbol){
                case "-":
                case "+":
                case "*":
                case "/":
                case "^":
                    while(!stack.isEmpty() && priority(stack.peek())>=priority(symbol))
                        output+=stack.pop()+" ";
                    stack.push(symbol);
                    break;
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
                case "-0":
                case "-1":
                case "-2":
                case "-3":
                case "-4":
                case "-5":
                case "-6":
                case "-7":
                case "-8":
                case "-9":
                case "+0":
                case "+1":
                case "+2":
                case "+3":
                case "+4":
                case "+5":
                case "+6":
                case "+7":
                case "+8":
                case "+9":
                    output+=symbol+" ";
                    break;
                case "(":
                    stack.push(symbol);
                    break;
                case ")":
                    while (!"(".equals(stack.peek()))
                        output+=stack.pop()+" ";
                    stack.pop();
                    break;
                case "0(":
                case "1(":
                case "2(":
                case "3(":
                case "4(":
                case "5(":
                case "6(":
                case "7(":
                case "8(":
                case "9(":
                case "-0(":
                case "-1(":
                case "-2(":
                case "-3(":
                case "-4(":
                case "-5(":
                case "-6(":
                case "-7(":
                case "-8(":
                case "-9(":
                case "+0(":
                case "+1(":
                case "+2(":
                case "+3(":
                case "+4(":
                case "+5(":
                case "+6(":
                case "+7(":
                case "+8(":
                case "+9(":
                    stack.push("*");
                    stack.push("(");
                    output+=symbol.substring(0,symbol.indexOf("("))+" ";
                    break;
                default:
                    System.out.println("Wrong input.Try again.");
                    return output;
            }
        }
        while (!stack.isEmpty())
            output+=stack.pop()+" ";
        return output;
    }

    public int priority(String operator){
        if ("+".equals(operator) || "-".equals(operator))
            return 1;
         else if ("*".equals(operator) || "/".equals(operator))
            return 2;
         else if ("^".equals(operator))
            return 3;
        else
            return 0;
    }

    public void convert(ActionEvent actionEvent) throws IOException {
        String inputText=input.getText();
        String outputText=convertToONP(inputText);
        output.setText(outputText);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage=new Stage();
        String title="Reverse Polish Notation";
        AnchorPane root=(AnchorPane) FXMLLoader.load(ONP.class.getResource("/gui.fxml"));
        Scene mainScene=new Scene(root);
        stage.setTitle(title);
        stage.setScene(mainScene);
        stage.show();
    }
}
