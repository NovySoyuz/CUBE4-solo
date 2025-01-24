package com.example.api.dto;

import lombok.Data;
@Data
// Creation d'un DTO pour limiter les informations que nous faisons passer
// Utile pour alléger un objet et de le transferer entre les couches de l'application
public class AdminConnectionDTO {
    private String name;
    private String firstname;
    private int is_admin;
}
