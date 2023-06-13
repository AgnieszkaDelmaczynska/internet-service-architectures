package com.spring.lab1.genre.entity;

import com.spring.lab1.cinema.entity.Cinema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    private String name;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Cinema> cinemas;

}
