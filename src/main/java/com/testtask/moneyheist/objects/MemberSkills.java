package com.testtask.moneyheist.objects;

import lombok.Value;

import java.util.ArrayList;

@Value
public class MemberSkills {
    ArrayList<Skill> skills;
    String mainSkill;

}
