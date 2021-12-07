package com.testtask.moneyheist.mappers;

import com.testtask.moneyheist.entities.MemberEntity;
import com.testtask.moneyheist.objects.Member;

import java.util.Optional;

public class MemberMapper {

    public MemberEntity memberToMemberEntity(Member member){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setName(member.getName());
        memberEntity.setEmail(member.getEmail());
        memberEntity.setSex(member.getSex());
        memberEntity.setSkills(member.getSkills());
        memberEntity.setMainSkill(member.getMainSkill());
        return memberEntity;
    }

    public Member memberEntityToMember(MemberEntity memberEntity){
        return  Member.builder()
                .name(memberEntity.getName())
                .sex(memberEntity.getSex())
                .email(memberEntity.getEmail())
                .skills(memberEntity.getSkills())
                .mainSkill(Optional.of(memberEntity.getMainSkill()).orElse(null))
                .memberStatus(memberEntity.getStatus())
                .build();
    }

}
