package org.example.characters.dto.character;

import lombok.Value;

import java.util.UUID;

@Value
public class CharacterDto {
    String name;

    int level;

    UUID professionId;
}
