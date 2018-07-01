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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

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

    public ResponseDTO findSettings(String gpuArchitecture, String gpuBrand, String gpuModel, String algorithm, Pageable pageable) {
        if (gpuArchitecture != null && Util.isAllEmpty(gpuBrand, gpuModel, algorithm)) {
            List<OverlockSetting> settings = overlockSettingRepository.findAllByGpuArchitectureOrderByVotesUpDescVotesDownAsc(gpuArchitecture, pageable);
            int count = overlockSettingRepository.countAllByGpuArchitecture(gpuArchitecture);
            return buildResult(settings, count);
        } else if (gpuBrand != null && Util.isAllEmpty(gpuModel, algorithm)) {
            List<OverlockSetting> settings = overlockSettingRepository.findAllByGpuArchitectureAndGpuBrandOrderByVotesUpDescVotesDownAsc(gpuArchitecture, gpuBrand, pageable);
            int count = overlockSettingRepository.countAllByGpuArchitectureAndGpuBrand(gpuArchitecture, gpuBrand);
            return buildResult(settings, count);
        } else if (gpuModel != null && Util.isAllEmpty(algorithm)) {
            List<OverlockSetting> settings = overlockSettingRepository.findAllByGpuArchitectureAndGpuBrandAndGpuModelOrderByVotesUpDescVotesDownAsc(gpuArchitecture, gpuBrand, gpuModel, pageable);
            int count = overlockSettingRepository.countAllByGpuArchitectureAndGpuBrandAndGpuModel(gpuArchitecture, gpuBrand, gpuModel);
            return buildResult(settings, count);
        } else {
            List<OverlockSetting> settings = overlockSettingRepository.findAllByGpuArchitectureAndGpuBrandAndGpuModelAndAlgorithmOrderByVotesUpDescVotesDownAsc(gpuArchitecture, gpuBrand, gpuModel, algorithm, pageable);
            int count = overlockSettingRepository.countAllByGpuArchitectureAndGpuBrandAndGpuModelAndAlgorithm(gpuArchitecture, gpuBrand, gpuModel, algorithm);
            return buildResult(settings, count);
        }
    }

    private ResponseDTO buildResult(List<OverlockSetting> settings, int count) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setContent(Util.getResult(settings));
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
