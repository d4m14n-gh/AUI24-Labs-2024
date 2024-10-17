package org.example.zad3.dto;

import lombok.*;

import java.util.UUID;

@Value
public class CharacterDto {
    String name;

    int level;

    UUID professionId;
}
