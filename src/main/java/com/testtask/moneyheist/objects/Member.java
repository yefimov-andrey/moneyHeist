package com.testtask.moneyheist.objects;

import com.testtask.moneyheist.Skill;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Builder
@Getter
@Setter
public class Member {

    private String name;
    private String sex;
    private String email;
    private ArrayList<Skill> skills;
    private String mainSkill;
    private MemberStatus memberStatus;
}
