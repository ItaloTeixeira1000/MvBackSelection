package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Profissional;
import com.example.demo.repository.ProfissionalRepository;

@Service
public class ProfissionalService {
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	public Profissional atualizar(Long codigo, Profissional profissional) {
		Profissional profissionalSalvo = buscarPeloCodigo(codigo);
		
		BeanUtils.copyProperties(profissional, profissionalSalvo, "codigo");
		
		return profissionalRepository.save(profissionalSalvo);
	}
	
	private Profissional buscarPeloCodigo(Long codigo) {
		Optional<Profissional> profissionalSalvo = profissionalRepository.findById(codigo);
		
		if(!profissionalSalvo.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return profissionalSalvo.get();
	}
}
