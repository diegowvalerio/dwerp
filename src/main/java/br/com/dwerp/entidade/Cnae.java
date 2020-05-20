package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="tbcnae")
public class Cnae implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Expose
	private Integer idcnae;
	@Column(nullable=false,columnDefinition="varchar(8)")
	@Expose
	private String cnae;
	@Column(nullable=false,columnDefinition="varchar(200)")
	@Expose
	private String descricao;
	@Column(nullable=true, columnDefinition="numeric(5,2)")
	private double perc_cnae;
	
	public Cnae() {
		super();
	}

	public Integer getIdcnae() {
		return idcnae;
	}

	public void setIdcnae(Integer idcnae) {
		this.idcnae = idcnae;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPerc_cnae() {
		return perc_cnae;
	}

	public void setPerc_cnae(double perc_cnae) {
		this.perc_cnae = perc_cnae;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idcnae == null) ? 0 : idcnae.hashCode());
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
		Cnae other = (Cnae) obj;
		if (idcnae == null) {
			if (other.idcnae != null)
				return false;
		} else if (!idcnae.equals(other.idcnae))
			return false;
		return true;
	}

	
}
