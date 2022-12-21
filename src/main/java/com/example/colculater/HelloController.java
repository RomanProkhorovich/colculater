package com.example.colculater;

import com.example.colculater.Model.NegativeSqrtArgException;
import com.example.colculater.Model.Operation;
import com.example.colculater.Model.ZeroDevideException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class HelloController {
    @FXML
    private TextField label;

    private StringBuilder Operand = new StringBuilder();
    private double firstOperand;
    private double secondOperand;
    private String operator = "";


    @FXML
    protected void onButtonClick(MouseEvent event) {
        char c;
        if (event.getSource().getClass() == Button.class) {
            c = ((Button) event.getSource()).getText().charAt(0);
            System.out.println(c);
            charInput(c);
        }
    }


    @FXML
    protected void onClearAllButtonClick(MouseEvent event) {
        firstOperand = 0;
        secondOperand = 0;
        doAfterOp();
        label.setText("");
    }

    @FXML
    protected void onClearButtonClick(MouseEvent event) {
        Operand.delete(Operand.length() - 1, Operand.length());
        label.setText(String.valueOf(Operand));
    }

    @FXML
    protected void onKeyPressed(KeyEvent event) {
        //System.out.println(Character.isDigit(event.getCharacter().charAt(0)));
        charInput(event.getCharacter().charAt(0));
    }


    private void charInput(char Char) {
        getinfj();
        if (Character.isDigit(Char)) {
            Operand.append(Char);
            label.setText(Operand.toString());
        } else if (Character.isLetter(Char)) {
            new Alert(Alert.AlertType.ERROR, "Letter? seriously? it's calculator ").show();
        } else if (Char == '.') {
            if (Operand.toString().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Enter the number ").show();
            } else if (Operand.toString().contains(".")) {
                new Alert(Alert.AlertType.ERROR, "second point ").show();
            } else {
                Operand.append(Char);
                label.setText(Operand.toString());
            }
        } else if (Char == '√' && operator.equals("")) {
            executeUnar(Char);
        } else if (Char == '√' && !operator.equals("")) {
            secondOperand = Double.parseDouble(Operand.toString());
            executeBin(Char);
            executeUnar(Char);
        } else if (Char == '-' || Char == '+' || Char == '*' || Char == '/' || Char == '^') {
            if (operator.equals("")) {
                operator = String.valueOf(Char);
                firstOperand = Double.parseDouble(Operand.toString());
                Operand.delete(0, Operand.length());
                label.setText(Operand.toString());
            } else {
                executeBin(Char);
            }
        } else if (Char == '=' && !operator.equals("")) {

            executeBin('0');
            Operand.append(firstOperand);
            label.setText(Operand.toString());
        } else if (Char == '=' && operator.equals("")) {
            //Operand.append(firstOperand);
            label.setText(Operand.toString());
        }
    }

    private void executeBin(char c) {
        try {
            secondOperand = Double.parseDouble(Operand.toString());
            firstOperand = Operation.executeBinaryOperation(firstOperand, secondOperand, operator);
            if (c != '0')
                operator = String.valueOf(c);
            else
                operator = "";
            clearOperand();
            label.setText(Operand.toString());
        } catch (NegativeSqrtArgException e) {
            new Alert(Alert.AlertType.ERROR, "negative sqrt arg").show();
        } catch (ZeroDevideException e) {
            new Alert(Alert.AlertType.ERROR, "Divide by 0").show();
        }
    }

    private void executeUnar(char Char) {
        try {
            firstOperand = Operation.executeUnarOperation(firstOperand, String.valueOf(Char));
            clearOperand();
            label.setText(String.valueOf(firstOperand));
        } catch (NegativeSqrtArgException e) {
            new Alert(Alert.AlertType.ERROR, "negative sqrt arg").show();
        }
    }

    private void clearOperand() {
        Operand.delete(0, Operand.length());
        //Operand.append(firstOperand);
    }

    private void getinfj() {
        System.out.println("arg1 " + firstOperand);
        System.out.println("arg2 " + secondOperand);
        System.out.println("operand " + Operand);
        System.out.println("operator " + operator);
    }

    private void doAfterOp() {
        Operand = new StringBuilder();
        //Operand.append(firstOperand);
        operator = "";
        //Operand.append(firstOperand);
        label.setText(String.valueOf(firstOperand));
    }
}