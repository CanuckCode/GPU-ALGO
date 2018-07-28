package com.gpualgo.service;

import com.gpualgo.domain.OverlockSetting;
import com.gpualgo.domain.User;
import com.gpualgo.domain.Vote;
import com.gpualgo.repository.OverlockSettingRepository;
import com.gpualgo.repository.VoteRepository;
import com.gpualgo.service.dto.OverlockSettingDTO;
import com.gpualgo.service.dto.ResponseDTO;
import com.gpualgo.service.util.VoteStatus;
import com.gpualgo.util.Util;
import com.gpualgo.util.exception.VoteException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OverlockSettingService {

    private final OverlockSettingRepository overlockSettingRepository;
    private final VoteRepository voteRepository;

    @Autowired
    public OverlockSettingService(OverlockSettingRepository overlockSettingRepository, VoteRepository voteRepository) {
        this.overlockSettingRepository = overlockSettingRepository;
        this.voteRepository = voteRepository;
    }

    public void saveOverlockSetting(OverlockSettingDTO dto) {
        OverlockSetting setting = new OverlockSetting();
        setting.setAlgorithm(dto.getAlgorithm());
        setting.setCoresNumber(dto.getCoresNumber());
        setting.setGpuArchitecture(dto.getArchitecture());
        setting.setGpuBrand(dto.getBrand());
        setting.setGpuModel(dto.getModel());
        setting.setMemoryCapacity(dto.getMemoryCapacity());
        setting.setPowerCapacity(dto.getPowerCapacity());

        overlockSettingRepository.save(setting);
    }

    public ResponseDTO findSettings(
        String gpuArchitecture,
        String gpuBrand,
        String gpuModel,
        String algorithm,
        Pageable pageable,
        String searchCondition
    ) {
        if (!Util.isAllEmpty(gpuArchitecture, gpuBrand) && gpuModel != null && (gpuModel.equals("GENERIC NVIDIA") || gpuModel.equals("GENERIC AMD"))) {
            if (StringUtils.isEmpty(algorithm)) {
                List<OverlockSetting> settings = overlockSettingRepository.findAllByGpuArchitectureAndGpuModel(gpuArchitecture, gpuModel, pageable);;
                Integer count = overlockSettingRepository.countAllByGpuArchitectureAndGpuModel(gpuArchitecture, gpuModel);
                return buildResult(settings, count, searchCondition);
            } else {
                List<OverlockSetting> settings = overlockSettingRepository.findAllByGpuArchitectureAndGpuModelAndAlgorithm(gpuArchitecture, gpuModel, algorithm, pageable);
                Integer count = overlockSettingRepository.countAllByGpuArchitectureAndGpuModelAndAlgorithm(gpuArchitecture, gpuModel, algorithm);
                return buildResult(settings, count, searchCondition);
            }
        } else if (gpuArchitecture != null && Util.isAllEmpty(gpuBrand, gpuModel, algorithm)) {
            List<OverlockSetting> settings = overlockSettingRepository.findAllByGpuArchitecture(gpuArchitecture, pageable);
            int count = overlockSettingRepository.countAllByGpuArchitecture(gpuArchitecture);
            return buildResult(settings, count, searchCondition);
        } else if (gpuBrand != null && Util.isAllEmpty(gpuModel, algorithm)) {
            List<OverlockSetting> settings = overlockSettingRepository.findAllByGpuArchitectureAndGpuBrand(gpuArchitecture, gpuBrand, pageable);
            int count = overlockSettingRepository.countAllByGpuArchitectureAndGpuBrand(gpuArchitecture, gpuBrand);
            return buildResult(settings, count, searchCondition);
        } else if (gpuModel != null && Util.isAllEmpty(algorithm)) {
            List<OverlockSetting> settings = overlockSettingRepository.findAllByGpuArchitectureAndGpuBrandAndGpuModel(gpuArchitecture, gpuBrand, gpuModel, pageable);
            int count = overlockSettingRepository.countAllByGpuArchitectureAndGpuBrandAndGpuModel(gpuArchitecture, gpuBrand, gpuModel);
            return buildResult(settings, count, searchCondition);
        } else {
            List<OverlockSetting> settings = overlockSettingRepository.findAllByGpuArchitectureAndGpuBrandAndGpuModelAndAlgorithm(gpuArchitecture, gpuBrand, gpuModel, algorithm, pageable);
            int count = overlockSettingRepository.countAllByGpuArchitectureAndGpuBrandAndGpuModelAndAlgorithm(gpuArchitecture, gpuBrand, gpuModel, algorithm);
            return buildResult(settings, count, searchCondition);
        }
    }

    private ResponseDTO buildResult(List<OverlockSetting> settings, int count, String searchCondition) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setContent(Util.getResult(settings, searchCondition));
        responseDTO.setTotalElements(count);
        return responseDTO;
    }

    public void voteUp(long id, User user) throws VoteException {
        Vote existingVote = voteRepository.findAllBySettingIdAndUserIdAndVoteStatus(id, user.getId(), VoteStatus.UP);
        if (existingVote != null) throw new VoteException("User " + user.getId() + " have already voted for " + id + " setting");
        OverlockSetting setting = overlockSettingRepository.findById(id);
        setting.setVotesUp(setting.getVotesUp() + 1);
        overlockSettingRepository.save(setting);
        saveVote(user, setting, VoteStatus.UP);
    }

    public void voteDown(long id, User user) throws VoteException {
        Vote vote = voteRepository.findAllBySettingIdAndUserIdAndVoteStatus(id, user.getId(), VoteStatus.DOWN);
        if (vote != null) throw new VoteException("User " + user.getId() + " have already voted for " + id + " setting");
        OverlockSetting setting = overlockSettingRepository.findById(id);
        setting.setVotesDown(setting.getVotesDown() + 1);
        overlockSettingRepository.save(setting);
        saveVote(user, setting, VoteStatus.DOWN);
    }

    private void saveVote(User user, OverlockSetting setting, VoteStatus status) {
        Vote vote = new Vote();
        vote.setSetting(setting);
        vote.setUser(user);
        vote.setVoteStatus(status);
        voteRepository.save(vote);
    }

}
