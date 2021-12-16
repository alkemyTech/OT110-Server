package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberRequest;
import com.alkemy.ong.dto.MemberResponse;

import javax.validation.Valid;

public interface IMemberService {

    public MemberResponse createMember(@Valid MemberRequest memberRequest);
}

