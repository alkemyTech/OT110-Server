package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberRequest;
import com.alkemy.ong.dto.MemberResponse;

import javax.validation.Valid;
import java.util.List;


public interface IMemberService {

	List<MemberResponse> getAllMembers();
    public MemberResponse createMember(@Valid MemberRequest memberRequest);
}

