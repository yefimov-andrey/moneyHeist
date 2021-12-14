package com.testtask.moneyheist.services;

import com.testtask.moneyheist.entities.HeistEntity;
import com.testtask.moneyheist.mappers.HeistMapper;
import com.testtask.moneyheist.objects.Heist;
import com.testtask.moneyheist.repositories.HeistRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeistService {

    private final HeistMapper heistMapper;

    private final HeistRepository heistRepository;

    public Long createHeist(Heist heist){
        HeistEntity heistEntity = heistRepository.save(heistMapper.heistToHeistEntity(heist));
        heistRepository.flush();
        return heistEntity.getId();
    }

    public boolean existsByName(String name){
        return heistRepository.existsByName(name);
    }

}
