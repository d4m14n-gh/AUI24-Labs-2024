package org.example.profession.dto.character;

import lombok.Value;

import java.util.UUID;

@Value
public class CharacterDto {
    String name;

    int level;

    UUID professionId;
}
