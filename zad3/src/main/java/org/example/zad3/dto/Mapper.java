package org.example.zad3.dto;

import org.example.zad3.character.Character;
import org.example.zad3.dto.character.CharacterDto;
import org.example.zad3.dto.character.CharactersDto;
import org.example.zad3.dto.profession.ProfessionDto;
import org.example.zad3.dto.profession.ProfessionsDto;
import org.example.zad3.profession.Profession;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class Mapper {
    public static CharacterDto toDTO(Character character) {
        return new CharacterDto(character.getName(), character.getLevel(),  character.getProfession().getUuid());
    }
    public static List<CharactersDto> toLCDTO(Stream<Character> characters) {
        return characters.map(character -> new CharactersDto(character.getUuid(), character.getName())).toList();
    }
    public static Character fromDTO(CharacterDto characterDto, UUID uuid, Profession profession) {
        return new Character(uuid, characterDto.getName(), characterDto.getLevel(),  profession);
    }

    public static ProfessionDto toDTO(Profession profession) {
        return new ProfessionDto(profession.getName(), profession.getUnlockLevel());
    }
    public static List<ProfessionsDto> toLPDTO(Stream<Profession> professions) {
        return professions.map(profession -> new ProfessionsDto(profession.getUuid(), profession.getName())).toList();
    }
    public static Profession fromDTO(ProfessionDto professionDto, UUID uuid) {
        return new Profession(uuid, professionDto.getName(), professionDto.getUnlockLevel(),  new ArrayList<Character>());
    }
}
