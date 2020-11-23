package com.example.demo.repository.estabelecimento_profissional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Estabelecimento_Profissional;
import com.example.demo.repository.filter.EstabelecimentoProfissionalFilter;

public interface EstabelecimentoProfissionalRepositoryQuery {
	
	public Page<Estabelecimento_Profissional> filtrar(EstabelecimentoProfissionalFilter epf, Pageable pageable);

}
