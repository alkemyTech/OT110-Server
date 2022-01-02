package com.alkemy.ong.controller;


import com.alkemy.ong.dto.MemberRequest;
import com.alkemy.ong.dto.MemberResponse;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.IMemberService;
import com.alkemy.ong.util.docs.CategoryConstantDocs;
import com.alkemy.ong.util.docs.MemberConstantDocs;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/members")
@Api(value = MemberConstantDocs.MEMBER)
public class MemberController {

	private final IMemberService memberService;
	
	@GetMapping
	@PreAuthorize(SecurityConstant.ADMIN)
	@ApiOperation(value = MemberConstantDocs.MEMBER_FIND_ALL, response = MemberResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = MemberConstantDocs.MEMBER_GET_200_OK),
            @ApiResponse(code = 404, message = MemberConstantDocs.MEMBER_GET_404_NOT_FOUND)
    })
	public ResponseEntity<Map<String, Object>> getAllMembers(
			@ApiParam(required = false) Pageable pageable,
	        @ApiParam(value = MemberConstantDocs.MEMBER_GET_PARAM_PAGE_NUMBER, required = true, example = "0")
			@RequestParam(name = "page") Integer pageNo, 
			HttpServletRequest request){
		String endPointName = request.getRequestURL() + "?" + request.getParameterNames().nextElement() + "=";
		Map<String, Object> listMembers = memberService.getAllMembers(pageNo, endPointName);
		return new ResponseEntity<>(listMembers, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@PreAuthorize(SecurityConstant.USER)
	@ApiOperation(value = MemberConstantDocs.MEMBER_UPDATE, response = MemberResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = MemberConstantDocs.MEMBER_UPDATE_OK),
            @ApiResponse(code = 404, message = MemberConstantDocs.MEMBER_404_NOT_FOUND)
    })
	public ResponseEntity<?> update(
			@ApiParam(value = MemberConstantDocs.MEMBER_UPDATE_PARAM_MEMBER_REQUEST, required = true)
			@Valid @RequestBody MemberRequest memberRequest, 
			@ApiParam(value = MemberConstantDocs.MEMBER_UPDATE_PARAM_ID, required = true, example = "1")
			@PathVariable("id") Long id){
		return new ResponseEntity<>(memberService.update(memberRequest, id), HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize(SecurityConstant.USER)
	@ApiOperation(value = MemberConstantDocs.MEMBER_CREATE, response = MemberResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = MemberConstantDocs.MEMBER_CREATED)
    })
	public ResponseEntity<MemberResponse> createMember(
	@ApiParam(value = MemberConstantDocs.MEMBER_CREATED_PARAM_MEMBER_REQUEST, required = true)
	@Valid @RequestBody MemberRequest memberRequest){
		return new ResponseEntity<>(memberService.createMember(memberRequest), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize(SecurityConstant.ADMIN)
	public ResponseEntity<Member> deleteMember(@PathVariable("id") Long id){
		return new ResponseEntity<>(memberService.deleteMember(id), HttpStatus.OK);
	}

}

