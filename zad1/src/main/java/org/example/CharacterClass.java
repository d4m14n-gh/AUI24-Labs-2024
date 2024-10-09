package org.example;

import lombok.*;

import java.io.Serializable;
import java.util.List;

//@ToString
//@Builder
//@EqualsAndHashCode
//@Getter
//@Setter
public class AnimalSpecies implements Serializable {
    String name;

    int meanWeight;
    //int lifeExpectancy;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Character> animals;
}

