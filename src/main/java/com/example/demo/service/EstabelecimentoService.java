package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Estabelecimento;
import com.example.demo.repository.EstabelecimentoRepository;

@Service
public class EstabelecimentoService {

	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	
	public Estabelecimento atualizar(Long codigo, Estabelecimento estabelecimento) {
		Estabelecimento estabelecimentoSalvo = buscarPeloCodigo(codigo);
		
		BeanUtils.copyProperties(estabelecimento, estabelecimentoSalvo, "codigo");
		
		return estabelecimentoRepository.save(estabelecimentoSalvo);
	}
	
	private Estabelecimento buscarPeloCodigo(Long codigo) {
		Optional<Estabelecimento> estabelecimentoSalvo = estabelecimentoRepository.findById(codigo);
		
		if(!estabelecimentoSalvo.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return estabelecimentoSalvo.get();
	}
}
