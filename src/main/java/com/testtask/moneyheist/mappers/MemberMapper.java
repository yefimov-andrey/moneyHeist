package com.testtask.moneyheist.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.moneyheist.Skill;
import com.testtask.moneyheist.entities.MemberEntity;
import com.testtask.moneyheist.objects.Member;
import lombok.NoArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Component
public class MemberMapper {

    public MemberEntity memberToMemberEntity(Member member){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setName(member.getName());
        memberEntity.setEmail(member.getEmail());
        memberEntity.setSex(member.getSex());
        memberEntity.setSkills(member.getSkills().toString());
        memberEntity.setMainSkill(member.getMainSkill());
        memberEntity.setStatus(member.getStatus());
        return memberEntity;
    }

    public Member memberEntityToMember(MemberEntity memberEntity){
        return  Member.builder()
                .name(memberEntity.getName())
                .sex(memberEntity.getSex())
                .email(memberEntity.getEmail())
                .skills(null)
                .mainSkill(Optional.of(memberEntity.getMainSkill()).orElse(null))
                .status(memberEntity.getStatus())
                .build();
    }

    public Skill[] skillsToArray(String skills) throws JsonProcessingException {
        String skills2 = StringEscapeUtils.escapeJava(skills);
        final ObjectMapper objectMapper = new ObjectMapper();
        Skill[] skillsArray = objectMapper.readValue(skills2, Skill[].class);
        return skillsArray;
    }




}
