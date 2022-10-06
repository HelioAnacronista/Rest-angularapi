package com.example.algamoneyapi.controllers;

import com.example.algamoneyapi.model.Categoria;
import com.example.algamoneyapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/categoria")
public class CategoriaControllers {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping
    public List<Categoria> findall() {
        return repository.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> findById(@PathVariable Long codigo, HttpServletResponse response) {
        Optional<Categoria> categoriaEncontrada = repository.findById(codigo);
        if (categoriaEncontrada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.of(categoriaEncontrada);
    }

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody Categoria dto, HttpServletResponse response) {
        Categoria categoriaSave = repository.save(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(categoriaSave.getCodigo()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(categoriaSave);
    }

    @DeleteMapping("/{codigo}")
    public void delete(@PathVariable Long codigo) {
        repository.deleteById(codigo);
    }
}
