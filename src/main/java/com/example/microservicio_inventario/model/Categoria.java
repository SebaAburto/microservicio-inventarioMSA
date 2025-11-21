package com.example.microservicio_inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String slug; //slug es un termino usado en desarrollo web, es para que la url no sea el id sino un texto mas legible.

    // Tortas Cuadradas
    // Tortas Circulares
    // Postres Individuales
    // Productos Sin Azúcar
    // Pastelería Tradicional
    // Productos sin gluten
    // Productos Vegana
    // Tortas Especiales

}