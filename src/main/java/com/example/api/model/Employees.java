package com.example.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity // Correspond à une table de la BDD
@Data // Anotation Lombok (Ajoute getter et setter)
public class Employees {
    @Id // Clé primaire de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrémentation de l'ID, génération automatique de la clé primaire
    private int id_employee;

    private String name;
    private String firstname;
    private String phone;
    private String mail;
    private int is_admin;

    // Relation plusieurs à 1
    // Permet de faire des suppression en cascade sinon erreur
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service") // Indique la colonne de clé étrangere
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // Ignorer les champs proxy
    private Services services; // Entité cible

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_site")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Site site;

}
