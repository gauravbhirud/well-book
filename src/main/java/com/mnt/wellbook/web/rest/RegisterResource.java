package com.mnt.wellbook.web.rest;

import com.mnt.wellbook.config.Constants;
import com.mnt.wellbook.domain.User;
import com.mnt.wellbook.repository.UserRepository;
import com.mnt.wellbook.security.AuthoritiesConstants;
import com.mnt.wellbook.service.MailService;
import com.mnt.wellbook.service.UserService;
import com.mnt.wellbook.service.dto.AdminUserDTO;
import com.mnt.wellbook.web.rest.errors.BadRequestAlertException;
import com.mnt.wellbook.web.rest.errors.EmailAlreadyUsedException;
import com.mnt.wellbook.web.rest.errors.LoginAlreadyUsedException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.Collections;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

import com.mnt.wellbook.domain.Key;

import com.mnt.wellbook.web.rest.errors.InvalidPasswordException;
import com.mnt.wellbook.web.rest.errors.BadRequestAlertException;

@RestController
@RequestMapping("/api")
public class RegisterResource {
	
	@Autowired
	KeyRepository keyRepository;
	
	@Autowired
    UserService userService;

      /**
     * {@code POST  company-register} : register the freight Forwarding Company by self.
     * @return the ResponseEntity with status 201 (CREATED).
     * @param ffCompanyDTO the managed  View Model.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     */
    @PostMapping("/client/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> clientRegister(@Valid @RequestBody ManagedUserVM managedUserVM) {
    	
    	if (isPasswordLengthInvalid(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }
    	
    	Key key = keyRepository.findOneByAlphanumericKeyIgnoreCase(managedUserVM.getAlphanumericKey());
        if(key == null){
            throw new BadRequestAlertException("key not found", "Client", managedUserVM.getEmail().toString());
        }
        
        if(key.getUsedStatus.equals("used")) {
        	throw new BadRequestAlertException("key already used", "Client", managedUserVM.getAlphanumericKey().toString());
        }
        
        if(!key.getEmail().equals(managedUserVM.getEmail())){
            throw new BadRequestAlertException("Register Email not match", "Client", managedUserVM.getEmail().toString());
        }
        userService.registerClient(managedUserVM, key);
      
    }
    
    
    @PostMapping("/staff/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> staffRegister(@Valid @RequestBody ManagedUserVM managedUserVM) {
    	
    	if (isPasswordLengthInvalid(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }
    	
    	Key key = keyRepository.findOneByAlphanumericKeyIgnoreCase(managedUserVM.getAlphanumericKey());
        if(key == null){
            throw new BadRequestAlertException("key not found", "Staff", managedUserVM.getEmail().toString());
        }
        
        if(key.getUsedStatus.equals("used")) {
        	throw new BadRequestAlertException("key already used", "Staff", managedUserVM.getAlphanumericKey().toString());
        }
        
        if(!key.getEmail().equals(managedUserVM.getEmail())){
            throw new BadRequestAlertException("Register Email not match", "Staff", managedUserVM.getEmail().toString());
        }
        userService.registerStaff(managedUserVM, key);
       

    }
    
    private static boolean isPasswordLengthInvalid(String password) {
        return (
            StringUtils.isEmpty(password) ||
            password.length() < ManagedUserVM.PASSWORD_MIN_LENGTH ||
            password.length() > ManagedUserVM.PASSWORD_MAX_LENGTH
        );
    }










}
