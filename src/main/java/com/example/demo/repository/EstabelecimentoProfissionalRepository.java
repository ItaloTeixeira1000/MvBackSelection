package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Estabelecimento_Profissional;
import com.example.demo.repository.estabelecimento_profissional.EstabelecimentoProfissionalRepositoryQuery;

public interface EstabelecimentoProfissionalRepository extends JpaRepository<Estabelecimento_Profissional, Long>, EstabelecimentoProfissionalRepositoryQuery {

}
