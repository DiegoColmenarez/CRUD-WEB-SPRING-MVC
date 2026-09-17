package org.exercise7.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.exercise7.model.enums.*;
import java.math.BigDecimal;

@Entity
@Table(name = "computers")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Brand is required")
    @Column(name = "marca", nullable = false, length = 50)
    private String brand;

    @NotNull(message = "Category is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false, length = 50)
    private Category category;

    @NotBlank(message = "CPU brand is required")
    @Column(name = "marca_cpu", nullable = false, length = 100)
    private String cpuBrand;

    @NotNull(message = "CPU speed is required")
    @Positive(message = "CPU speed must be greater than 0")
    @Column(name = "velocidad_cpu", nullable = false, precision = 5, scale = 2)
    private BigDecimal cpuSpeed;

    @NotNull(message = "RAM technology is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "tecnologia_ram", nullable = false, length = 20)
    private RamTechnology ramTechnology;

    @NotNull(message = "RAM capacity is required")
    @Positive(message = "RAM capacity must be greater than 0")
    @Column(name = "capacidad_ram", nullable = false)
    private Integer ramCapacity;

    @NotNull(message = "Disk technology is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "tecnologia_disco", nullable = false, length = 20)
    private DiskTechnology diskTechnology;

    @NotNull(message = "Disk capacity is required")
    @Positive(message = "Disk capacity must be greater than 0")
    @Column(name = "capacidad_disco", nullable = false)
    private Integer diskCapacity;

    @NotNull(message = "Number of USB ports is required")
    @PositiveOrZero(message = "USB ports cannot be negative")
    @Column(name = "num_puertos_usb", nullable = false)
    private Integer usbPorts;

    @NotNull(message = "Number of HDMI ports is required")
    @PositiveOrZero(message = "HDMI ports cannot be negative")
    @Column(name = "num_puertos_hdmi", nullable = false)
    private Integer hdmiPorts;

    @NotBlank(message = "Monitor brand is required")
    @Column(name = "marca_monitor", nullable = false, length = 100)
    private String monitorBrand;

    @NotNull(message = "Inches are required")
    @Positive(message = "Inches must be greater than 0")
    @Max(value = 80, message = "Maximum allowed size is 80 inches")
    @Column(name = "pulgadas", nullable = false, precision = 5, scale = 2)
    private BigDecimal inches;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    @Max(value = 1000000, message = "Price exceeds the allowed limit")
    @Column(name = "precio", nullable = false, precision = 9, scale = 2)
    private BigDecimal price;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public String getCpuBrand() { return cpuBrand; }
    public void setCpuBrand(String cpuBrand) { this.cpuBrand = cpuBrand; }
    public BigDecimal getCpuSpeed() { return cpuSpeed; }
    public void setCpuSpeed(BigDecimal cpuSpeed) { this.cpuSpeed = cpuSpeed; }
    public RamTechnology getRamTechnology() { return ramTechnology; }
    public void setRamTechnology(RamTechnology ramTechnology) { this.ramTechnology = ramTechnology; }
    public Integer getRamCapacity() { return ramCapacity; }
    public void setRamCapacity(Integer ramCapacity) { this.ramCapacity = ramCapacity; }
    public DiskTechnology getDiskTechnology() { return diskTechnology; }
    public void setDiskTechnology(DiskTechnology diskTechnology) { this.diskTechnology = diskTechnology; }
    public Integer getDiskCapacity() { return diskCapacity; }
    public void setDiskCapacity(Integer diskCapacity) { this.diskCapacity = diskCapacity; }
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