package com.testtask.moneyheist.objects;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.ArrayList;
import java.util.Set;

@Builder
@With
@Value
public class Member {
    String name;
    Sex sex;
    String email;
    Set<Skill> skills;
    Skill mainSkill;
    MemberStatus status;
}
