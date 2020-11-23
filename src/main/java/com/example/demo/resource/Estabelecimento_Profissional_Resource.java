package com.example.demo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Estabelecimento_Profissional;
import com.example.demo.repository.EstabelecimentoProfissionalRepository;
import com.example.demo.repository.filter.EstabelecimentoProfissionalFilter;

@RestController
@RequestMapping("/estabelecimento-profissional")
public class Estabelecimento_Profissional_Resource {
	
	@Autowired
	private EstabelecimentoProfissionalRepository estabelecimentoProfissionalRepository;

	@GetMapping
	public Page<Estabelecimento_Profissional> listar(EstabelecimentoProfissionalFilter epf, Pageable pageable) {
		return estabelecimentoProfissionalRepository.filtrar(epf, pageable);
	}
	
}
