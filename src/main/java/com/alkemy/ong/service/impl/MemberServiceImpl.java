package com.alkemy.ong.service.impl;


import com.alkemy.ong.dto.MemberRequest;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;

import com.alkemy.ong.dto.MemberResponse;
import com.alkemy.ong.exception.EmptyDataException;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.IMemberService;
import com.alkemy.ong.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements IMemberService{

	private final MemberRepository memberRepository;
	private final MemberMapper memberMapper;
	private final MessageSource messageSource;
	
	@Override
	public List<MemberResponse> getAllMembers(){
		String memberListIsEmpty = messageSource.getMessage("member.listEmpty", null, Locale.US);
		List<Member> listMember = memberRepository.findAll();
		List<MemberResponse> listMemberResponse = new ArrayList<>();
		listMember.stream().forEach(member -> {
			MemberResponse memberResponse = memberMapper.memberModel2DTO(member);
			listMemberResponse.add(memberResponse);
		});
        if(listMemberResponse.isEmpty()){
            throw new EmptyDataException(memberListIsEmpty);
        }		
		return listMemberResponse;
	}

    @Override
    public MemberResponse createMember(MemberRequest memberRequest) {
        Member member = new Member();
        member.setName(memberRequest.getName());
        member.setFacebookUrl(memberRequest.getFacebookUrl());
        member.setInstagramUrl(memberRequest.getInstagramUrl());
        member.setLinkedinUrl(memberRequest.getInstagramUrl());
        member.setImage(memberRequest.getImage());
        member.setDescription(memberRequest.getDescription());
        Member memberCreate = memberRepository.save(member);
        return memberMapper.memberModel2DTO(memberCreate);
    }

}

