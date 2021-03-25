package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOTipoCrm;
import br.com.dwerp.entidade.TipoCrm;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateTipoCrm extends DAOGenericoHibernate<TipoCrm> implements DAOTipoCrm,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateTipoCrm(){
		super(TipoCrm.class);
	}


}
