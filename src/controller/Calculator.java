package controller;

import exceptions.ArithmeticError;
import exceptions.Inconsistency;
import exceptions.InvalidInput;
import model.Database;
import model.Equation;
import model.Variable;
import model.dataStructure.LinkedStack;

public class Calculator {
    private static Calculator calculator ;
    private Calculator(){}

    public static Calculator getCalculator() {
        if (calculator==null){
            calculator = new Calculator();
        }
        return calculator;
    }
    public boolean calculate(Variable variable) throws ArithmeticError, InvalidInput, Inconsistency {
        if (isSolvable(variable.getValue().getPostfixEquation())){
            if (variable.isSetValue()){
                throw new Inconsistency();
            }
            LinkedStack<Double> stack = new LinkedStack<>();
            String[] tokens = variable.getValue().getPostfixEquation().split(" ");
            for (String token : tokens){
                if (isNumeric(token)){
                    stack.push(Double.parseDouble(token));
                }else if (isOperator(token)){
                    double num1 = stack.pop();
                    double num2 = stack.pop();
                    double result = applyOperator(token,num1,num2) ;
                    stack.push(roundToFourDecimals(result));
                }
            }
            variable.getValue().setRawEquation(String.valueOf(stack.pop()));
            variable.setSetValue(true);
            return true ;
        }
        return false ;

    }
    private double roundToFourDecimals(double value){
        return Math.round(value * 10000.0 ) / 10000.0 ;
    }
    private boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String s) {
        return "+-*/^".contains(s);
    }

    private double applyOperator(String operator, double operand1, double operand2) throws ArithmeticError {
        switch (operator) {
            case "+":
                return operand2 + operand1;
            case "-":
                return operand2 - operand1;
            case "*":
                return operand2 * operand1;
            case "/":
                if (operand1 == 0) {
                    throw new ArithmeticError("Division by zero");
                }
                return operand2 / operand1;
            case "^":
                if (operand2 < 0 && operand1 == .5){
                    throw new ArithmeticError();
                }
                return Math.pow(operand2, operand1);
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
    private boolean isSolvable(String postfix){
        for (Character c : postfix.toCharArray()){
            if (Character.isLetter(c)){
                return false ;
            }
        }
        return true ;
    }
    public void reWrite(Variable variable) throws ArithmeticError, InvalidInput {
        String variableName = variable.getName();
        String variableValue = variable.getValue().getRawEquation();
        for (int i = 0; i < Database.getDatabase().getEquations().size(); i++) {
            Variable currentVariable = Database.getDatabase().getEquations().dequeue() ;
            if (currentVariable.getName() != null){
                String rawEquation = currentVariable.getValue().getRawEquation();
                String updateRawEquation = rawEquation.replace(variableName,variableValue);
                currentVariable.getValue().setRawEquation(updateRawEquation);
            }
            Database.getDatabase().getEquations().enqueue(currentVariable);
        }
    }
}
