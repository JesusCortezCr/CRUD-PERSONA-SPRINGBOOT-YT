package com.proyecto.cortez.crud_persona.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede ser vacio")
    @Size(min = 3,max = 50,message = "El nombre debe tener como minimo 3 caracteres o 50 como maximo")
    private String nombre;

    @Email(message = "El email no es valido")
    @NotBlank(message = "El email no puede ser vacio")
    @Column(unique = true)
    private String email;

    @Min(value = 18,message = "La edad minima es de 18 años")
    private int edad;
    

    
}
