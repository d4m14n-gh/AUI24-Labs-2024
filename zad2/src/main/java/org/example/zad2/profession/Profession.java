package org.example.zad2.profession;

import jakarta.persistence.*;
import lombok.*;
import org.example.zad2.character.Character;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "character_profession")
@Getter //lombok
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Profession implements Serializable {
    @Id
    private UUID uuid;

    @Column(unique = true)
    private String name;

    @Column(name = "unlock_level")
    private int unlockLevel;

    @Column(name = "character_list")
    @OneToMany(mappedBy = "profession", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude //lombok
    @EqualsAndHashCode.Exclude
    private List<Character> charactersList;
}

