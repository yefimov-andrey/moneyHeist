package com.testtask.moneyheist.services;

import com.testtask.moneyheist.entities.MemberEntity;
import com.testtask.moneyheist.mappers.MemberMapper;
import com.testtask.moneyheist.objects.Member;
import com.testtask.moneyheist.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    @Autowired
    private final MemberRepository memberRepository;

    public Long createMember(Member member){
        MemberEntity memberEntity = memberRepository.save(memberMapper.memberToMemberEntity(member));
        memberRepository.flush();
        return memberEntity.getId();
    }

    public boolean existsByEmail(String email){
        Page<MemberEntity> entityPage = memberRepository.findAll(PageRequest.of(0,1000));
        List<MemberEntity> entities = memberRepository.findAll();
        List<String> emails = new ArrayList<>();
        for (MemberEntity entity:entities) {
            emails.add(entity.getEmail());
        }
        return !memberRepository.findByEmail(email).get().equals(Collections.emptyList());
    }

}
