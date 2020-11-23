package com.example.demo.repository.estabelecimento;

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

import com.example.demo.model.Estabelecimento;
import com.example.demo.repository.filter.EstabelecimentoFilter;

public class EstabelecimentoRepositoryImpl implements EstabelecimentoRespositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Estabelecimento> filtrar(EstabelecimentoFilter estabelecimentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Estabelecimento> criteria = builder.createQuery(Estabelecimento.class);
		Root<Estabelecimento> root = criteria.from(Estabelecimento.class);
		
		Predicate[] predicates = criarRestricoes(estabelecimentoFilter, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<Estabelecimento> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(estabelecimentoFilter));
	}
	
	private Long total(EstabelecimentoFilter estabelecimentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Estabelecimento> root = criteria.from(Estabelecimento.class);
		
		Predicate[] predicates = criarRestricoes(estabelecimentoFilter, builder, root);
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
	
private Predicate[] criarRestricoes(EstabelecimentoFilter estabelecimentoFilter, CriteriaBuilder builder, Root<Estabelecimento> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(estabelecimentoFilter.getNome())) {
			
			predicates.add(builder.like(builder.lower(root.get("nome")), "%" + estabelecimentoFilter.getNome().toLowerCase() + "%"));
		}
		
		if(!StringUtils.isEmpty(estabelecimentoFilter.getCodigo())) {
			predicates.add(builder.equal(root.get("codigo"), estabelecimentoFilter.getCodigo()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}


}
