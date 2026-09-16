package org.exercise7.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.exercise7.model.enums.Category;
import org.exercise7.model.enums.DiskTechnology;
import org.exercise7.model.enums.RamTechnology;
import java.math.BigDecimal;

@Entity
@Table(name = "computers")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Brand cannot be empty")
    @Size(min = 2, max = 50, message = "Brand must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-_]+$", message = "Invalid brand format")
    @Column(name = "brand", nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category is mandatory")
    @Column(name = "category", nullable = false)
    private Category category;

    @NotBlank(message = "CPU brand is mandatory")
    @Column(name = "cpu_brand")
    private String cpuBrand;

    @NotBlank(message = "CPU speed is mandatory")
    @Pattern(regexp = "(?i)^\\d+(\\.\\d+)?\\s*(GHz|MHz)?$", message = "Invalid speed format (e.g., 3.5GHz)")
    @Column(name = "cpu_speed")
    private String cpuSpeed;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "RAM technology is mandatory")
    @Column(name = "ram_technology")
    private RamTechnology ramTechnology;

    @NotBlank(message = "RAM capacity is mandatory")
    @Pattern(regexp = "^\\d+\\s*(GB|MB)$", message = "Invalid capacity format (e.g., 16GB)")
    @Column(name = "ram_capacity")
    private String ramCapacity;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Disk technology is mandatory")
    @Column(name = "disk_technology")
    private DiskTechnology diskTechnology;

    @NotBlank(message = "Disk capacity is mandatory")
    @Pattern(regexp = "^\\d+\\s*(GB|TB|MB)$", message = "Invalid capacity format (e.g., 1TB)")
    @Column(name = "disk_capacity")
    private String diskCapacity;

    @NotNull(message = "USB ports count is mandatory")
    @Min(value = 0, message = "Cannot have negative ports")
    @Column(name = "usb_ports")
    private Integer usbPorts;

    @NotNull(message = "HDMI ports count is mandatory")
    @Min(value = 0, message = "Cannot have negative ports")
    @Column(name = "hdmi_ports")
    private Integer hdmiPorts;

    @NotBlank(message = "Monitor brand is mandatory")
    @Column(name = "monitor_brand")
    private String monitorBrand;

    @NotNull(message = "Screen size (inches) is mandatory")
    @DecimalMin(value = "0.1", message = "Inches must be strictly positive")
    @DecimalMax(value = "80.0", message = "Inches cannot exceed 80")
    @Column(name = "inches")
    private BigDecimal inches;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.01", message = "Price must be strictly positive")
    @DecimalMax(value = "1000000.00", message = "Price exceeds allowed limit")
    @Digits(integer = 7, fraction = 2, message = "Invalid price format")
    @Column(name = "price")
    private BigDecimal price;

    public Computer() {}

}