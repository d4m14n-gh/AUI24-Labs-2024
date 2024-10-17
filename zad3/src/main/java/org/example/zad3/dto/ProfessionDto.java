package org.example.zad3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class ProfessionDto {
     private UUID id;
     private String name;
     private int unlockLevel;
     private List<UUID> characterIds;
}
