package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Profissional;
import com.example.demo.repository.profissional.ProfissionalRepositoryQuery;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long>, ProfissionalRepositoryQuery {

}
