package org.example.zad2;

public class CharacterMapper {
    public static CharacterDto toDTO(Character character){
        return new CharacterDto(character.name, character.level, character.character_class.name);
    }
}
