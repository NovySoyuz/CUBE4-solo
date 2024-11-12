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
    id_service INT,
    id_site INT,
    FOREIGN KEY (id_service) REFERENCES services(id_service),
    FOREIGN KEY (id_site) REFERENCES site(id_site)
);

INSERT INTO services (name) VALUES
    ('Ressources Humaines'),
    ('Informatique'),
    ('Commercial'),
    ('Marketing');


INSERT INTO site (city) VALUES
    ('Montpellier'),
    ('Marseille'),
    ('Paris'),
    ('Lyon');

INSERT INTO employees (name, firstname, phone, mail, id_service, id_site) VALUES
    ('Dupont', 'Jean', '0123456789', 'jean.dupont@example.com', 1, 1),
    ('Martin', 'Claire', '0987654321', 'claire.martin@example.com', 2, 2),
    ('Leclerc', 'Paul', '0123987654', 'paul.leclerc@example.com', 3, 1),
    ('Moreau', 'Sophie', '0145789632', 'sophie.moreau@example.com', 4, 3);