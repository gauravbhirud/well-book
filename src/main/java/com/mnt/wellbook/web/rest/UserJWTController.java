package com.mnt.wellbook.web.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mnt.wellbook.security.jwt.JWTFilter;
import com.mnt.wellbook.security.jwt.TokenProvider;
import com.mnt.wellbook.web.rest.vm.LoginVM;
import com.mnt.wellbook.domain.User;
import com.mnt.wellbook.repository.UserRepository;

import com.mnt.wellbook.web.rest.errors.BadRequestAlertException;

import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.mnt.wellbook.service.dto.LoginDTO;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    
    private final UserDetailsService userDetailsService;

    private final UserRepository userRepository;
    
    public UserJWTController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder,UserDetailsService userDetailsService,
    		UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    
    
    @PostMapping("/authenticate")
   	public ResponseEntity<?> authorizes(@Valid @RequestBody LoginVM loginVM) {
   		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
   				loginVM.getUsername(),
   				loginVM.getPassword()
   				);

   		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
   		SecurityContextHolder.getContext().setAuthentication(authentication);
           User user = userRepository.findOneByEmail(loginVM.getUsername());
           String jwt = tokenProvider.createToken(authentication, loginVM.isRememberMe());
           System.out.println("jwt ________ : " + jwt);
           UserDetails userDetails = userDetailsService.loadUserByUsername(loginVM.getUsername());
           GrantedAuthority role = userDetails.getAuthorities().stream().findFirst().get();

           
           if(user.getJwtToken() != null){

//               boolean userStatus = checkUserStatus(user, role.toString());
//               if(userStatus){
//                   
           
                   LoginDTO loginDto = new LoginDTO();
           
                   loginDto.setJwtToken(jwt);
                   loginDto.setRole(userDetails.getAuthorities());
                   loginDto.setLogin(user.getLogin());
                   loginDto.setLoginStatus(false);
                   
                   HttpHeaders httpHeaders = new HttpHeaders();
                   httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
                   return new ResponseEntity<>(loginDto, httpHeaders, HttpStatus.OK);
//               }
                 
           }

//               boolean userStatus = checkUserStatus(user, role.toString());
//               if(userStatus) {
                   
             
                   LoginDTO loginDto = new LoginDTO();
             
                   loginDto.setJwtToken(jwt);
                   loginDto.setRole(userDetails.getAuthorities());
                   loginDto.setLogin(user.getLogin());
                   loginDto.setLoginStatus(false);
             
                   user.setJwtToken(jwt);
                   userRepository.save(user);
             
                   HttpHeaders httpHeaders = new HttpHeaders();
                   httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
                   return new ResponseEntity<>(loginDto, httpHeaders, HttpStatus.OK);

//               }
              
//               throw new BadRequestAlertException("Not Activated", "Sign-In", "User company not active");
             //return new ResponseEntity<> ("User company not active", HttpStatus.BAD_REQUEST);
                   	
   	} 

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
