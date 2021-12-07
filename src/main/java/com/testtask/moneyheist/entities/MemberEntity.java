package com.testtask.moneyheist.entities;

import com.testtask.moneyheist.Skill;
import com.testtask.moneyheist.objects.Member;
import com.testtask.moneyheist.objects.MemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
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

    @Column(name= "sex", nullable = false)
    private String sex;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "skills", nullable = false)
    private ArrayList<Skill> skills;

    @Column(name = "status", nullable = false)
    private MemberStatus status;

    @Column(name = "mainSkill")
    private String mainSkill;
}
