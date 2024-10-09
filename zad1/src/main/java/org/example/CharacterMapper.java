package org.example;

public class CharacterMapper {
    public static CharacterDto toDTO(Character character){
        return new CharacterDto(character.name, character.level, character.characterClass.name);
    }
}
