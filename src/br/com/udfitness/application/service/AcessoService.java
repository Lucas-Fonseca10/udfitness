package br.com.udfitness.application.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.udfitness.application.util.StringUtils;
import br.com.udfitness.application.util.ValidationException;
import br.com.udfitness.domain.acesso.Acesso;
import br.com.udfitness.domain.acesso.AcessoRepository;
import br.com.udfitness.domain.acesso.TipoAcesso;
import br.com.udfitness.domain.aluno.Aluno;
import br.com.udfitness.domain.aluno.AlunoRepository;

@Stateless
public class AcessoService {

	@EJB
	private AcessoRepository acessoRepository;

	@EJB
	private AlunoRepository alunoRepository;
	
	
	public TipoAcesso registrarAcesso(String matricula, String cpf) {
		
		if(StringUtils.isEmpty(matricula) && StringUtils.isEmpty(cpf) ) {
			throw new ValidationException("É preciso fornecer a matrícula ou o CPF");
		}
		
		Aluno aluno;
		if(StringUtils.isEmpty(matricula)) {
			aluno = alunoRepository.findByCpf(cpf);
		}else {
			aluno = alunoRepository.findByMatricula(matricula);
		}
		
		if(aluno==null) {
			throw new ValidationException("O aluno não foi encontrado");
		}
		
		Acesso ultimoAcesso = acessoRepository.findUltimoAcesso(aluno);
		TipoAcesso tipoAcesso;
		
		
		if(ultimoAcesso == null || ultimoAcesso.isEntradaSaidaPreenchidas()) {
			ultimoAcesso= new Acesso();
			ultimoAcesso.setAluno(aluno);
			tipoAcesso = ultimoAcesso.registrarAcesso();
			acessoRepository.store(ultimoAcesso);	
		} else {
			tipoAcesso = ultimoAcesso.registrarAcesso();
		}
		
		return tipoAcesso;
	}
}
