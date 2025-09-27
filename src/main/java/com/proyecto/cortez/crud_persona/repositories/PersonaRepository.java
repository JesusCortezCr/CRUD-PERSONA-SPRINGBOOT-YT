package com.proyecto.cortez.crud_persona.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.cortez.crud_persona.entities.Persona;

public interface PersonaRepository extends JpaRepository<Persona,Long> {

}
