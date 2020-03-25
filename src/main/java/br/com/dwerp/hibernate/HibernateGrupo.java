package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOGrupo;
import br.com.dwerp.entidade.Grupo;

@Dependent
public class HibernateGrupo extends DAOGenericoHibernate<Grupo> implements DAOGrupo,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateGrupo(){
		super(Grupo.class);
	}


}
