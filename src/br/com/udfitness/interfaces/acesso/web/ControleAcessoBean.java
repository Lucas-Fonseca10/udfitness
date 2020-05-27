package br.com.udfitness.interfaces.acesso.web;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.udfitness.application.service.AcessoService;
import br.com.udfitness.application.util.ValidationException;
import br.com.udfitness.domain.acesso.TipoAcesso;

@Named
@RequestScoped
public class ControleAcessoBean {

	@EJB
	private AcessoService acessoService;
	
	@Inject
	private FacesContext facesContext;
	
	private String matricula;
	private String cpf;
	
	public String registrarAcesso() {
		TipoAcesso tipoAcesso;
		try {
		tipoAcesso = acessoService.registrarAcesso(matricula,cpf);
		} catch(ValidationException e){
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}
		
		String msg;
		if(tipoAcesso== TipoAcesso.Entrada) {
			msg="ENTRADA registrada!";
		}else if (tipoAcesso== TipoAcesso.Saida) {
			msg= "SAÍDA registrada!";
		}else {
			msg="Dados de registro de acesso inconscistentes";
		}
		
		facesContext.addMessage(null,new FacesMessage(msg));
		return null;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
}
