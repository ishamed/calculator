package controller;

import exceptions.ArithmeticError;
import exceptions.Inconsistency;
import exceptions.InvalidInput;
import model.Database;
import model.Equation;
import model.Variable;

public class VariableController {
    private static VariableController variableController;
    private VariableController(){}

    public static VariableController getEquationController() {
        if (variableController == null){
            variableController = new VariableController();
        }
        return variableController;
    }
    public void makeVariable(String equation) throws ArithmeticError, InvalidInput, Inconsistency {
        String[] vAndEquation = equation.split("=");
        Variable variable ;
        if (vAndEquation.length == 1){
            variable = new Variable(null,new Equation(vAndEquation[0].toUpperCase()));
        }else {
            char name = vAndEquation[0].toUpperCase().charAt(0);
            if (Database.getDatabase().getInconsistencyManager()[name - 'A']){
                throw new Inconsistency();
            }
            variable = new Variable(vAndEquation[0].toUpperCase(),new Equation(vAndEquation[1].toUpperCase()));
        }

        Database.getDatabase().getEquations().enqueue(variable);
    }
    public String showAnswer(){
        StringBuilder result = new StringBuilder();
        for (Variable v : Database.getDatabase().getAnswers()){
            if (v.getName() != null){
                result.append(v.getName()).append("=").append(v.getValue().getRawEquation()).append("\n");
            }else {
                result.append(v.getValue().getPostfixEquation()).append("\n");
            }
        }
        return result.toString();
    }
}
