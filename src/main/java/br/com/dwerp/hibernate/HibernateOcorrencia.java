package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOOcorrencia;
import br.com.dwerp.entidade.Ocorrencia;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateOcorrencia extends DAOGenericoHibernate<Ocorrencia> implements DAOOcorrencia,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateOcorrencia(){
		super(Ocorrencia.class);
	}


}
