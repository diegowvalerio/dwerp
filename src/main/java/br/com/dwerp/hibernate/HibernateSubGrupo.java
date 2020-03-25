package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOSubGrupo;
import br.com.dwerp.entidade.SubGrupo;

@Dependent
public class HibernateSubGrupo extends DAOGenericoHibernate<SubGrupo> implements DAOSubGrupo,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateSubGrupo(){
		super(SubGrupo.class);
	}


}
