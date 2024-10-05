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
    Profession profession;

//    @Override
//    public String toString() {
//        return this.toString();
//    }
}
