package org.exercise7.model.service;

import org.exercise7.model.entity.Computer;
import org.exercise7.model.repository.ComputerRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ComputerService {

    private final ComputerRepository computerRepository;

    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }
    @Transactional
    public Computer saveComputer(Computer computer) {
        if (computer.getUsbPorts() + computer.getHdmiPorts() > 20) {
            throw new IllegalArgumentException("Total port count cannot exceed 20");
        }
        return computerRepository.save(computer);
    }

    @Transactional(readOnly = true)
    public List<Computer> findAllComputers() {
        return computerRepository.findAll();
    }
}
