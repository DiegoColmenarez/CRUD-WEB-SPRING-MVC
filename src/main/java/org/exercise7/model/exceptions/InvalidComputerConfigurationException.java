package org.exercise7.model.exceptions;

public class InvalidComputerConfigurationException extends DomainException {
    public InvalidComputerConfigurationException(String message) {
        super(message);
    }

    private static final String MESSAGES_INVALID = "The total number of USB and HDMI ports cannot exceed 20.";

    public static InvalidComputerConfigurationException becauseTotalPortsExceedLimit() {
        return new InvalidComputerConfigurationException(MESSAGES_INVALID);
    }
}
