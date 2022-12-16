package com.mnt.wellbook.service;

import com.mnt.wellbook.config.Constants;
import com.mnt.wellbook.domain.Authority;
import com.mnt.wellbook.domain.User;
import com.mnt.wellbook.repository.AuthorityRepository;
import com.mnt.wellbook.repository.UserRepository;
import com.mnt.wellbook.security.AuthoritiesConstants;
import com.mnt.wellbook.security.SecurityUtils;
import com.mnt.wellbook.service.dto.AdminUserDTO;
import com.mnt.wellbook.service.dto.UserDTO;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.security.RandomUtil;

import com.mnt.wellbook.repository.ClientRepository;

import com.mnt.wellbook.repository.KeyRepository;


/**
 * Service class for managing users.
 */
@Service
@Transactional
public class KeyService {
	
	
	private final KeyRepository keyRepository;
	
	
	public KeyService(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;

    }
	
	
	    public void updateKeyStatus(String alphanumericKey) {

	    	keyRepository.findOneByAlphanumericKey(alphanumericKey).ifPresent(key ->{
	            key.setUsedStatus("used");
	        });

	    }
}