package exceptions;

public class Inconsistency extends Exception{
    public Inconsistency(String message){
        super(message);
    }
    public Inconsistency(){
        this("Inconsistency");
    }
}
