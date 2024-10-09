package org.example;

import lombok.*;

import java.io.Serializable;

@Builder
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Character implements Serializable {
    String name;

    int level;

    @ToString.Exclude
    CharacterClass characterClass;

//    @Override
//    public String toString() {
//        return this.toString();
//    }
}
