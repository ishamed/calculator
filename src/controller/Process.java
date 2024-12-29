package controller;

import exceptions.ArithmeticError;
import exceptions.Inconsistency;
import exceptions.InvalidInput;
import model.Database;
import model.Variable;

public class Process {
    private static Process process ;
    private Process(){}

    public static Process getProcess() {
        if (process == null){
            process = new Process();
        }
        return process;
    }
    public String calculate() throws ArithmeticError, Inconsistency, InvalidInput {
        while (!Database.getDatabase().getEquations().isEmpty()){
            Variable variable = Database.getDatabase().getEquations().dequeue();
            if (Calculator.getCalculator().calculate(variable)){
                Calculator.getCalculator().reWrite(variable);
                Database.getDatabase().getAnswers().add(variable);
            }else {
                Database.getDatabase().getEquations().enqueue(variable);
            }
        }
        return VariableController.getEquationController().showAnswer();
    }
}
