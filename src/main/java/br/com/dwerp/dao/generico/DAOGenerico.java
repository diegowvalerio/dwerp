package br.com.dwerp.dao.generico;

import java.util.List;

public interface DAOGenerico<E> {
	public E salvar(E e);
	public E alterar(E e);
	public boolean excluir(Integer id);
	public E consultar(Integer id);
	public List<E> consultar();	
	
	public List<E> buscacidadenome(String e);
	
}
