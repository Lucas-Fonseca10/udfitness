package br.com.udfitness.application.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.udfitness.domain.aluno.Estado;
import br.com.udfitness.domain.aluno.EstadoRepository;
import br.com.udfitness.domain.aluno.Aluno.Sexo;
import br.com.udfitness.domain.aluno.Aluno.Situacao;


@Stateless
public class DataService {

	@EJB
	private EstadoRepository estadoRepository;
	
	public List<Estado>listEstados(){
		return estadoRepository.listEstado();
			
	}
	
	public Sexo[] getSexos() {
		return Sexo.values();
		
	}
	
	public Situacao[] getSituacoes() {
		return Situacao.values();
		
	}
	
	
}
