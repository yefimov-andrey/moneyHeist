package com.testtask.moneyheist.services;

import com.testtask.moneyheist.entities.MemberEntity;
import com.testtask.moneyheist.mappers.MemberMapper;
import com.testtask.moneyheist.objects.Member;
import com.testtask.moneyheist.objects.MemberSkills;
import com.testtask.moneyheist.objects.Skill;
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

    public MemberEntity findById(Long id){
        return memberRepository.findById(id).orElse(null);
    }

    public Skill findBySkillName(ArrayList<Skill> skillArrayList, String skillName){
        return skillArrayList.stream().filter(skill -> skillName.equals(skill.getName())).findFirst().orElse(null);
    }

    public int changeMemberSkills(Long id, MemberSkills memberSkills){
        ArrayList<Skill> newSkillsArrayList = memberSkills.getSkills();
        List<String> newSkillNames = new ArrayList<>();
        for (Skill newSkill: newSkillsArrayList) {
            if(newSkillNames.contains(newSkill.getName()))
                return 400;
            newSkillNames.add(newSkill.getName());
        }
        MemberEntity memberEntity = memberRepository.findById(id).orElse(null);
        String mainSkill = memberSkills.getMainSkill();
        ArrayList<Skill> oldSkillsArrayList = memberMapper.memberSkillsToArrayList(memberEntity.getSkills());
        Skill tempSkill = new Skill("a", "*");
        int size = oldSkillsArrayList.size();
        boolean isInOldList;
        for (Skill newSkill: newSkillsArrayList) {
            isInOldList = false;
            for(Skill oldSkill: oldSkillsArrayList){
                if(newSkill.getName().equals(oldSkill.getName())) {
                    isInOldList = true;
                    tempSkill = oldSkill;
                    break;
                }
            }
            if(!isInOldList)
                oldSkillsArrayList.add(size,newSkill);
            else{
                oldSkillsArrayList.set(oldSkillsArrayList.indexOf(tempSkill), newSkill);
            }
        }
        memberEntity.setSkills(oldSkillsArrayList.toString());
        if(mainSkill != null) {
            if(findBySkillName(oldSkillsArrayList, mainSkill) != null)
                memberEntity.setMainSkill(mainSkill);
            else{
                return 400;
            }
        }
        else
            memberEntity.setMainSkill("");
        memberRepository.saveAndFlush(memberEntity);
        return 200;
    }

    public MemberEntity deleteMemberSkill(MemberEntity memberEntity, Skill skill){
        ArrayList<Skill> skillArrayList = memberMapper.memberSkillsToArrayList(memberEntity.getSkills());
        skillArrayList.remove(skill);
        memberEntity.setSkills(skillArrayList.toString());
        memberRepository.saveAndFlush(memberEntity);
        return memberEntity;
    }

}
