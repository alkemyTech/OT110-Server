package com.alkemy.ong.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

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
	
}
