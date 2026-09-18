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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public String getCpuBrand() { return cpuBrand; }
    public void setCpuBrand(String cpuBrand) { this.cpuBrand = cpuBrand; }
    public String getCpuSpeed() { return cpuSpeed; }
    public void setCpuSpeed(String cpuSpeed) { this.cpuSpeed = cpuSpeed; }
    public RamTechnology getRamTechnology() { return ramTechnology; }
    public void setRamTechnology(RamTechnology ramTechnology) { this.ramTechnology = ramTechnology; }
    public String getRamCapacity() { return ramCapacity; }
    public void setRamCapacity(String ramCapacity) { this.ramCapacity = ramCapacity; }
    public DiskTechnology getDiskTechnology() { return diskTechnology; }
    public void setDiskTechnology(DiskTechnology diskTechnology) { this.diskTechnology = diskTechnology; }
    public String getDiskCapacity() { return diskCapacity; }
    public void setDiskCapacity(String diskCapacity) { this.diskCapacity = diskCapacity; }
    public Integer getUsbPorts() { return usbPorts; }
    public void setUsbPorts(Integer usbPorts) { this.usbPorts = usbPorts; }
    public Integer getHdmiPorts() { return hdmiPorts; }
    public void setHdmiPorts(Integer hdmiPorts) { this.hdmiPorts = hdmiPorts; }
    public String getMonitorBrand() { return monitorBrand; }
    public void setMonitorBrand(String monitorBrand) { this.monitorBrand = monitorBrand; }
    public BigDecimal getInches() { return inches; }
    public void setInches(BigDecimal inches) { this.inches = inches; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}