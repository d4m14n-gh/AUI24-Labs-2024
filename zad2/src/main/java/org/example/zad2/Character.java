package org.example.zad2;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "characters")
@Getter //lombok
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Character implements Serializable {
    @Id
    UUID uuid;
    String name;
    int level;

    @ManyToOne
    @JoinColumn(name = "character_class")
    @ToString.Exclude //lombok
    CharacterClass character_class;

//    @Override
//    public String toString() {
//        return this.toString();
//    }
}
