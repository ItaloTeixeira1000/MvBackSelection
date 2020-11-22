package com.example.demo.repository.profissional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Profissional;
import com.example.demo.repository.filter.ProfissionalFilter;

public interface ProfissionalRepositoryQuery {
	
	public Page<Profissional> filtrar(ProfissionalFilter profissionalFilter, Pageable pageable);

}
