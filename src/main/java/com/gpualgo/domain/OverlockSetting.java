package com.gpualgo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "overlock_settings")
public class OverlockSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private long id;

    @Column(name = "gpu_architecture")
    private String gpuArchitecture;

    @Column(name = "gpu_brand")
    private String gpuBrand;

    @Column(name = "gpu_model")
    private String gpuModel;

    @Column(name = "algorithm")
    private String algorithm;

    @Column(name = "memory_capacity")
    private long memoryCapacity;

    @Column(name = "cores_number")
    private long coresNumber;

    @Column(name = "power_capacity")
    private long powerCapacity;

    @Column(name = "votes_up")
    private long votesUp;

    @Column(name = "votes_down")
    private long votesDown;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGpuArchitecture() {
        return gpuArchitecture;
    }

    public void setGpuArchitecture(String gpuArchitecture) {
        this.gpuArchitecture = gpuArchitecture;
    }

    public String getGpuBrand() {
        return gpuBrand;
    }

    public void setGpuBrand(String gpuBrand) {
        this.gpuBrand = gpuBrand;
    }

    public String getGpuModel() {
        return gpuModel;
    }

    public void setGpuModel(String gpuModel) {
        this.gpuModel = gpuModel;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public long getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(long memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public long getCoresNumber() {
        return coresNumber;
    }

    public void setCoresNumber(long coresNumber) {
        this.coresNumber = coresNumber;
    }

    public long getPowerCapacity() {
        return powerCapacity;
    }

    public void setPowerCapacity(long powerCapacity) {
        this.powerCapacity = powerCapacity;
    }

    public long getVotesUp() {
        return votesUp;
    }

    public void setVotesUp(long votesUp) {
        this.votesUp = votesUp;
    }

    public long getVotesDown() {
        return votesDown;
    }

    public void setVotesDown(long votesDown) {
        this.votesDown = votesDown;
    }
}
