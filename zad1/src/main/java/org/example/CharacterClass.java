package org.example;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@ToString
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class CharacterClass implements Serializable {
    String name;

    int unlockLevel;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Character> characters;
}

