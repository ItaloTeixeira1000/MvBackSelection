package com.example.demo.repository.estabelecimento;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Estabelecimento;
import com.example.demo.repository.filter.EstabelecimentoFilter;

public interface EstabelecimentoRespositoryQuery {
	public Page<Estabelecimento> filtrar(EstabelecimentoFilter estabelecimentoFilter, Pageable pageable);

}
