package com.testtask.moneyheist.objects;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Value
public class Heist {

    String name;
    String location;
    LocalDateTime startTime;
    LocalDateTime endTime;
    ArrayList<HeistSkill> skills;

}
