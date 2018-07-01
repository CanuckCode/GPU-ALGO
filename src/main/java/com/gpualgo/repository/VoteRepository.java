package com.gpualgo.repository;

import com.gpualgo.domain.OverlockSetting;
import com.gpualgo.domain.Vote;
import com.gpualgo.service.util.VoteStatus;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {

    Vote findAllBySettingIdAndUserIdAndVoteStatus(Long settingId, Long userId, VoteStatus status);

}
