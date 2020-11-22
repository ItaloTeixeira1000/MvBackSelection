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
import com.example.demo.model.Estabelecimento;
import com.example.demo.repository.EstabelecimentoRepository;
import com.example.demo.repository.filter.EstabelecimentoFilter;
import com.example.demo.service.EstabelecimentoService;

@RestController
@RequestMapping("/estabelecimento")
public class EstabelecimentoResource {
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private EstabelecimentoService estabelecimentoService;
	
	@GetMapping
	public Page<Estabelecimento> listar(EstabelecimentoFilter estabelecimentoFilter, Pageable pageable) {
		return estabelecimentoRepository.filtrar(estabelecimentoFilter, pageable);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Estabelecimento>> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findById(codigo);
		return estabelecimento.isPresent() ? ResponseEntity.ok(estabelecimento) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Estabelecimento> criar(@RequestBody @Valid Estabelecimento estabelecimento, HttpServletResponse response) {
		Estabelecimento estabelecimentoSalvo = estabelecimentoRepository.save(estabelecimento);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, estabelecimento.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(estabelecimentoSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		estabelecimentoRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Estabelecimento> atualizar(@PathVariable Long codigo, @Valid @RequestBody Estabelecimento estabelecimento){
		Estabelecimento estabelecimentoSalvo = estabelecimentoService.atualizar(codigo, estabelecimento);
		
		return ResponseEntity.ok(estabelecimentoSalvo);
		
	}
	
}
