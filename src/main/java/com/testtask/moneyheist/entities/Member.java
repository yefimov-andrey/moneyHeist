package com.testtask.moneyheist.entities;

import com.testtask.moneyheist.Skill;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
public class Member {
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

    public enum MemberStatus {
        AVAILABLE,
        EXPIRED,
        INCARCERATED,
        RETIRED
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
