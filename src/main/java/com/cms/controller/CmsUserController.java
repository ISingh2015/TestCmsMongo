package com.cms.controller;

import com.cms.config.CmsJwtTokenProvider;
import com.cms.vo.CmsUser;
import com.cms.authentication.JwtAuthenticationResponse;
import com.cms.exception.CmsRecordNotFoundException;
import com.cms.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 06.06.2019
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CmsUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CmsJwtTokenProvider tokenProvider;

    @PostMapping("/cms-sign-in")
    public ResponseEntity<?> authenticateUser(String userNameorEmail, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userNameorEmail, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/cms-register-in")
    public ResponseEntity<?> registerUser(@Valid @RequestBody CmsUser cmsUser) {
        if(userRepository.existsByUserName(cmsUser.getUsername())) {
            return new ResponseEntity(new CmsRecordNotFoundException(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByUserEmail(cmsUser.getEmail())) {
            return new ResponseEntity(new CmsRecordNotFoundException(false, "Email is already taken!"), HttpStatus.BAD_REQUEST);
        }
        userRepository.save(cmsUser);
        return ResponseEntity.ok(cmsUser);
    }
}
