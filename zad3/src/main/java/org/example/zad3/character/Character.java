package org.example.zad3.character;

import jakarta.persistence.*;
import lombok.*;
import org.example.zad3.profession.Profession;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "characters")
@Getter //lombok
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Character implements Serializable {
    @Id
    private UUID uuid;
    private String name;
    private int level;

    @ManyToOne
    @JoinColumn(name = "character_profession")
    @ToString.Exclude //lombok
    private Profession profession;
}
