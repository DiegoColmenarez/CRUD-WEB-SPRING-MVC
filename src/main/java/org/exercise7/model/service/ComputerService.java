package org.exercise7.model.service;

import org.exercise7.model.repository.ComputerRepository;

public class ComputerService {

    private final ComputerRepository computerRepository;

    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }
}
