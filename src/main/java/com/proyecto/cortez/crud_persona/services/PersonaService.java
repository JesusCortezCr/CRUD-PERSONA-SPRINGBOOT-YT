package com.proyecto.cortez.crud_persona.services;

import java.util.List;

import com.proyecto.cortez.crud_persona.entities.Persona;

public interface PersonaService {

    public List<Persona> listadoPersonas();

    public Persona buscarPersonaPorId(Long id);

    public Persona crearPersona(Persona persona);

    public Persona actualizarPersona(Long id,Persona persona);

    public void eliminarUsuario(Long id);
}
