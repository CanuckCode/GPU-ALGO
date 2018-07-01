package com.gpualgo.repository;

import com.gpualgo.domain.OverlockSetting;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OverlockSettingRepository extends PagingAndSortingRepository<OverlockSetting, Long> {

    List<OverlockSetting> findAllByGpuArchitectureOrderByVotesUpDescVotesDownAsc(String gpuArchitecture, Pageable pageable);
    List<OverlockSetting> findAllByGpuArchitectureAndGpuBrandOrderByVotesUpDescVotesDownAsc(String gpuArchitecture, String gpuBrand, Pageable pageable);
    List<OverlockSetting> findAllByGpuArchitectureAndGpuBrandAndGpuModelOrderByVotesUpDescVotesDownAsc(String gpuArchitecture, String gpuBrand, String gpuModel, Pageable pageable);
    List<OverlockSetting> findAllByGpuArchitectureAndGpuBrandAndGpuModelAndAlgorithmOrderByVotesUpDescVotesDownAsc(String gpuArchitecture, String gpuBrand, String gpuModel, String algorithm, Pageable pageable);
    OverlockSetting findById(long id);

    Integer countAllByGpuArchitecture(String gpuArchitecture);
    Integer countAllByGpuArchitectureAndGpuBrand(String gpuArchitecture, String gpuBrand);
    Integer countAllByGpuArchitectureAndGpuBrandAndGpuModel(String gpuArchitecture, String gpuBrand, String gpuModel);
    Integer countAllByGpuArchitectureAndGpuBrandAndGpuModelAndAlgorithm(String gpuArchitecture, String gpuBrand, String gpuModel, String algorithm);

}
