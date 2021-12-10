package com.testtask.moneyheist.mappers;

import com.testtask.moneyheist.entities.MemberEntity;
import com.testtask.moneyheist.objects.Member;
import com.testtask.moneyheist.objects.Skill;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

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

    public ArrayList<Skill> skillsToArrayList(String skills){
        if(skills != null) {
            ArrayList<Skill> skillArrayList = new ArrayList<>();
            Pattern pattern = Pattern.compile("Skill\\(");
            String[] skillsArray = pattern.split(skills);
            String skillName = "";
            String skillLevel = "";
            int k = 0;
            for (String skill : skillsArray) {
                if (k != 0) {
                    skillName = skill.split("name=")[1].split(",")[0];
                    skillLevel = skill.split("level=")[1].split("\\)")[0];
                    skillArrayList.add(k - 1, new Skill(skillName, skillLevel));
                }
                k++;
            }
            return skillArrayList;
        }
        else
            return null;
    }




}
