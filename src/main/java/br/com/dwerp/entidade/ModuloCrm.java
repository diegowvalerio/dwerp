package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "tbmodulocrm")
public class ModuloCrm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer idmodulocrm;

	@Column(nullable = false, columnDefinition = "varchar(100)")
	@Expose
	private String descricao;
	
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	private Boolean situacao;

	public ModuloCrm() {
		super();
	}
	
	public Integer getIdmodulocrm() {
		return idmodulocrm;
	}

	public void setIdmodulocrm(Integer idmodulocrm) {
		this.idmodulocrm = idmodulocrm;
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
		result = prime * result + ((idmodulocrm == null) ? 0 : idmodulocrm.hashCode());
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
		ModuloCrm other = (ModuloCrm) obj;
		if (idmodulocrm == null) {
			if (other.idmodulocrm != null)
				return false;
		} else if (!idmodulocrm.equals(other.idmodulocrm))
			return false;
		return true;
	}

}
