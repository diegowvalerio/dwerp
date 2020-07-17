package br.com.dwerp.hibernate.generico;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.dwerp.dao.generico.DAOGenerico;
import br.com.dwerp.entidade.Cidade;
import br.com.dwerp.entidade.Produto;


public class DAOGenericoHibernate<E> implements DAOGenerico<E>, Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected EntityManager manager;
	private Class classeEntidade;
	
	public DAOGenericoHibernate(Class classeEntidade){
		this.classeEntidade = classeEntidade;
	}

	@Override
	public E salvar(E e) {
		manager.persist(e);
		return e;
	}
	
	
	@Override
	public E alterar(E e) {
		return manager.merge(e);
	}

	@Override
	public boolean excluir(Integer id) {
		E e = consultar(id);
		manager.remove(e);
		return true;
	}

	
	@Override
	public E consultar(Integer id) {
		return (E) manager.find(classeEntidade, id);
	}

	
	@Override
	public List<E> consultar() {
		return manager.createQuery("from "+classeEntidade.getSimpleName()).getResultList();
	}
	
	@Override
	public List<E> consultar_ativos() {		
		return manager.createQuery("from "+classeEntidade.getSimpleName()+" where situacao = true").getResultList();
	}
	
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<E> buscacidadenome(String e){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cidade.class);
		
		criteria.add(Restrictions.ilike("nome", e.toUpperCase(),MatchMode.START));
		
		return criteria.list();
	}
	
	//busca produto por nome
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<E> buscaproduto(String e){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);
		
		criteria.add(Restrictions.ilike("descricao", e.toUpperCase(),MatchMode.START));
		criteria.add(Restrictions.not(Restrictions.in("tipoproduto", "ACABADO")));
		criteria.add(Restrictions.eq("situacao", Boolean.TRUE));
		
		return criteria.list();
	}
	
}
