package br.com.dwerp.dao.generico;

import java.util.Date;
import java.util.List;


public interface DAOGenerico<E> {
	public E salvar(E e);
	public E alterar(E e);
	public boolean excluir(Integer id);
	public E consultar(Integer id);
	public List<E> consultar();	
	public List<E> consultar_ativos();	
	public List<E> consultar_funcionarios();	
	
	public List<E> consultar_ocorrencia_usuario(String e);
	
	public List<E> buscacidadenome(String e);
	public List<E> buscaproduto(String e);
	
	
	//index de resumo
	
	//buscar clientes novos no periodo informado no painel de resumo
	public List<E> clientesnovos(Date data1, Date data2);
	
}
