package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "tbcanal")
public class Canal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer idcanal;

	@Column(nullable = false, columnDefinition = "varchar(100)")
	@Expose
	private String descricao;
	
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	private Boolean situacao;

	public Canal() {
		super();
	}

	public Integer getIdcanal() {
		return idcanal;
	}

	public void setIdcanal(Integer idcanal) {
		this.idcanal = idcanal;
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
		result = prime * result + ((idcanal == null) ? 0 : idcanal.hashCode());
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
		Canal other = (Canal) obj;
		if (idcanal == null) {
			if (other.idcanal != null)
				return false;
		} else if (!idcanal.equals(other.idcanal))
			return false;
		return true;
	}

}
