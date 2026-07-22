package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Country - JPA Entity class mapped to the 'country' database table.
 *
 * Hands-on 1 (Quick Example): Demonstrates the core JPA mapping annotations.
 *
 * @Entity  - Marks this class as a JPA-managed entity. Hibernate will
 *            create/validate a matching database table for it.
 *
 * @Table   - Specifies the exact table name in the database.
 *            Without this, JPA would default to the class name ("Country").
 *
 * @Id     - Marks the primary key field.
 *
 * @Column - Maps a Java field to a specific database column name.
 *           Without @Column, JPA uses the field name as the column name.
 */
@Entity
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "co_code")
    private String code;

    @Column(name = "co_name")
    private String name;

    // ----------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------

    public Country() {
    }

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    // ----------------------------------------------------------------
    // Getters and Setters
    // ----------------------------------------------------------------

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ----------------------------------------------------------------
    // toString
    // ----------------------------------------------------------------

    @Override
    public String toString() {
        return "Country{code='" + code + "', name='" + name + "'}";
    }
}
