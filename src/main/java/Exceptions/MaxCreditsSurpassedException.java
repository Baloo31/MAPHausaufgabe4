package Exceptions;

public class MaxCreditsSurpassedException extends Exception{

    public MaxCreditsSurpassedException(String message){
        super(message);
    }
}