package com.example.demo.repository.estabelecimento_profissional;

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
import com.example.demo.model.Estabelecimento_Profissional;
import com.example.demo.repository.filter.EstabelecimentoProfissionalFilter;

public class EstabelecimentoProfissionalRepositoryImpl implements EstabelecimentoProfissionalRepositoryQuery {
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Estabelecimento_Profissional> filtrar(EstabelecimentoProfissionalFilter epf, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Estabelecimento_Profissional> criteria = builder.createQuery(Estabelecimento_Profissional.class);
		Root<Estabelecimento_Profissional> root = criteria.from(Estabelecimento_Profissional.class);
		
		Predicate[] predicates = criarRestricoes(epf, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<Estabelecimento_Profissional> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(epf));
	}
	
	private Long total(EstabelecimentoProfissionalFilter estabelecimentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Estabelecimento_Profissional> root = criteria.from(Estabelecimento_Profissional.class);
		
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
	
private Predicate[] criarRestricoes(EstabelecimentoProfissionalFilter estabelecimentoFilter, CriteriaBuilder builder, Root<Estabelecimento_Profissional> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
