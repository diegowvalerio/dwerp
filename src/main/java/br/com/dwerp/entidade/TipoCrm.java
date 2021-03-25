package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "tbtipocrm")
public class TipoCrm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer idtipocrm;

	@Column(nullable = false, columnDefinition = "varchar(100)")
	@Expose
	private String descricao;

	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	private Boolean situacao;
	
	public TipoCrm() {
		super();
	}

	public Integer getIdtipocrm() {
		return idtipocrm;
	}

	public void setIdtipocrm(Integer idtipocrm) {
		this.idtipocrm = idtipocrm;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idtipocrm == null) ? 0 : idtipocrm.hashCode());
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
		TipoCrm other = (TipoCrm) obj;
		if (idtipocrm == null) {
			if (other.idtipocrm != null)
				return false;
		} else if (!idtipocrm.equals(other.idtipocrm))
			return false;
		return true;
	}

	
}
