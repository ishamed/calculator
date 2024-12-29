package controller;

import exceptions.CircularDependency;
import exceptions.InvalidFormat;
import exceptions.InvalidInput;
import exceptions.NotDefinedVariable;
import model.Database;

import java.util.Stack;
import java.util.regex.Pattern;

public class InputController {
    private InputController(){}
    private static InputController inputController ;
    public static InputController getInputController() {
        if (inputController == null){
            inputController = new InputController();
        }
        return inputController;
    }
    private void validInput(String input) throws InvalidInput {
        String regex = "^[a-zA-Z0-9+\\-*/^=!().\\\\s]+$";
        if (!Pattern.matches(regex,input)){
            throw new InvalidInput();
        }
        if (input.split("=").length !=2 && input.split("=").length != 1){
            throw new InvalidInput();
        }
        String[] split = input.split("=");
        if (!Character.isLetter(split[0].charAt(0)) && split[0].length() != 1 && split.length !=1){
            throw new InvalidInput();
        }
    }
    private void formatInput(String input) throws InvalidFormat {
        Stack<Character> checkInput = new Stack<>();
        for (char c : input.toCharArray()) {
            if (c == '(') {
                checkInput.push(c);
            } else if (c == ')') {
                if (checkInput.isEmpty() || checkInput.pop() != '(') {
                    throw new InvalidFormat();
                }
            }
        }
        if (!checkInput.isEmpty()) {
            throw new InvalidFormat();
        }
    }

    private void checkConsecutiveOperators(String expression) throws InvalidFormat{
        for (int i = 0; i < expression.length()-1 ; i++) {
            if (isOperators(String.valueOf(expression.charAt(i))) && isOperators(String.valueOf(expression.charAt(i+1)))){
                throw new InvalidFormat("Error: Two consecutive operators found.");
            }
        }
    }
    private boolean isOperators(String value){
        return (value.equals("/") || value.equals("+") || value.equals("*") || value.equals("-") || value.equals("^"));
    }
    public void findCircularDependency() throws CircularDependency {
        for (int i = 0; i < Database.getDatabase().getCircularDependency().length; i++) {
            for (int j = 0; j < Database.getDatabase().getCircularDependency()[i].length ; j++) {
                if (Database.getDatabase().getCircularDependency()[i][j] && Database.getDatabase().getCircularDependency()[j][i]){
                    throw new CircularDependency();
                }
            }
        }
    }
    
    public void findErrorInExpression(String expression) throws InvalidInput, InvalidFormat {
        validInput(expression);
        formatInput(expression);
        checkConsecutiveOperators(expression);
    }
    public void findDefineError() throws NotDefinedVariable {
        for (int i = 0; i < Database.getDatabase().getCircularDependency().length; i++) {
            for (int j = 0; j < Database.getDatabase().getCircularDependency()[i].length ; j++) {
                if (Database.getDatabase().getCircularDependency()[i][j] && !Database.getDatabase().getInconsistencyManager()[j]){
                    throw new NotDefinedVariable();
                }

            }

        }
    }
}
