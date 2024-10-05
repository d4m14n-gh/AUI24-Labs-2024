package org.example;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@ToString
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class Profession implements Serializable {
    String name;

    int baseArmor;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Character> characters;
}

