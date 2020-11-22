package com.example.demo.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.event.RecursoCriadoEvent;
import com.example.demo.model.Profissional;
import com.example.demo.repository.ProfissionalRepository;
import com.example.demo.repository.filter.ProfissionalFilter;
import com.example.demo.service.ProfissionalService;

@RestController
@RequestMapping("/profissional")
public class ProfissionalResource {
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ProfissionalService profissionalService;
	
	@GetMapping
	public Page<Profissional> listar(ProfissionalFilter profissionalFilter, Pageable pageable) {
		return profissionalRepository.filtrar(profissionalFilter, pageable);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Profissional>> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Profissional> profissional = profissionalRepository.findById(codigo);
		return profissional.isPresent() ? ResponseEntity.ok(profissional) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Profissional> criar(@Valid @RequestBody Profissional profissional, HttpServletResponse response) {
		Profissional profissionalSalvo = profissionalRepository.save(profissional);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, profissional.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(profissionalSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		profissionalRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Profissional> atualizar(@PathVariable Long codigo, @Valid @RequestBody Profissional profissional){
		Profissional profissionalSalvo = profissionalService.atualizar(codigo, profissional);
		
		return ResponseEntity.ok(profissionalSalvo);
		
	}

}
