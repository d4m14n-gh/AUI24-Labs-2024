package org.example.profession.dto.profession;

import lombok.Value;

import java.util.UUID;

@Value
public class ProfessionsDto {
    public UUID id;
    public String name;
}