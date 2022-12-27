package com.mnt.wellbook.service;

import com.mnt.wellbook.config.Constants;
import com.mnt.wellbook.domain.Authority;
import com.mnt.wellbook.domain.User;
import com.mnt.wellbook.domain.Section;
import com.mnt.wellbook.domain.Staff;
import com.mnt.wellbook.domain.Client;
import com.mnt.wellbook.repository.AuthorityRepository;
import com.mnt.wellbook.repository.UserRepository;
import com.mnt.wellbook.security.AuthoritiesConstants;
import com.mnt.wellbook.security.SecurityUtils;
import com.mnt.wellbook.service.dto.AdminUserDTO;
import com.mnt.wellbook.service.dto.UserDTO;


import java.time.LocalDateTime;

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
import com.mnt.wellbook.repository.SectionRepository;
import com.mnt.wellbook.repository.StaffRepository;

import com.mnt.wellbook.web.rest.vm.ManagedUserVM;

import com.mnt.wellbook.service.KeyService;


import com.mnt.wellbook.domain.Key;
import javax.validation.Valid;

@Service
@Transactional
public class SectionService {
	
	
	private final SectionRepository sectionRepository;
	
	private final ClientRepository clientRepository;
	
	private final StaffRepository staffRepository;
	
	
	
	public SectionService(SectionRepository sectionRepository,ClientRepository clientRepository,StaffRepository staffRepository) {
        this.sectionRepository = sectionRepository;
        this.clientRepository=clientRepository;
        this.staffRepository=staffRepository;
    }
	
	
	    public List<Map<String, Object>> createSection(ArrayList<String> sectionNameList,String description,Long clientId,Long staffId) {
	    	
	    	List<Map<String, Object>> finalList = new ArrayList<>();
	    	
	    	for(String sectionName : sectionNameList) {
		    	Section section=new Section();
		    	section.setName(sectionName);
		    	section.setDescription(description);
		    	Client client=clientRepository.findById(clientId).get();
		    	section.setClient(client);
		    	
		    	Staff staff=staffRepository.findById(staffId).get();
		    	section.setStaff(staff);
		    	
		    	
		    	section=sectionRepository.save(section);
		    	
		    	Map<String, Object> objMap = new LinkedHashMap<String, Object>();
	            objMap.put("id", section.getSectionId());
	            objMap.put("sectionName", section.getName());
	            finalList.add(objMap);
	    	}
	    	
	    	return finalList;

	    }
}