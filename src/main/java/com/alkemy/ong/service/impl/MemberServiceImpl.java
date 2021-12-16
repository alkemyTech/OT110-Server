package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.MemberRequest;
import com.alkemy.ong.dto.MemberResponse;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.IMemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements IMemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponse createMember(MemberRequest memberRequest) {
        Member member = new Member();
        member.setName(memberRequest.getName());
        member.setFacebookUrl(memberRequest.getFacebookUrl());
        member.setInstagramUrl(memberRequest.getInstagramUrl());
        member.setLinkedinUrl(memberRequest.getInstagramUrl());
        member.setImage(memberRequest.getImage());
        member.setDescription(memberRequest.getDescription());
        member.setDateCreation(LocalDateTime.now());
        member.setDateUpdate(LocalDateTime.now());
        Member memberCreate = memberRepository.save(member);
        return generateMember(memberCreate);
    }

    private MemberResponse generateMember(Member member){
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setName(member.getName());
        memberResponse.setFacebookUrl(member.getFacebookUrl());
        memberResponse.setInstagramUrl(member.getInstagramUrl());
        memberResponse.setLinkedinUrl(member.getInstagramUrl());
        memberResponse.setImage(member.getImage());
        memberResponse.setDescription(member.getDescription());
        memberResponse.setDateCreation(member.getDateCreation().toString());
        memberResponse.setDateUpdate(member.getDateUpdate().toString());
        return memberResponse;
    }

}

