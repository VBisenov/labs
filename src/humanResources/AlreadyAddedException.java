package humanResources;

public class AlreadyAddedException extends Exception {
    AlreadyAddedException(){

    }
    AlreadyAddedException(String message){
        super(message);
    }
}
