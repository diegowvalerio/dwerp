package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

@Entity
@Table(name="tbformapag")
public class FormaPag implements Serializable {

	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer idformapag;
	@Column(nullable=false,columnDefinition="varchar(100)")
	private String nome;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	private Boolean situacao;
	private static final long serialVersionUID = 1L;

	public FormaPag() {
		super();
	}   
	public Integer getIdformapag() {
		return this.idformapag;
	}

	public void setIdformapag(Integer idformapag) {
		this.idformapag = idformapag;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public Boolean getSituacao() {
		return this.situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idformapag == null) ? 0 : idformapag.hashCode());
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
		FormaPag other = (FormaPag) obj;
		if (idformapag == null) {
			if (other.idformapag != null)
				return false;
		} else if (!idformapag.equals(other.idformapag))
			return false;
		return true;
	}
   
}
