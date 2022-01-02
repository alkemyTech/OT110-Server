package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.MemberRequest;
import com.alkemy.ong.dto.MemberResponse;
import com.alkemy.ong.exception.EmptyDataException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.IMemberService;
import com.alkemy.ong.util.UpdateFields;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements IMemberService{

	public String endpoint;
	public final Integer SIZE = 10;
	private final MemberRepository memberRepository;
	private final MemberMapper memberMapper;
	private final MessageSource messageSource;
	private final UpdateFields updateFields;

	@Override
	public Map<String, Object> getAllMembers(Integer pageNo, String endPointName){

		String memberListIsEmpty = messageSource.getMessage("member.listEmpty", null, Locale.US);
		String memberLastPage = messageSource.getMessage("member.lastPage", null, Locale.US);
		final String membersName = "Members";
		final String nextPageName = "Next Page";
		final String previousPageName = "Previous Page";
		endpoint = endPointName;

		Pageable paging = PageRequest.of(pageNo, SIZE);
		Page<Member> pageMember = memberRepository.findAll(paging);
		int lastPage = pageMember.getTotalPages()-1;

		if(pageNo > lastPage){
			throw new NotFoundException(memberLastPage + pageMember.getTotalPages());
		}

		List<MemberResponse> memberResponse = pageMember.map(memberMapper::memberModel2DTO).toList();

		if (memberResponse.isEmpty()){
			throw new EmptyDataException(memberListIsEmpty);
		}

		String nextPage = nextPageEndpoint(pageNo.intValue(),lastPage);
		String previousPage = previousPageEndpoint(pageNo.intValue());

		Map<String, Object> response = new HashMap<>();
		response.put(membersName, memberResponse);
		response.put(nextPageName, nextPage);
		response.put(previousPageName, previousPage);

		return response;
	}

	private String nextPageEndpoint(Integer actual, int lastPageNo){
		if (actual.intValue() >= lastPageNo){
			return null;
		}
		actual++;
		return endpoint.concat(actual.toString());
	}

	private String previousPageEndpoint(Integer actual){
		if (actual.intValue() == 0){
			return null;
		}
		actual--;
		return endpoint.concat(actual.toString());
	}

	@Transactional
	@Override
	public MemberResponse update(MemberRequest memberRequest, Long id){
		String memberNotFound = messageSource.getMessage("member.notFound", null, Locale.US);

		Member member = memberRepository.findById(id).orElseThrow(() -> new NotFoundException(memberNotFound));

		updateFields.updateIfNotBlankAndNotEqual(memberRequest.getName(), member.getName(), member::setName, "name");
		updateFields.updateIfNotEmptyAndNotEqual(memberRequest.getFacebookUrl(), member.getFacebookUrl(), member::setFacebookUrl, "facebookUrl");
		updateFields.updateIfNotEmptyAndNotEqual(memberRequest.getInstagramUrl(), member.getInstagramUrl(), member::setInstagramUrl, "instagramUrl");
		updateFields.updateIfNotEmptyAndNotEqual(memberRequest.getLinkedinUrl(), member.getLinkedinUrl(), member::setLinkedinUrl, "linkedinUrl");
		updateFields.updateIfNotBlankAndNotEqual(memberRequest.getImage(), member.getImage(), member::setImage, "image");
		updateFields.updateIfNotBlankAndNotEqual(memberRequest.getDescription(), member.getDescription(), member::setDescription, "description");

		if(updateFields.isHasUpdate()){
			member.setDateUpdate(LocalDateTime.now());
		}

		return new MemberResponse(member.getName(),
								  	member.getFacebookUrl(),
									member.getInstagramUrl(),
									member.getLinkedinUrl(),
									member.getImage(),
									member.getDescription());
	}

    @Override
    public MemberResponse createMember(MemberRequest memberRequest) {
        Member member = new Member();
        member.setName(memberRequest.getName());
        member.setFacebookUrl(String.valueOf(memberRequest.getFacebookUrl()));
        member.setInstagramUrl(String.valueOf(memberRequest.getInstagramUrl()));
        member.setLinkedinUrl(String.valueOf(memberRequest.getLinkedinUrl()));
        member.setImage(memberRequest.getImage());
        member.setDescription(memberRequest.getDescription());
        Member memberCreate = memberRepository.save(member);
        return memberMapper.memberModel2DTO(memberCreate);
    }

	@Override
	public Member deleteMember(Long id) {
		String memberNotFound = messageSource.getMessage("member.notFound", null, Locale.US);
		Member member = memberRepository.findById(id).orElseThrow(()->new NotFoundException(memberNotFound));
		memberRepository.delete(member);
		return member;
	}

}

