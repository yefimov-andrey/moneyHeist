package com.testtask.moneyheist.repositories;

import com.testtask.moneyheist.entities.MemberEntity;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
    List<MemberEntity> findByEmail(String email);

}
