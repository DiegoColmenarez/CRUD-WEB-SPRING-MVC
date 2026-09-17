package org.exercise7.model.exceptions;

public class ComputerNotFoundException extends ResourceNotFoundException{
    public ComputerNotFoundException(String message) {
        super(message);
    }

    private static final String MESSAGE_COMPUTER_NOT_EXIST = "The computer id: '%d' don't exist";

    public static ComputerNotFoundException becauseIdDoesNotExist(Long computerId){
        return new ComputerNotFoundException(String.format(MESSAGE_COMPUTER_NOT_EXIST, computerId));
    }
}