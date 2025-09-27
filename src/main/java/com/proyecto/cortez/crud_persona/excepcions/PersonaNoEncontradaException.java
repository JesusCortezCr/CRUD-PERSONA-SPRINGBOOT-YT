package com.proyecto.cortez.crud_persona.excepcions;

public class PersonaNoEncontradaException extends RuntimeException {

    public PersonaNoEncontradaException(Long id){
        super("Persona con el id : "+id+" no existe");
    }
}
