package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOCentroConsumo;
import br.com.dwerp.entidade.CentroConsumo;

@Dependent
public class HibernateCentroConsumo extends DAOGenericoHibernate<CentroConsumo> implements DAOCentroConsumo,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateCentroConsumo(){
		super(CentroConsumo.class);
	}


}
