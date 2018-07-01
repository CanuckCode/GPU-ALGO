package com.gpualgo.service.dto;

import com.gpualgo.domain.OverlockSetting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OverlockSettingDTO {

    private long id;
    private long position;
    private String architecture;
    private String brand;
    private String model;
    private String algorithm;
    private long memoryCapacity;
    private long coresNumber;
    private long powerCapacity;
    private long votesUp;
    private long votesDown;

    public static OverlockSettingDTO buildFromEntity(OverlockSetting setting) {
        return OverlockSettingDTO
            .builder()
            .id(setting.getId())
            .architecture(setting.getGpuArchitecture())
            .brand(setting.getGpuBrand())
            .model(setting.getGpuModel())
            .algorithm(setting.getAlgorithm())
            .memoryCapacity(setting.getMemoryCapacity())
            .coresNumber(setting.getCoresNumber())
            .powerCapacity(setting.getPowerCapacity())
            .votesUp(setting.getVotesUp())
            .votesDown(setting.getVotesDown())
            .build();
    }

}
