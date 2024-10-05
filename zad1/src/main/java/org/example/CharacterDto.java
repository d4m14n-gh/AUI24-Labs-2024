package org.example;

import lombok.*;

@Value
//@lombok.
public class CharacterDto implements Comparable<CharacterDto> {
    String name;

    int level;

    String profession;

    @Override
    public int compareTo(CharacterDto other) {
        if (!name.equals(other.name))
            return name.compareTo(other.name);
        else if (level!=other.level)
            return Integer.compare(level, other.level);
        else return 0;
    }
}
