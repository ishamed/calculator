package exceptions;

public class InvalidInput extends Exception {
    public InvalidInput(String message) {
        super(message);
    }
    public InvalidInput(){
        this("Invalid input");
    }
}