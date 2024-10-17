package org.example.zad2.character;

import org.example.zad2.profession.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CharacterRepository extends JpaRepository<Character, UUID> {
    List<Character> findCharacterByProfession(Profession profession);
    public void deleteCharacterByUuid(UUID id);
}