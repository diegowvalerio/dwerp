package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

@Entity
@Table(name="tbcentroconsumo")
public class CentroConsumo implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer idcentroconsumo;
	@Column(nullable=false,columnDefinition="varchar(50)")
	private String nome;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	private Boolean situacao;
	
	public CentroConsumo() {
		super();
	}   
	 
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdcentroconsumo() {
		return idcentroconsumo;
	}

	public void setIdcentroconsumo(Integer idcentroconsumo) {
		this.idcentroconsumo = idcentroconsumo;
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
		result = prime * result + ((idcentroconsumo == null) ? 0 : idcentroconsumo.hashCode());
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
		CentroConsumo other = (CentroConsumo) obj;
		if (idcentroconsumo == null) {
			if (other.idcentroconsumo != null)
				return false;
		} else if (!idcentroconsumo.equals(other.idcentroconsumo))
			return false;
		return true;
	}   
   
}
