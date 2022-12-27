package com.mnt.wellbook.web.rest;

import com.mnt.wellbook.config.Constants;
import com.mnt.wellbook.domain.User;
import com.mnt.wellbook.repository.UserRepository;
import com.mnt.wellbook.security.AuthoritiesConstants;
import com.mnt.wellbook.service.MailService;

import java.io.IOException;
import com.mnt.wellbook.service.UserService;
import com.mnt.wellbook.service.dto.AdminUserDTO;
import com.mnt.wellbook.web.rest.errors.BadRequestAlertException;
import com.mnt.wellbook.web.rest.errors.EmailAlreadyUsedException;
import com.mnt.wellbook.web.rest.errors.LoginAlreadyUsedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.StringUtils;
import java.util.*;
import java.util.Collections;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import com.mnt.wellbook.web.rest.vm.KeyAndPasswordVM;

import com.mnt.wellbook.web.rest.vm.ManagedUserVM;


import com.mnt.wellbook.repository.KeyRepository;


import com.mnt.wellbook.domain.Section;
import com.mnt.wellbook.domain.Key;

import com.mnt.wellbook.web.rest.errors.InvalidPasswordException;
import com.mnt.wellbook.web.rest.errors.BadRequestAlertException;

import com.mnt.wellbook.service.FileStorageService;

import com.mnt.wellbook.domain.Document;

import com.mnt.wellbook.service.IFileSytemStorage;

import com.mnt.wellbook.service.KeyService;


import com.mnt.wellbook.service.SectionService;

@RestController
@RequestMapping("/api")
public class FileResource {
	@Autowired
    IFileSytemStorage storageService;
	
	@Autowired
	KeyService keyService;
	
	@Autowired
	SectionService sectionService;


	
	@Value("${jhipster.clientApp.name}")
    private String applicationName;
	

    @PostMapping("/uploadfile")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\") "+
    		" || hasAuthority(\"" + AuthoritiesConstants.CLIENT + "\")")
    public ResponseEntity<List<String>> uploadFile (@RequestParam("file") MultipartFile[] files,@RequestParam("sectionId") Long sectionId,
    												@RequestParam("remark") String remark) {
    	
            List<String> responses = Arrays
                    .asList(files)
                    .stream()
                    .map(
                        file -> {
                            String upfile = storageService.saveFile(file,sectionId,remark);
                            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                    .path("/api/download/")
                                    .path(upfile)
                                    .toUriString();
                            return new String(upfile+" File uploaded successfuly!");
                        }
                    )
                    .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
    
    
    @GetMapping("/getfile/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable Long id) {
      Resource resource = storageService.loadFile(id);

      return ResponseEntity.ok()
              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
              .body(resource);
    }
    
    
    @PostMapping("/addSection")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\") "+
    		" || hasAuthority(\"" + AuthoritiesConstants.CLIENT + "\")")
    public ResponseEntity<Map<String, Object>> createSection (@RequestParam("sectionName") String sectionName,@RequestParam("description")String description,
    															@RequestParam("clientId")Long clientId,@RequestParam("staffId")Long staffId) {
    	
    	Map<String, Object> result = sectionService.createSection(sectionName,description,clientId,staffId);

            	
         return ResponseEntity.status(HttpStatus.OK).body(result);
    }
	
	
}

	