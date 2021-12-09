package com.testtask.moneyheist.repositories;

import com.testtask.moneyheist.entities.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>{

    boolean existsByName(String name);

    Optional<List<MemberEntity>> findByEmail(String email);

    Optional<MemberEntity> findById(Long id);

}
