package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOCadastroGeral;
import br.com.dwerp.entidade.CadastroGeral;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateCadastroGeral extends DAOGenericoHibernate<CadastroGeral> implements DAOCadastroGeral,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateCadastroGeral(){
		super(CadastroGeral.class);
	}


}
