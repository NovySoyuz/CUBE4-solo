package com.example.api.repository;

import com.example.api.model.Employees;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends CrudRepository<Employees, Integer>
{
    // Faire attention à la nomenclature !!!
    // findBy = inqique que c'est une requete de recherche

    // Methode pour trouver des employés par leur nom
    // List<> Une List en Java est particulièrement appropriée pour les scénarios dans lesquels la
    // taille du volume de données n’est pas connue au préalable ou peut évoluer au fil du temps
    List<Employees> findByNameIgnoreCase(String name);
    // Service_id indique qu'on veut récuperer des id dans l'entité Services
    List<Employees> findByServices_id(Integer id);
    List<Employees>findBySite_id(Integer id);
    // Query spécifique à notre route
    @Query(
            value = "SELECT * FROM employees WHERE is_admin = 1 AND name = :name AND firstname = :firstname",
            nativeQuery = true
    )
    // Ne doit jamais manipuler de DTO
    // Doit toujours manipuler des entités modele
    Employees loginAdmin(@Param("name") String name, @Param("firstname") String firstname);
}
