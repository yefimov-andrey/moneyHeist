package com.testtask.moneyheist.controllers;

import com.testtask.moneyheist.entities.MemberEntity;
import com.testtask.moneyheist.mappers.MemberMapper;
import com.testtask.moneyheist.objects.Member;
import com.testtask.moneyheist.objects.MemberSkills;
import com.testtask.moneyheist.objects.Skill;
import com.testtask.moneyheist.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public ResponseEntity createMember(@RequestBody Member member) {
        /*ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);*/
        if(!memberService.existsByEmail(member.getEmail())) {
            Long id = memberService.createMember(member);
            ArrayList<Skill> skills1 = member.getSkills();
            Set<String> skillNames = new HashSet<>();
            String skillName = "";
            for (Skill skill:skills1) {
                skillName = skill.getName();
                if(!skillNames.contains(skillName)){
                    skillNames.add(skillName);
                }
                else {
                    return errorMemberResponse("Same skill is mentioned more than once");
                }
            }
            return createdMemberResponse(id);

        }
        else {
            return errorMemberResponse("Email already exists!");
        }
    }

    @RequestMapping(value = "/member/{memberId}/skills", method = RequestMethod.PUT)
    public ResponseEntity putMemberSkills(@PathVariable Long memberId, @RequestBody MemberSkills memberSkills){
        if(memberService.findById(memberId) != null) {
            int result = memberService.changeMemberSkills(memberId, memberSkills);
            if (result == 204)
                return ResponseEntity.noContent().header("Content-Location", "/member/" + memberId + "/skills").build();
            else
                return ResponseEntity.badRequest().build();
        }
        else
            return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/member/{memberId}/skills/{skillName}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMemberSkill(@PathVariable Long memberId, @PathVariable String skillName){
        MemberEntity memberEntity;
        if((memberEntity = memberService.findById(memberId)) != null){
            Skill skill;
            ArrayList<Skill> skillArrayList = memberMapper.memberSkillsToArrayList(memberEntity.getSkills());
            if((skill = memberService.findBySkillName(skillArrayList, skillName)) != null){
                memberService.deleteMemberSkill(memberEntity, skill);
                return ResponseEntity.noContent().build();
            }
            else
                return ResponseEntity.notFound().build();
        }
        else
            return ResponseEntity.notFound().build();
    }

    ResponseEntity<String> createdMemberResponse(Long id) {
        return ResponseEntity.created(URI.create("/member/" + id))
                .body("");
    }

    ResponseEntity<String> errorMemberResponse(String error){
        return ResponseEntity.badRequest()
                .header("Error", error)
                .body("");
    }

}
