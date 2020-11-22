package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Estabelecimento;
import com.example.demo.repository.estabelecimento.EstabelecimentoRespositoryQuery;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long>, EstabelecimentoRespositoryQuery{

}
