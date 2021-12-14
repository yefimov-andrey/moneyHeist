package com.testtask.moneyheist.mappers;

import com.testtask.moneyheist.entities.HeistEntity;
import com.testtask.moneyheist.objects.Heist;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class HeistMapper {

    public HeistEntity heistToHeistEntity(Heist heist){
        HeistEntity heistEntity = new HeistEntity();
        heistEntity.setName(heist.getName());
        heistEntity.setLocation(heist.getLocation());
        heistEntity.setSkills(heist.getSkills().toString());
        heistEntity.setStartTime(heist.getStartTime());
        heistEntity.setEndTime(heist.getEndTime());
        return heistEntity;
    }

}
