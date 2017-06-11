package org.vkalashnykov.lab5ex;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * Created by vkalashnykov on 6/11/17.
 */
public class Analyzer extends Application{

    public TextField inputText;
    public TextField output;

    public String run(String inputText) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String regexPattern="(([(]?(([0-9]+([.][0-9]+)?)|([(][0-9]+([.][0-9]+)?([+-/*^](([0-9]+([.][0-9]+)?)|([(][0-9]+([.][0-9]+)?[)])))*[)]))([+-/*^](([0-9]+([.][0-9]+)?)|([(][0-9]+([.][0-9]+)?([+-/*^](([0-9]+([.][0-9]+)?)|([(][0-9]+([.][0-9]+)?[)])))*[)])))*[)]?)+[;])+";
        String[] input=inputText.split("[ \t]+");
        String inputParsed="";
        for (String inputPart : input){
            inputParsed+=inputPart;
        }
        if (Pattern.matches(regexPattern,inputParsed)) {
            return "Input is right!";
        } else {
            return "Input is wrong!";
        }
    }

    public void checkString(ActionEvent actionEvent) throws IOException {
        String input=inputText.getText();
        String outputText="";
        if (!"".equals(input))
            outputText=run(input);
        output.setText(outputText);
    }

    public static void main(String[] args) {
        Application.launch(Analyzer.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("/regex.fxml"));

        primaryStage.setTitle("Lab 5");
        primaryStage.setScene(new Scene(root,650,400));
        primaryStage.show();
    }
}
