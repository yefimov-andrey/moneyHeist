package com.testtask.moneyheist.objects;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Value
@Setter
@RequiredArgsConstructor
public class Skill {
    String name;
    String level;
}
