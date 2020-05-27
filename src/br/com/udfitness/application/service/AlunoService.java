package br.com.udfitness.application.service;


import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.udfitness.application.util.StringUtils;
import br.com.udfitness.application.util.Validation;
import br.com.udfitness.application.util.ValidationException;
import br.com.udfitness.domain.acesso.Acesso;
import br.com.udfitness.domain.aluno.Aluno;
import br.com.udfitness.domain.aluno.Aluno.Situacao;
import br.com.udfitness.domain.aluno.AlunoRepository;

@Stateless
public class AlunoService {

	@EJB
	private AlunoRepository alunoRepository;
	
	public void createOrUpdate(Aluno aluno) {

		if(StringUtils.isEmpty(aluno.getMatricula())) {
			create(aluno);
		}else {
			update(aluno);
			
		}
		
	}
	
	private void create(Aluno aluno) {
		Validation.assertNotEmpty(aluno);
		
		String maxMatricula = alunoRepository.getMaxMatriculaAno();
		aluno.gerarMatricula(maxMatricula);
		alunoRepository.store(aluno);
	}
	
	public void delete(String matricula) {
		alunoRepository.delete(matricula);
		
	}
	
	
	private void update(Aluno aluno) {
		Validation.assertNotEmpty(aluno);
		Validation.assertNotEmpty(aluno.getMatricula());
		alunoRepository.update(aluno);
	}
	
	public Aluno findByMatricula(String matricula) {
		return alunoRepository.findByMatricula(matricula);
		
	}
	
	public List<Aluno> listAlunos(String matricula, String nome, String cpf, Integer telefone){
		if(StringUtils.isEmpty(matricula) && StringUtils.isEmpty(nome) &&  StringUtils.isEmpty(cpf) && telefone==null ) {
			throw new ValidationException("Pelo menos um críterio de pesquisa deve ser fornecido");
		}
		
		return alunoRepository.listAlunos(matricula, nome, cpf, telefone);
		
	}
	
	public List<Aluno> listSituacoesAlunos(Situacao situacao){
		Validation.assertNotEmpty(situacao);
		return alunoRepository.listSituacoesAlunos(situacao);
		
	}
	
	public List<Acesso> listAcessosAlunos(String matricula, LocalDate dataInicial, LocalDate dataFinal){
		if(StringUtils.isEmpty(matricula) && dataInicial == null & dataFinal == null) {
			throw new ValidationException("Pelo menos um críterio de pesquisa deve ser fornecido");
		}
		
		
		return alunoRepository.listAcessosAlunos(matricula, dataInicial, dataFinal);
		
	}
}
