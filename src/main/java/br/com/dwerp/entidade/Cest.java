package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="tbcest")
public class Cest implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Expose
	private Integer idcest;
	@Column(nullable=false,columnDefinition="varchar(7)")
	@Expose
	private String cest;
	@Column(nullable=true,columnDefinition="varchar(8)")
	@Expose
	private String ncm;
	@Column(nullable=false,columnDefinition="varchar(100)")
	@Expose
	private String descricao;
	
	public Cest() {
		super();
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public Integer getIdcest() {
		return idcest;
	}

	public void setIdcest(Integer idcest) {
		this.idcest = idcest;
	}

	public String getCest() {
		return cest;
	}

	public void setCest(String cest) {
		this.cest = cest;
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
		result = prime * result + ((idcest == null) ? 0 : idcest.hashCode());
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
		Cest other = (Cest) obj;
		if (idcest == null) {
			if (other.idcest != null)
				return false;
		} else if (!idcest.equals(other.idcest))
			return false;
		return true;
	}
}
