package com.example.demo.repository.profissional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.example.demo.model.Profissional;
import com.example.demo.repository.filter.ProfissionalFilter;

public class ProfissionalRepositoryImpl implements ProfissionalRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Profissional> filtrar(ProfissionalFilter profissionalFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Profissional> criteria = builder.createQuery(Profissional.class);
		Root<Profissional> root = criteria.from(Profissional.class);
		
		Predicate[] predicates = criarRestricoes(profissionalFilter, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<Profissional> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(profissionalFilter));
	}
	
	private Long total(ProfissionalFilter profissionalFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Profissional> root = criteria.from(Profissional.class);
		
		Predicate[] predicates = criarRestricoes(profissionalFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroPorPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroPorPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
private Predicate[] criarRestricoes(ProfissionalFilter profissionalFilter, CriteriaBuilder builder, Root<Profissional> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(profissionalFilter.getNome())) {
			
			predicates.add(builder.like(builder.lower(root.get("nome")), "%" + profissionalFilter.getNome().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}


}
