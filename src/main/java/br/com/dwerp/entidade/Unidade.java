package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="tbunidade")
public class Unidade implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Expose
	private Integer idunidade;
	@Column(nullable=false,columnDefinition="varchar(3)")
	@Expose
	private String unidade;
	@Column(nullable=false,columnDefinition="varchar(100)")
	@Expose
	private String descricao;
	
	public Unidade() {
		super();
	}
	public Integer getIdunidade() {
		return idunidade;
	}


	public void setIdunidade(Integer idunidade) {
		this.idunidade = idunidade;
	}


	public String getUnidade() {
		return unidade;
	}


	public void setUnidade(String unidade) {
		this.unidade = unidade;
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
		result = prime * result + ((idunidade == null) ? 0 : idunidade.hashCode());
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
		Unidade other = (Unidade) obj;
		if (idunidade == null) {
			if (other.idunidade != null)
				return false;
		} else if (!idunidade.equals(other.idunidade))
			return false;
		return true;
	}
}
