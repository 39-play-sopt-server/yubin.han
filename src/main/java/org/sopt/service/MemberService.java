package org.sopt.service;

import org.sopt.domain.Member;
import org.sopt.dto.MemberCreateRequest;
import org.sopt.exception.MemberNotFoundException;
import org.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long createMember(MemberCreateRequest request) {
        Member member = new Member(request.name());
        return memberRepository.save(member).getId();
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
        memberRepository.delete(member);
    }
}
