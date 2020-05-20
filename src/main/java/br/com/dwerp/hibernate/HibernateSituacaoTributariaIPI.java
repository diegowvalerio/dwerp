package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOSituacaoTributariaIPI;
import br.com.dwerp.entidade.SituacaoTributariaIPI;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateSituacaoTributariaIPI extends DAOGenericoHibernate<SituacaoTributariaIPI> implements DAOSituacaoTributariaIPI,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateSituacaoTributariaIPI(){
		super(SituacaoTributariaIPI.class);
	}


}
