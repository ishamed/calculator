package exceptions;

public class InvalidFormat extends Exception{
    public InvalidFormat(){
        this("Invalid format");
    }
    public InvalidFormat(String message){
        super(message);
    }
}
