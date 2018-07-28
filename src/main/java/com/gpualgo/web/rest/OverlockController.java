package com.gpualgo.web.rest;

import com.gpualgo.domain.User;
import com.gpualgo.repository.UserRepository;
import com.gpualgo.security.SecurityUtils;
import com.gpualgo.service.OverlockSettingService;
import com.gpualgo.service.dto.OverlockSettingDTO;
import com.gpualgo.service.dto.ResponseDTO;
import com.gpualgo.util.exception.VoteException;
import com.gpualgo.web.rest.errors.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/overlock")
public class OverlockController {

    private final OverlockSettingService settingService;
    private final UserRepository userRepository;

    @Autowired
    public OverlockController(OverlockSettingService settingService, UserRepository repository) {
        this.settingService = settingService;
        this.userRepository = repository;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveOverlockSetting(@RequestBody OverlockSettingDTO dto) {
        try {
            settingService.saveOverlockSetting(dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/findSettings")
    public ResponseEntity<?> findSettings(
        String architecture,
        String brand,
        String model,
        String algorithm,
        Integer page,
        Integer size,
        String sortProperty,
        String sortIt,
        String searchCondition
    ) {
        try {
            Sort sort = new Sort(Sort.Direction.DESC, "votesUp");
            PageRequest pageable = new PageRequest(page - 1, size, sort);
            if (sortProperty != null && sortIt != null) {
                pageable = new PageRequest(page - 1, size, Sort.Direction.fromString(sortIt), sortProperty);
            }
            ResponseDTO settings = settingService.findSettings(architecture, brand, model, algorithm, pageable, searchCondition);
            return ResponseEntity.ok().body(settings);
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setSuccess(false);
            return ResponseEntity.ok().body(responseDTO);
        }
    }

    @GetMapping("/vote/up")
    public ResponseEntity<?> voteUp(long id) {
        try {
            final String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
            Optional<User> user = userRepository.findOneByLogin(userLogin);
            if (!user.isPresent()) {
                throw new InternalServerErrorException("User could not be found");
            }
            settingService.voteUp(id, user.get());
            return ResponseEntity.ok().build();
        } catch (VoteException e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setSuccess(false);
            return ResponseEntity.ok().body(responseDTO);
        }
    }

    @GetMapping("/vote/down")
    public ResponseEntity<?> voteDown(long id) {
        try {
            final String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
            Optional<User> user = userRepository.findOneByLogin(userLogin);
            if (!user.isPresent()) {
                throw new InternalServerErrorException("User could not be found");
            }
            settingService.voteDown(id, user.get());
            return ResponseEntity.ok().build();
        } catch (VoteException e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setSuccess(false);
            return ResponseEntity.ok().body(responseDTO);
        }
    }



}
