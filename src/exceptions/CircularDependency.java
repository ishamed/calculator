package exceptions;

public class CircularDependency extends Exception{
    public CircularDependency(String message){
        super(message);
    }
    public CircularDependency(){
        this("circular dependency");
    }
}
