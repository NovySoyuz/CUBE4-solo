CREATE DATABASE infcdl140;

USE infcdl140;

CREATE TABLE IF NOT EXISTS services (
    id_service INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS site (
    id_site INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    city VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS employees (
    id_employee INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    firstname VARCHAR(255),
    phone VARCHAR(255),
    mail VARCHAR(255),
    id_service INT NOT NULL,
    id_site INT NOT NULL,
    FOREIGN KEY(id_service) REFERENCES services(id_service),
    FOREIGN KEY(id_site) REFERENCES site(id_site)
);

INSERT INTO services (name) VALUES
    ('Ressources Humaines'),
    ('Informatique'),
    ('Commercial'),
    ('Marketing');

INSERT INTO site (city) VALUES
    ('Montpellier'),
    ('Marseille'),
    ('Toulouse'),
    ('Paris');