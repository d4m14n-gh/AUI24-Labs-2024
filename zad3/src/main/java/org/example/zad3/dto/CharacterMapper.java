package org.example.zad3.dto;

import org.example.zad3.character.Character;
import org.example.zad3.character.CharacterService;
import org.example.zad3.profession.ProfessionService;

import java.util.UUID;

public class CharacterMapper {
    public static CharacterDto toDTO(Character character) {
        return new CharacterDto(character.getName(), character.getLevel(),  character.getProfession().getUuid());
    }
    public static Character fromDTO(CharacterDto characterDto, ProfessionService professionService) {
        return new Character(UUID.randomUUID(), characterDto.getName(), characterDto.getLevel(),  professionService.getProfessionById( characterDto.getProfessionId() ).orElseThrow());
    }
}
