package com.gpualgo.repository;

import com.gpualgo.domain.OverlockSetting;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OverlockSettingRepository extends PagingAndSortingRepository<OverlockSetting, Long> {

    List<OverlockSetting> findAllByGpuArchitecture(String gpuArchitecture, Pageable pageable);
    List<OverlockSetting> findAllByGpuArchitectureAndGpuBrand(String gpuArchitecture, String gpuBrand, Pageable pageable);
    List<OverlockSetting> findAllByGpuArchitectureAndGpuBrandAndGpuModel(String gpuArchitecture, String gpuBrand, String gpuModel, Pageable pageable);
    List<OverlockSetting> findAllByGpuArchitectureAndGpuBrandAndGpuModelAndAlgorithm(String gpuArchitecture, String gpuBrand, String gpuModel, String algorithm, Pageable pageable);
    OverlockSetting findById(long id);

    List<OverlockSetting> findAllByGpuArchitectureAndGpuModel(String architecture, String model, Pageable pageable);
    List<OverlockSetting> findAllByGpuArchitectureAndGpuModelAndAlgorithm(String architecture, String model, String algorithm, Pageable pageable);


    Integer countAllByGpuArchitecture(String gpuArchitecture);
    Integer countAllByGpuArchitectureAndGpuBrand(String gpuArchitecture, String gpuBrand);
    Integer countAllByGpuArchitectureAndGpuBrandAndGpuModel(String gpuArchitecture, String gpuBrand, String gpuModel);
    Integer countAllByGpuArchitectureAndGpuBrandAndGpuModelAndAlgorithm(String gpuArchitecture, String gpuBrand, String gpuModel, String algorithm);

    Integer countAllByGpuArchitectureAndGpuModel(String architecture, String model);
    Integer countAllByGpuArchitectureAndGpuModelAndAlgorithm(String architecture, String model, String algorithm);

}
