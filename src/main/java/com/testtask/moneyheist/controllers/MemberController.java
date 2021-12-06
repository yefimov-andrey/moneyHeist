package com.testtask.moneyheist.controllers;

import com.testtask.moneyheist.Skill;
import com.testtask.moneyheist.entities.Member;
import com.testtask.moneyheist.repositories.MemberRepository;
import com.testtask.moneyheist.services.MemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    public static final MemberService memberService = new MemberService();

    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public void createMember(@RequestParam String name, @RequestParam String sex, @RequestParam String email,
                             @RequestParam Skill[] skills, @RequestParam String mainSkill,
                             @RequestParam Member.MemberStatus memberStatus) {
        if(!memberService.memberExists(email))
            memberService.createMember();
    }

}
