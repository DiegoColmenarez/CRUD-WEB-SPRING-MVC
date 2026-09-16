package org.exercise7.model.service;

import org.exercise7.model.entity.Computer;
import org.exercise7.model.enums.Category;
import org.exercise7.model.exceptions.ComputerNotFoundException;
import org.exercise7.model.exceptions.InvalidComputerConfigurationException;
import org.exercise7.model.repository.ComputerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ComputerService {

    private final ComputerRepository computerRepository;

    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }
    @Transactional
    public Computer saveComputer(Computer computer) {
        if (computer.getUsbPorts() + computer.getHdmiPorts() > 20) {
            throw InvalidComputerConfigurationException.becauseTotalPortsExceedLimit();
        }
        return computerRepository.save(computer);
    }

    @Transactional(readOnly = true)
    public List<Computer> findAllComputers() {
        return computerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Computer findById(Long id) {
        return computerRepository.findById(id)
                .orElseThrow(ComputerNotFoundException::becauseIdDoesNotExist);
    }

    @Transactional
    public void deleteComputer(Long id) {
        if (!computerRepository.existsById(id)) {
            throw ComputerNotFoundException.becauseIdDoesNotExist();
        }
        computerRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Computer> findByBrand(String brand) {
        return computerRepository.findByBrandContainingIgnoreCase(brand);
    }

    @Transactional(readOnly = true)
    public List<Computer> findByCategory(Category category) {
        return computerRepository.findByCategory(category);
    }

    @Transactional(readOnly = true)
    public List<Computer> findByMaxPrice(BigDecimal maxPrice) {
        return computerRepository.findByPriceLessThanEqual(maxPrice);
    }
}
