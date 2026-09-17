package org.exercise7.model.repository;

import org.exercise7.model.entity.Computer;
import org.exercise7.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
    List<Computer> findByBrandContainingIgnoreCase(String brand);
    List<Computer> findByCategory(Category category);
    List<Computer> findByPriceLessThanEqual(BigDecimal maxPrice);
}