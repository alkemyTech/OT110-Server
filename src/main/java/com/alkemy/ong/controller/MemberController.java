package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberRequest;
import com.alkemy.ong.dto.MemberResponse;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.IMemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@AllArgsConstructor
public class MemberController {

    private final IMemberService memberService;

    @PostMapping
    @PreAuthorize(SecurityConstant.USER)
    public ResponseEntity<MemberResponse> createMember(@Valid @RequestBody MemberRequest memberRequest){
        return new ResponseEntity<>(memberService.createMember(memberRequest), HttpStatus.CREATED);
    }
}

