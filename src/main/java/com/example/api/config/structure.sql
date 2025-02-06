CREATE
DATABASE infcdl140;

USE
infcdl140;

CREATE TABLE IF NOT EXISTS services
(
    id_service INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR (255)
);

CREATE TABLE IF NOT EXISTS site
(
    id_site INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    city VARCHAR (255)
);

CREATE TABLE IF NOT EXISTS employees
(
    id_employee INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR (255),
    firstname VARCHAR (255),
    phone VARCHAR (255),
    mail VARCHAR (255),
    id_service INT,
    id_site INT,
    FOREIGN KEY (id_service) REFERENCES services (id_service),
    FOREIGN KEY (id_site) REFERENCES site(id_site)
);

INSERT INTO services (name)
VALUES ('Ressources Humaines'),
       ('Informatique'),
       ('Commercial'),
       ('Marketing');

INSERT INTO site (city)
VALUES ('Montpellier'),
       ('Marseille'),
       ('Toulouse'),
       ('Paris');

INSERT INTO employees (name, firstname, mail, phone, is_admin, id_service, id_site)
VALUES ('Dupont', 'Jean', 'jean.dupont@example.com', '0612345678', 0, 1, 1),
       ('Martin', 'Sophie', 'sophie.martin@example.com', '0623456789', 0, 4, 2),
       ('Bernard', 'Luc', 'luc.bernard@example.com', '0634567890', 1, 4, 3),
       ('Robert', 'Emma', 'emma.robert@example.com', '0644678901', 0, 1, 4),
       ('Petit', 'Léo', 'leo.petit@example.com', '0646789012', 0, 4, 1),
       ('Durand', 'Julie', 'julie.durand@example.com', '0667890123', 0, 4, 2),
       ('Lemoine', 'Thomas', 'thomas.lemoine@example.com', '0678901234', 0, 1, 3),
       ('Morel', 'Laura', 'laura.morel@example.com', '0689012344', 0, 4, 4),
       ('Fournier', 'Antoine', 'antoine.fournier@example.com', '0690123446', 0, 4, 1),
       ('Girard', 'Camille', 'camille.girard@example.com', '0611223344', 1, 1, 2),
       ('Andre', 'Paul', 'paul.andre@example.com', '0622334444', 0, 4, 3),
       ('Mercier', 'Manon', 'manon.mercier@example.com', '0633444466', 0, 4, 4),
       ('Dupuis', 'Hugo', 'hugo.dupuis@example.com', '0644446677', 0, 1, 1),
       ('Lemoine', 'Alice', 'alice.lemoine@example.com', '0644667788', 0, 4, 2),
       ('Gautier', 'Maxime', 'maxime.gautier@example.com', '0666778899', 0, 4, 3),
       ('Noel', 'Chloé', 'chloe.noel@example.com', '0677889900', 0, 1, 4),
       ('Renaud', 'Matthieu', 'matthieu.renaud@example.com', '0688990011', 0, 4, 1),
       ('Blanc', 'Emma', 'emma.blanc@example.com', '0699001122', 0, 4, 2),
       ('Baron', 'Julien', 'julien.baron@example.com', '0610112233', 0, 1, 3),
       ('Perrot', 'Nina', 'nina.perrot@example.com', '0621223344', 0, 4, 4),
       ('Leroy', 'Nathan', 'nathan.leroy@example.com', '0633112244', 0, 3, 1),
       ('Moreau', 'Elisa', 'elisa.moreau@example.com', '0644223344', 0, 2, 3),
       ('Giraud', 'Alexandre', 'alexandre.giraud@example.com', '0644334466', 0, 4, 2),
       ('Fabre', 'Louise', 'louise.fabre@example.com', '0666444477', 0, 4, 4),
       ('Chevalier', 'Victor', 'victor.chevalier@example.com', '0677446688', 0, 1, 1),
       ('Benoit', 'Emma', 'emma.benoit@example.com', '0688667799', 0, 2, 3),
       ('Leclerc', 'Arthur', 'arthur.leclerc@example.com', '0699778800', 0, 3, 2),
       ('Simon', 'Marie', 'marie.simon@example.com', '0610889911', 0, 4, 4),
       ('Rousseau', 'Gabriel', 'gabriel.rousseau@example.com', '0621990022', 0, 4, 1),
       ('Lambert', 'Zoé', 'zoe.lambert@example.com', '0632001133', 0, 1, 3),
       ('Garnier', 'Hugo', 'hugo.garnier@example.com', '0643112244', 0, 2, 2),
       ('Dupuy', 'Jade', 'jade.dupuy@example.com', '0644223344', 0, 3, 4),
       ('Prevost', 'Léo', 'leo.prevost@example.com', '0664334466', 0, 4, 1),
       ('Maillard', 'Chloé', 'chloe.maillard@example.com', '0676444477', 0, 4, 3),
       ('Remy', 'Lucas', 'lucas.remy@example.com', '0687446688', 0, 1, 2),
       ('Marchal', 'Lina', 'lina.marchal@example.com', '0698667799', 0, 2, 4),
       ('Gillet', 'Theo', 'theo.gillet@example.com', '0619778800', 0, 3, 1),
       ('Charpentier', 'Mia', 'mia.charpentier@example.com', '0620889911', 0, 4, 3),
       ('Julien', 'Nathan', 'nathan.julien@example.com', '0631990022', 0, 4, 2),
       ('Delattre', 'Noah', 'noah.delattre@example.com', '0642001133', 0, 1, 4),
       ('Durand', 'Sophie', 'sophie.durand@example.com', '0623456789', 0, 2, 1),
       ('Martin', 'Paul', 'paul.martin@example.com', '0634567890', 0, 1, 2),
       ('Bernard', 'Lucie', 'lucie.bernard@example.com', '0645678901', 0, 3, 1),
       ('Morel', 'Hugo', 'hugo.morel@example.com', '0656789012', 0, 2, 3),
       ('Girard', 'Emma', 'emma.girard@example.com', '0667890123', 0, 1, 1),
       ('Lemoine', 'Thomas', 'thomas.lemoine@example.com', '0678901234', 0, 3, 2),
       ('Robert', 'Julie', 'julie.robert@example.com', '0689012345', 0, 2, 1),
       ('Petit', 'Nicolas', 'nicolas.petit@example.com', '0690123456', 0, 1, 3),
       ('Garnier', 'Alice', 'alice.garnier@example.com', '0611234567', 0, 3, 1),
       ('Chevalier', 'Mathieu', 'mathieu.chevalier@example.com', '0622345678', 0, 2, 2),
       ('Blanc', 'Camille', 'camille.blanc@example.com', '0633456789', 0, 1, 1),
       ('Dufour', 'Maxime', 'maxime.dufour@example.com', '0644567890', 0, 3, 3),
       ('Barbier', 'Laura', 'laura.barbier@example.com', '0655678901', 0, 2, 1),
       ('Rousseau', 'Antoine', 'antoine.rousseau@example.com', '0666789012', 0, 1, 2),
       ('Vidal', 'Sophie', 'sophie.vidal@example.com', '0677890123', 0, 3, 1),
       ('Lemoine', 'Pierre', 'pierre.lemoine@example.com', '0688901234', 0, 2, 3),
       ('Collet', 'Marine', 'marine.collet@example.com', '0699012345', 0, 1, 1),
       ('Perrin', 'Louis', 'louis.perrin@example.com', '0611123456', 0, 3, 2),
       ('Leblanc', 'Chloé', 'chloe.leblanc@example.com', '0622234567', 0, 2, 1),
       ('Leclerc', 'Hugo', 'hugo.leclerc@example.com', '0633345678', 0, 1, 3),
       ('Guillaume', 'Manon', 'manon.guillaume@example.com', '0644456789', 0, 3, 1),
       ('Fabre', 'Alexandre', 'alexandre.fabre@example.com', '0655567890', 0, 2, 2),
       ('Olivier', 'Elise', 'elise.olivier@example.com', '0666678901', 0, 1, 1),
       ('Dupuis', 'Gabriel', 'gabriel.dupuis@example.com', '0677789012', 0, 3, 3),
       ('Moulin', 'Sarah', 'sarah.moulin@example.com', '0688890123', 0, 2, 1),
       ('Regnier', 'Clément', 'clement.regnier@example.com', '0699901234', 0, 1, 2),
       ('Marchand', 'Noémie', 'noemie.marchand@example.com', '0611012345', 0, 3, 1),
       ('Simon', 'Julien', 'julien.simon@example.com', '0622123456', 0, 2, 3),
       ('Giraud', 'Alice', 'alice.giraud@example.com', '0633234567', 0, 1, 1),
       ('Benoit', 'Romain', 'romain.benoit@example.com', '0644345678', 0, 3, 2),
       ('Roy', 'Eléonore', 'eleonore.roy@example.com', '0655456789', 0, 2, 1),
       ('Lambert', 'Quentin', 'quentin.lambert@example.com', '0666567890', 0, 1, 3),
       ('Fontaine', 'Eva', 'eva.fontaine@example.com', '0677678901', 0, 3, 1),
       ('Gosselin', 'Mathis', 'mathis.gosselin@example.com', '0688789012', 0, 2, 2),
       ('Carlier', 'Justine', 'justine.carlier@example.com', '0699890123', 0, 1, 1),
       ('Bouvier', 'Lucas', 'lucas.bouvier@example.com', '0610901234', 0, 3, 3),
       ('Maillard', 'Sophie', 'sophie.maillard@example.com', '0621012345', 0, 2, 1),
       ('Perrot', 'Maxence', 'maxence.perrot@example.com', '0632123456', 0, 1, 2),
       ('Navarro', 'Amandine', 'amandine.navarro@example.com', '0643234567', 0, 3, 1),
       ('Pichon', 'Théo', 'theo.pichon@example.com', '0654345678', 0, 2, 3),
       ('Hebert', 'Marion', 'marion.hebert@example.com', '0665456789', 0, 1, 1),
       ('Masson', 'Adrien', 'adrien.masson@example.com', '0676567890', 0, 3, 2),
       ('Lopez', 'Mélanie', 'melanie.lopez@example.com', '0687678901', 0, 2, 1),
       ('Joly', 'Benjamin', 'benjamin.joly@example.com', '0698789012', 0, 1, 3),
       ('Rodriguez', 'Charlotte', 'charlotte.rodriguez@example.com', '0619890123', 0, 3, 1),
       ('Nguyen', 'Axel', 'axel.nguyen@example.com', '0620901234', 0, 2, 2),
       ('Lucas', 'Isabelle', 'isabelle.lucas@example.com', '0631012345', 0, 1, 1),
       ('Brunet', 'Florian', 'florian.brunet@example.com', '0642123456', 0, 3, 3),
       ('Picard', 'Clara', 'clara.picard@example.com', '0653234567', 0, 2, 1);
