package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="tbfuncao")
public class Funcao implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Expose
	private Integer idfuncao;
	
	@Column(nullable=false,columnDefinition="varchar(10)")
	@Expose
	private String cbo;
	
	@Column(nullable=false,columnDefinition="varchar(150)")
	@Expose
	private String descricao;
	
	public Funcao() {
		super();
	}

	public Integer getIdfuncao() {
		return idfuncao;
	}

	public void setIdfuncao(Integer idfuncao) {
		this.idfuncao = idfuncao;
	}

	public String getCbo() {
		return cbo;
	}

	public void setCbo(String cbo) {
		this.cbo = cbo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idfuncao == null) ? 0 : idfuncao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcao other = (Funcao) obj;
		if (idfuncao == null) {
			if (other.idfuncao != null)
				return false;
		} else if (!idfuncao.equals(other.idfuncao))
			return false;
		return true;
	}

	
}
