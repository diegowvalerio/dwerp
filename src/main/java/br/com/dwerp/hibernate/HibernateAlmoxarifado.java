package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOAlmoxarifado;
import br.com.dwerp.entidade.Almoxarifado;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateAlmoxarifado extends DAOGenericoHibernate<Almoxarifado> implements DAOAlmoxarifado,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateAlmoxarifado(){
		super(Almoxarifado.class);
	}


}
