package org.example.characters.profession;


import jakarta.persistence.*;
import lombok.*;
import org.example.characters.character.Character;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "character_profession")
@Getter //lombok
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Profession implements Serializable {
    @Id
    private UUID uuid;

    @Column(unique = true)
    private String name;

    @Column(name = "character_list")
    @OneToMany(mappedBy = "profession", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude //lombok
    @EqualsAndHashCode.Exclude
    private List<Character> charactersList;
}