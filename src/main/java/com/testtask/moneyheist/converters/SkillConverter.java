package com.testtask.moneyheist.converters;

import com.testtask.moneyheist.objects.Skill;

import javax.persistence.AttributeConverter;

public class SkillConverter implements AttributeConverter<Skill, String> {

    private static final String SEPARATOR = ", ";
    @Override
    public String convertToDatabaseColumn(Skill skill) {
        if(skill == null)
            return null;
        StringBuilder sb = new StringBuilder();
        if (skill.getName() != null && !skill.getName()
                .isEmpty()) {
            sb.append(skill.getName());
            sb.append(SEPARATOR);
        }
        if (skill.getLevel() != null
                && !skill.getLevel().isEmpty()) {
            sb.append(skill.getLevel());
        }
        return sb.toString();
    }

    @Override
    public Skill convertToEntityAttribute(String skillString) {
        if (skillString == null || skillString.isEmpty()) {
            return null;
        }

        String[] pieces = skillString.split(SEPARATOR);

        if (pieces.length == 0) {
            return null;
        }

        Skill skill = new Skill();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (skillString.contains(SEPARATOR)) {
            skill.setName(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                skill.setLevel(pieces[1]);
            }
        } else {
            skill.setLevel(firstPiece);
        }

        return skill;
    }
}
