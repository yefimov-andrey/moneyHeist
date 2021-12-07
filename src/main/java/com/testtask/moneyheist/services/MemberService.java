package com.testtask.moneyheist.services;

import com.testtask.moneyheist.entities.MemberEntity;
import com.testtask.moneyheist.repositories.MemberRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@NoArgsConstructor
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public void createMember(MemberEntity memberEntity){
        //MemberEntity memberEntity = new MemberEntity();
        memberRepository.saveAndFlush(memberEntity);
    }

    public boolean memberExists(String email){
        return memberRepository.findByEmail(email).equals(Collections.emptyList());
    }
}
