package com.alkemy.ong.mapper;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.MemberResponse;
import com.alkemy.ong.model.Member;

@Component
public class MemberMapper {

	public MemberResponse memberModel2DTO(Member member) {
		MemberResponse memberResponse = new MemberResponse();
		memberResponse.setName(member.getName());
		memberResponse.setFacebookUrl(member.getFacebookUrl());
		memberResponse.setInstagramUrl(member.getInstagramUrl());
		memberResponse.setLinkedinUrl(member.getLinkedinUrl());
		memberResponse.setImage(member.getImage());
		memberResponse.setDescription(member.getDescription());
		return memberResponse;
	}
	
}
