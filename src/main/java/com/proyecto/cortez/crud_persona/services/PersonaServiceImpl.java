package com.proyecto.cortez.crud_persona.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.cortez.crud_persona.entities.Persona;
import com.proyecto.cortez.crud_persona.excepcions.PersonaNoEncontradaException;
import com.proyecto.cortez.crud_persona.repositories.PersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> listadoPersonas() {
        if (personaRepository.findAll().size() == 0) {
            throw new RuntimeException("No existen personas registradas");
        }
        return personaRepository.findAll();
    }

    @Override
    public Persona buscarPersonaPorId(Long id) {
        return personaRepository.findById(id).orElseThrow(() -> new PersonaNoEncontradaException(id));
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public Persona actualizarPersona(Long id, Persona persona) {
        Persona personaExistente = personaRepository
                .findById(id)
                .orElseThrow(() -> new PersonaNoEncontradaException(id));
        if (personaExistente != null) {
            personaExistente.setNombre(persona.getNombre());
            personaExistente.setEmail(persona.getEmail());
            personaExistente.setEdad(persona.getEdad());
            return personaRepository.save(personaExistente);
        }
        return persona;
    }

    @Override
    public void eliminarUsuario(Long id) {
        Persona personaExistente = personaRepository
                .findById(id)
                .orElseThrow(() -> new PersonaNoEncontradaException(id));
        personaRepository.deleteById(personaExistente.getId());
    }

}
