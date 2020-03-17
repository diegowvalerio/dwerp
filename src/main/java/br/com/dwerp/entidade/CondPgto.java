package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

@Entity
@Table(name="tbcondicaopgto")
public class CondPgto implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer idcondpgto;
	@Column(nullable=false,columnDefinition="varchar(50)")
	private String nome;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	private Boolean situacao;
	@Column(nullable=false)
	private Integer qtdeparcelas;	

	public CondPgto() {
		super();
	}   
	public Integer getIdcondpgto() {
		return this.idcondpgto;
	}

	public void setIdcondpgto(Integer idcondpgto) {
		this.idcondpgto = idcondpgto;
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
	public Integer getQtdeparcelas() {
		return this.qtdeparcelas;
	}

	public void setQtdeparcelas(Integer qtdeparcelas) {
		this.qtdeparcelas = qtdeparcelas;
	}   

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idcondpgto == null) ? 0 : idcondpgto.hashCode());
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
		CondPgto other = (CondPgto) obj;
		if (idcondpgto == null) {
			if (other.idcondpgto != null)
				return false;
		} else if (!idcondpgto.equals(other.idcondpgto))
			return false;
		return true;
	}
   
}
