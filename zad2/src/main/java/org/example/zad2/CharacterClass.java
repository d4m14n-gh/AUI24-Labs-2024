package org.example.zad2;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "characters_classes")
@Getter //lombok
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CharacterClass implements Serializable {
    @Id
    UUID uuid;

    String name;

    int unlock_level;

    @OneToMany(mappedBy = "characters")
    @ToString.Exclude //lombok
    @EqualsAndHashCode.Exclude
    List<Character> characters;
}

