package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOSituacaoTributariaPISCOFINS;
import br.com.dwerp.entidade.SituacaoTributariaPISCOFINS;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateSituacaoTributariaPISCOFINS extends DAOGenericoHibernate<SituacaoTributariaPISCOFINS> implements DAOSituacaoTributariaPISCOFINS,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateSituacaoTributariaPISCOFINS(){
		super(SituacaoTributariaPISCOFINS.class);
	}


}
