package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOProduto;
import br.com.dwerp.entidade.Produto;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateProduto extends DAOGenericoHibernate<Produto> implements DAOProduto,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateProduto(){
		super(Produto.class);
	}


}
