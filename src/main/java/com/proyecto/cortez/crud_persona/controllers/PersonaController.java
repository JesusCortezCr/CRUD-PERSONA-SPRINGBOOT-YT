package com.proyecto.cortez.crud_persona.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.cortez.crud_persona.entities.Persona;
import com.proyecto.cortez.crud_persona.services.PersonaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/persona") //localhost:8080/persona
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    //localhost:8080/persona
    @GetMapping
    public ResponseEntity<?> listadoPersonas(){
        return ResponseEntity.ok(personaService.listadoPersonas());
    }

    //localhost:8080/persona/2
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPersonaPorId(@PathVariable Long id){
        return ResponseEntity.ok(personaService.buscarPersonaPorId(id));
    }

    //localhost:8080/crear
    @PostMapping("/crear")
    public ResponseEntity<?> crearPersona(@Valid @RequestBody Persona persona){
        return ResponseEntity.ok(personaService.crearPersona(persona));
    }

    ////localhost:8080/actualizar/4
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarPersona(@PathVariable Long id, @Valid @RequestBody Persona persona){
        return ResponseEntity.ok(personaService.actualizarPersona(id, persona));
    }

    //localhost:8080/eliminar/2
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPersona(@PathVariable Long id){
       
        personaService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    
    
}
