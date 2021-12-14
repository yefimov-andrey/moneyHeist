package com.testtask.moneyheist.repositories;

import com.testtask.moneyheist.entities.HeistEntity;
import com.testtask.moneyheist.entities.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeistRepository extends JpaRepository<HeistEntity, Long>{

    boolean existsByName(String name);

    Optional<HeistEntity> findById(Long id);

}
