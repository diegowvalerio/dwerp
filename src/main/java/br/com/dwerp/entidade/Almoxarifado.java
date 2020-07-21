package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="tbalmoxarifado")
public class Almoxarifado implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Expose
	private Integer idalmoxarifado;

	@Column(nullable=false,columnDefinition="varchar(100)")
	@Expose
	private String descricao;
	
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	private Boolean situacao;
	
	public Almoxarifado() {
		super();
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getIdalmoxarifado() {
		return idalmoxarifado;
	}
	public void setIdalmoxarifado(Integer idalmoxarifado) {
		this.idalmoxarifado = idalmoxarifado;
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
		result = prime * result + ((idalmoxarifado == null) ? 0 : idalmoxarifado.hashCode());
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
		Almoxarifado other = (Almoxarifado) obj;
		if (idalmoxarifado == null) {
			if (other.idalmoxarifado != null)
				return false;
		} else if (!idalmoxarifado.equals(other.idalmoxarifado))
			return false;
		return true;
	}

}
