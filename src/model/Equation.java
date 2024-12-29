package model;

import exceptions.ArithmeticError;
import exceptions.InvalidInput;
import model.dataStructure.LinkedStack;

public class Equation {
    private String rawEquation ;
    private String postfixEquation ;
    public Equation(String rawEquation) throws ArithmeticError, InvalidInput {
        setRawEquation(rawEquation);
    }



    private int precedence(String op) {
        switch (op) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }
    private String createPostfixEquation() throws ArithmeticError {
        StringBuilder result = new StringBuilder();
        LinkedStack<String> stack = new LinkedStack<>();
        StringBuilder number = new StringBuilder();
        boolean lastWasOperator = true;

        for (int i = 0; i < rawEquation.length(); i++) {
            char c = rawEquation.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                number.append(c);
                lastWasOperator = false;
            } else if (Character.isLetter(c)) {
                String token = parseToken(i);
                if (token.equals("PI")) {
                    result.append("3.14159").append(" ");
                } else if (token.equals("EN")) {
                    result.append("2.71828").append(" ");
                } else {
                    result.append(token);
                    if (i != rawEquation.length()-1 && rawEquation.charAt(i+1) != '!'){
                        result.append(" ");
                    }
                }
                i += token.length() - 1;
                lastWasOperator = false;
            } else if (c == '-') {
                if (lastWasOperator || (i > 0 && rawEquation.charAt(i - 1) == '(')) {
                    number.append(c);
                } else {
                    if (number.length() > 0) {
                        result.append(number).append(' ');
                        number.setLength(0);
                    }
                    while (!stack.isEmpty() && precedence(stack.top()) >= precedence("-")) {
                        result.append(stack.pop()).append(' ');
                    }
                    stack.push("-");
                    lastWasOperator = true;
                }
            } else if (c == '!') {
                if (number.length() > 0) {
                    if (!isInteger(Double.parseDouble(number.toString()))){
                        throw new ArithmeticError("Factorial is only defined for integers. The entered number is not a valid integer.");
                    }
                    int operand = (int) Double.parseDouble(number.toString());
                    int factorialResult = factorial(operand);
                    result.append(factorialResult).append(' ');
                    number.setLength(0);
                } else if (!result.isEmpty() && Character.isLetter(result.charAt(result.length() - 1))) {
                    result.append('!').append(" ");
                } else {
                    throw new ArithmeticError("Factorial requires an operand before '!'");
                }
                lastWasOperator = false;
            } else if (c == '(') {
                stack.push("(");
                lastWasOperator = true;
            } else if (c == ')') {
                if (number.length() > 0) {
                    result.append(number).append(' ');
                    number.setLength(0);
                }
                while (!stack.isEmpty() && !stack.top().equals("(")) {
                    result.append(stack.pop()).append(' ');
                }
                if (!stack.isEmpty() && stack.top().equals("(")) {
                    stack.pop();
                }
                lastWasOperator = false;
            } else if (isOperator(Character.toString(c))) {
                if (number.length() > 0) {
                    result.append(number).append(' ');
                    number.setLength(0);
                }
                while (!stack.isEmpty() && precedence(stack.top()) >= precedence(Character.toString(c))) {
                    result.append(stack.pop()).append(' ');
                }
                stack.push(Character.toString(c));
                lastWasOperator = true;
            }
        }

        if (number.length() > 0) {
            result.append(number).append(' ');
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(' ');
        }

        return result.toString();
    }



    private int factorial(double n) throws ArithmeticError {
        if (n < 0) {
            throw new ArithmeticError("Factorial is not defined for negative numbers");
        }
        int result = 1;
        for (int i = 1; i <= (int) n; i++) {
            result *= i;
        }
        return result;
    }
    private boolean isInteger(double number){
        return number == Math.floor(number);
    }
    private String parseToken(int startIndex) {
        StringBuilder token = new StringBuilder();
        for (int i = startIndex; i < rawEquation.length(); i++) {
            char c = rawEquation.charAt(i);
            if (Character.isLetter(c)) {
                token.append(c);
            } else {
                break;
            }
        }
        return token.toString();
    }


    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^");
    }


    public String getRawEquation() {
        return rawEquation;
    }

    public void setRawEquation(String rawEquation) throws ArithmeticError, InvalidInput {
        this.rawEquation = rawEquation;
        setPostfixEquation(createPostfixEquation());
    }

    public String getPostfixEquation() {
        return postfixEquation;
    }

    public void setPostfixEquation(String postfixEquation) {
        this.postfixEquation = postfixEquation;
    }
}
