package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="tbncm")
public class Ncm implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Expose
	private Integer idncm;
	@Column(nullable=false,columnDefinition="varchar(8)")
	@Expose
	private String ncm;
	@Column(nullable=false,columnDefinition="varchar(100)")
	@Expose
	private String descricao;
	@Column(nullable=false, columnDefinition="numeric(6,2)")
	private Double ipi;
	@ManyToOne
	@JoinColumn(nullable = true)
	private RegraImposto regraImposto;
	
	public Ncm() {
		super();
	}

	public Integer getIdncm() {
		return idncm;
	}

	public void setIdncm(Integer idncm) {
		this.idncm = idncm;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getIpi() {
		return ipi;
	}

	public void setIpi(Double ipi) {
		this.ipi = ipi;
	}

	public RegraImposto getRegraImposto() {
		return regraImposto;
	}

	public void setRegraImposto(RegraImposto regraImposto) {
		this.regraImposto = regraImposto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idncm == null) ? 0 : idncm.hashCode());
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
		Ncm other = (Ncm) obj;
		if (idncm == null) {
			if (other.idncm != null)
				return false;
		} else if (!idncm.equals(other.idncm))
			return false;
		return true;
	}
	
}
