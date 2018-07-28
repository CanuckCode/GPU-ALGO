package com.gpualgo.util;

import com.gpualgo.domain.OverlockSetting;
import com.gpualgo.service.dto.OverlockSettingDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Util {

    public static boolean isAllEmpty(String... args) {
        Optional<String> nonNullValue = Arrays.stream(args).filter(StringUtils::isNotEmpty).findFirst();
        return !nonNullValue.isPresent();
    }

    public static List<OverlockSettingDTO> getResult(List<OverlockSetting> settings, String searchCondition) {
        return settings
            .stream()
            .filter(e -> {
                if (StringUtils.isNotEmpty(searchCondition)) {
                    return e.getGpuArchitecture().contains(searchCondition) || e.getGpuBrand().contains(searchCondition) ||
                        e.getGpuModel().contains(searchCondition) || e.getAlgorithm().contains(searchCondition);
                }
                return true;
            })
            .map(OverlockSettingDTO::buildFromEntity)
            .sorted((f, s) -> Math.toIntExact(s.getVotesUp() - f.getVotesUp()))
            .collect(Collectors.toList());
    }

}
