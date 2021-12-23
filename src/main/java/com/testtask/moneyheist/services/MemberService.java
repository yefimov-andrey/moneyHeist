package com.testtask.moneyheist.services;

import com.testtask.moneyheist.objects.Skill;
import com.testtask.moneyheist.entities.MemberEntity;
import com.testtask.moneyheist.mappers.MemberMapper;
import com.testtask.moneyheist.objects.Member;
import com.testtask.moneyheist.repositories.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Service
@Transactional
public class MemberService {

    private MemberMapper memberMapper;


    private MemberRepository memberRepository;


    public ResponseEntity createMemberNew(Member member){
        if(Pattern.compile("^(.+)@(\\S+)$").matcher(member.getEmail()).matches()) {
            if (!existsByEmail(member.getEmail())) {
                List<Skill> skills = new ArrayList<>(member.getSkills());
                boolean repeatSkills = false;
                for (int i = 0; i < skills.size(); i++) {
                    for (int k = i + 1; k < skills.size(); k++) {
                        if (skills.get(i) == skills.get(k))
                            repeatSkills = true;
                    }
                }
                if (!repeatSkills) {
                    MemberEntity memberEntity = memberRepository.save(memberMapper.memberToMemberEntity(member));
                    return ResponseEntity.created(URI.create("/member/" + memberEntity.getId())).body("");
                } else {
                    return ResponseEntity.badRequest().header("Error", "Same skill is mentioned more than once")
                            .body("");
                }

            } else
                return ResponseEntity.badRequest().header("Error", "Member with this email already exists!")
                        .body("");
        }
        else {
            return ResponseEntity.badRequest().header("Error", "Email is not valid!")
                    .body("");
        }
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

}
