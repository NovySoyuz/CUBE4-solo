package com.example.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity // Correspond à une table de la BDD
@Data // Anotation Lombok (Ajoute getter et setter)
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_site")
    private Integer id;

    private String city;
}
