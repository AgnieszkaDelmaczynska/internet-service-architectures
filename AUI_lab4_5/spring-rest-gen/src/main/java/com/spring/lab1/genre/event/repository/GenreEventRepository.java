package com.spring.lab1.genre.event.repository;

import com.spring.lab1.genre.entity.Genre;
import com.spring.lab1.genre.event.dto.PostGenreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class GenreEventRepository {
    
    private RestTemplate restTemplate;
    
    @Autowired
    public GenreEventRepository(@Value("http://cin:8081/api/") String baseUrl) { //http://localhost:8081/api/
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Genre genre) {
        restTemplate.delete("/genres/{name}", genre.getName());
    }

    public void create(Genre genre) {
        restTemplate.postForLocation("/genres", PostGenreRequest.entityToDtoMapper().apply(genre));
    }
}
