package org.example.zad3.dto.character;

import lombok.Singular;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class CharactersDto {
    public UUID id;
    public String name;
}
