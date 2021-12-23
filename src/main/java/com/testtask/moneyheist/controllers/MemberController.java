package com.testtask.moneyheist.controllers;

import com.testtask.moneyheist.objects.Member;
import com.testtask.moneyheist.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/")
    public ResponseEntity createMember(@RequestBody Member member) {
        return memberService.createMemberNew(member);
    }



}
