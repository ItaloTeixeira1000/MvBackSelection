package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estabelecimento_profissional")
public class Estabelecimento_Profissional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private Long codigo_profissional;
	private Long codigo_estabelecimento;
	
	public Long getCodigo_profissional() {
		return codigo_profissional;
	}
	public void setCodigo_profissional(Long codigo_profissional) {
		this.codigo_profissional = codigo_profissional;
	}
	public Long getCodigo_estabelecimento() {
		return codigo_estabelecimento;
	}
	public void setCodigo_estabelecimento(Long codigo_estabelecimento) {
		this.codigo_estabelecimento = codigo_estabelecimento;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estabelecimento_Profissional other = (Estabelecimento_Profissional) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
}
