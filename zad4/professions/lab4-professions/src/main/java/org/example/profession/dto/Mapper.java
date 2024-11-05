package org.example.profession.dto;

import org.example.profession.dto.character.CharacterDto;
import org.example.profession.dto.character.CharactersDto;
import org.example.profession.dto.profession.ProfessionDto;
import org.example.profession.dto.profession.ProfessionsDto;
import org.example.profession.profession.Profession;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class Mapper {
    public static ProfessionDto toDTO(Profession profession) {
        return new ProfessionDto(profession.getName(), profession.getUnlockLevel());
    }
    public static List<ProfessionsDto> toLPDTO(Stream<Profession> professions) {
        return professions.map(profession -> new ProfessionsDto(profession.getUuid(), profession.getName())).toList();
    }
    public static Profession fromDTO(ProfessionDto professionDto, UUID uuid) {
        return new Profession(uuid, professionDto.getName(), professionDto.getUnlockLevel()/*, new ArrayList<Character>()*/);
    }
}
