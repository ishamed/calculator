package exceptions;

public class ArithmeticError extends Exception{
    public ArithmeticError(String message){
        super(message);
    }
    public ArithmeticError(){
        this("Arithmetic Error");
    }
}
