package com.testtask.moneyheist.entities;

import com.testtask.moneyheist.converters.SkillConverter;
import com.testtask.moneyheist.objects.Sex;
import com.testtask.moneyheist.objects.Skill;
import com.testtask.moneyheist.objects.MemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table (name = "MEMBER_ENTITY")
@Getter
@Setter
@NoArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "skills")
    @ElementCollection(targetClass = Skill.class)
    private Set<Skill> skills;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Column(name = "main_skill")
    @Convert(converter = SkillConverter.class)
    private Skill mainSkill;
}
