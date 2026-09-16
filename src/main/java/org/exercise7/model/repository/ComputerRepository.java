package org.exercise7.model.repository;

import org.exercise7.model.entity.Computer;
import org.exercise7.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.util.List;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
    List<Computer> findByBrandContainingIgnoreCase(String brand);
    List<Computer> findByCategory(Category category);
    List<Computer> findByPriceLessThanEqual(BigDecimal maxPrice);
}