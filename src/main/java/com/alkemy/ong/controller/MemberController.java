package com.alkemy.ong.controller;

import java.util.List;

import com.alkemy.ong.dto.MemberRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.MemberResponse;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.IMemberService;

import lombok.AllArgsConstructor;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {

	private final IMemberService memberService;
	
	@GetMapping
	@PreAuthorize(SecurityConstant.ADMIN)
	public ResponseEntity<List<MemberResponse>> getAllMembers(){
		List<MemberResponse> listMembers = memberService.getAllMembers();
		return new ResponseEntity<List<MemberResponse>>(listMembers, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize(SecurityConstant.USER)
	public ResponseEntity<MemberResponse> createMember(@Valid @RequestBody MemberRequest memberRequest){
		return new ResponseEntity<>(memberService.createMember(memberRequest), HttpStatus.CREATED);
	}
	
}

