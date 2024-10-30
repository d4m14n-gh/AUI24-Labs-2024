package org.example.zad3.dto.profession;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class ProfessionDto {
     private String name;
     private int unlockLevel;
}
