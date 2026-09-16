package org.exercise7.model.exceptions;

public class ComputerNotFoundException extends ResourceNotFoundException{
    public ComputerNotFoundException(String message) {
        super(message);
    }

    private static final String MESSAGE_COMPUTER_NOT_EXIST = "The computer id: '%d' don't exist";

    public static ComputerNotFoundException becauseIdDoesNotExist(){
        return new ComputerNotFoundException(MESSAGE_COMPUTER_NOT_EXIST);
    }
}
