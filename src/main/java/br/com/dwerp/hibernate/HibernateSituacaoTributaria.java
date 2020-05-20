package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOSituacaoTributaria;
import br.com.dwerp.entidade.SituacaoTributaria;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateSituacaoTributaria extends DAOGenericoHibernate<SituacaoTributaria> implements DAOSituacaoTributaria,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateSituacaoTributaria(){
		super(SituacaoTributaria.class);
	}


}
