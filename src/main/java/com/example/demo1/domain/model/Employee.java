package com.example.demo1.domain.model;

// représentation des besoins metiers.
// Representer au mieux la bdd pour ne pas avoir de problemes lors de la serialisation / déserialisation
public class Employee {
    private int id_employee;
    private String name;
    private String mail;
    private String firstname;
    private String phone;
    // On instancie Services et Site car ils sont présent dans l'API
    // Il faut donc créer les 2 classes et les instancier pour que le framework jakeson puisse serialiser le JSON
    private Services services;
    private Site site;


    // Initialisation d'un constructeur sans arguments (constructeur par defaut)
    // Souvent utilisé par les framework de serialisation/désérialisation (Jackson)
    // Permet de creer des instances de classes sans avoir à fournir des valeurs initlaies

    public Employee() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
