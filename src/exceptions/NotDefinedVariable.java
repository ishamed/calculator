package exceptions;

public class NotDefinedVariable extends Exception{
    public NotDefinedVariable(String message){
        super(message);
    }
    public NotDefinedVariable(){
        this("not defined variable");
    }
}
