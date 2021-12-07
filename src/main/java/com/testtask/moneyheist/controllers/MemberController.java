package com.testtask.moneyheist.controllers;

import com.testtask.moneyheist.Skill;
import com.testtask.moneyheist.entities.MemberEntity;
import com.testtask.moneyheist.mappers.MemberMapper;
import com.testtask.moneyheist.objects.Member;
import com.testtask.moneyheist.objects.MemberStatus;
import com.testtask.moneyheist.services.MemberService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class MemberController {

    private static final MemberService memberService = new MemberService();
    public static final MemberMapper memberMapper = new MemberMapper();

    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public void createMember(@RequestBody Member member) {
        if(!memberService.memberExists(member.getEmail())) {
            if (member.getMainSkill().equals(null))
                member.setMainSkill("");
            /*Member member = Member.builder()
                    .name(name)
                    .sex(sex)
                    .email(email)
                    .skills(skills)
                    .mainSkill(Optional.of(mainSkill).orElse(null))
                    .memberStatus(memberStatus)
                    .build();*/
            memberService.createMember(memberMapper.memberToMemberEntity(member));
        }
    }

}
