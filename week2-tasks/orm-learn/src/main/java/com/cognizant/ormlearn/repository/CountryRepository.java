package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CountryRepository - Spring Data JPA repository for Country.
 *
 * Hands-on 1: Extends JpaRepository<Country, String> where:
 *   - Country is the entity type
 *   - String  is the primary key type (country code)
 *
 * Spring Data JPA auto-generates implementations for all standard CRUD
 * operations (findAll, findById, save, deleteById, etc.) at runtime.
 * No implementation class is needed.
 *
 * Hands-on 2 (Query Methods): demonstrates Spring Data JPA's ability
 * to generate queries from method names without writing any SQL.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    /**
     * Hands-on 2 - Query Method 1:
     * Find all countries whose name contains the given text (case-insensitive).
     * Spring generates: SELECT * FROM country WHERE UPPER(co_name) LIKE UPPER('%text%')
     *
     * Usage: countryRepository.findByNameContainingIgnoreCase("ou")
     */
    List<Country> findByNameContainingIgnoreCase(String text);

    /**
     * Hands-on 2 - Query Method 2:
     * Same as above but results sorted by name ascending.
     * Adding "OrderByNameAsc" to the method name tells Spring to add ORDER BY.
     */
    List<Country> findByNameContainingIgnoreCaseOrderByNameAsc(String text);

    /**
     * Hands-on 2 - Query Method 3:
     * Find all countries whose name starts with a given letter/prefix.
     * Spring generates: SELECT * FROM country WHERE co_name LIKE 'Z%'
     *
     * Usage: countryRepository.findByNameStartingWith("Z")
     */
    List<Country> findByNameStartingWith(String prefix);
}
