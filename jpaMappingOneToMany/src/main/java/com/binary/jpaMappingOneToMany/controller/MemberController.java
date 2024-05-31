package com.binary.jpaMappingOneToMany.controller;

import com.binary.jpaMappingOneToMany.Dtos.AuthenticationRequestDto;
import com.binary.jpaMappingOneToMany.Dtos.AuthenticationResponseDto;
import com.binary.jpaMappingOneToMany.config.JwtUtil;
import com.binary.jpaMappingOneToMany.config.MemberUserDetailService;
import com.binary.jpaMappingOneToMany.model.Member;
import com.binary.jpaMappingOneToMany.service.MemberServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @Autowired
    private MemberServiceImple memberService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MemberUserDetailService memberDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Object> registerMember(@RequestBody Member member){
        return  new ResponseEntity<>(memberService.createMember(member), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> createMemberAuthToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword())
            );
        }catch (Exception e){
            throw  new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = memberDetailsService.loadUserByUsername(authenticationRequestDto.getUsername());
        final  String jwt = jwtUtil.generateToken(userDetails);

        return new ResponseEntity<>(new AuthenticationResponseDto(jwt), HttpStatus.CREATED);
    }


}
