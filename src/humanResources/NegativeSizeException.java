package humanResources;

public class NegativeSizeException extends NegativeArraySizeException{

    public NegativeSizeException(){
    }

    public NegativeSizeException(String message){
        super(message);
    }
}
