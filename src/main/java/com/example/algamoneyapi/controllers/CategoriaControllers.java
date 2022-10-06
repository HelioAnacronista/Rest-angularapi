package com.example.algamoneyapi.controllers;

import com.example.algamoneyapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/categoria")
public class CategoriaControllers {

    @Autowired
    private CategoriaRepository repository;


}
