package org.example;

import lombok.*;

import java.io.Serializable;

@Builder
//@ToString
//@EqualsAndHashCode
@Getter
@Setter
public class Animal implements Serializable {
    String name;

    int age;

    @ToString.Exclude
    AnimalSpecies animalSpecies;

//    @Override
//    public String toString() {
//        return this.toString();
//    }
}
