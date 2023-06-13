package com.spring.lab1.genre.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "genres")
public class Genre {

    /**
     * Genre name.
     */
    @Id
    private String name;

    /**
     * The year genre was created.
     */
    private int year_of_creation;

    /**
     * Genre precursor.
     */
    private String precursor;

    /**
     * Level of difficulty of the genre.
     */
    private int popularity;

}
