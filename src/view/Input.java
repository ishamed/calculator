package view;

import controller.InputController;
import controller.Process;
import controller.VariableController;
import exceptions.*;

import java.util.Scanner;

public class Input {
    private static Input input ;
    private Scanner scan ;
    private Input(){
        scan = new Scanner(System.in);
    }

    public static Input getInput() {
        if (input == null){
            input = new Input();
        }
        return input;
    }

    public Scanner getScan() {
        return scan;
    }
    public void input(){
        String inp = scan.nextLine();
        if (inp.startsWith("Inp")){
            String[] strings = inp.split(" ");
            inputExpression(Integer.parseInt(strings[1]));
        }
    }
    public void inputExpression(int num){
        for (int i = 0; i < num; i++) {
            String input = scan.nextLine();
            try {
                InputController.getInputController().findErrorInExpression(input);
                VariableController.getEquationController().makeVariable(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            InputController.getInputController().findCircularDependency();
            System.out.println(Process.getProcess().calculate());
            InputController.getInputController().findDefineError();
        } catch (ArithmeticError e) {
            System.out.println(e.getMessage());
        } catch (Inconsistency e) {
            System.out.println(e.getMessage());
        } catch (InvalidInput e) {
            System.out.println(e.getMessage());
        }catch (NotDefinedVariable e){
            System.out.println(e.getMessage());
        } catch (CircularDependency e) {
            System.out.println(e.getMessage());
        }
    }
}
