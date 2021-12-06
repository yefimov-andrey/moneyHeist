package com.testtask.moneyheist.services;

import com.testtask.moneyheist.entities.Member;
import com.testtask.moneyheist.repositories.MemberRepository;
import lombok.NoArgsConstructor;

import java.util.Collections;

@NoArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;

    public void createMember(){
        Member member = new Member();
        memberRepository.save(member);
    }

    public boolean memberExists(String email){
        return memberRepository.findByEmail(email).equals(Collections.emptyList());
    }
}
