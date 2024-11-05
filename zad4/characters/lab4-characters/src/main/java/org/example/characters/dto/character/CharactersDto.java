package org.example.characters.dto.character;

import lombok.Value;

import java.util.UUID;

@Value
public class CharactersDto {
    public UUID id;
    public String name;
}
