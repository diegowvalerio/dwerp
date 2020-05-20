package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="tbsituacaotributaria")
public class SituacaoTributaria implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Expose
	private Integer idsituacaotributaria;
	
	@Column(nullable=false,columnDefinition="varchar(3)")
	@Expose
	private String situacaotributaria;
	
	@Column(nullable=false,columnDefinition="varchar(100)")
	@Expose
	private String nomesituacaotributaria;


	public SituacaoTributaria() {
		super();
	}


	public Integer getIdsituacaotributaria() {
		return idsituacaotributaria;
	}


	public void setIdsituacaotributaria(Integer idsituacaotributaria) {
		this.idsituacaotributaria = idsituacaotributaria;
	}


	public String getSituacaotributaria() {
		return situacaotributaria;
	}


	public void setSituacaotributaria(String situacaotributaria) {
		this.situacaotributaria = situacaotributaria;
	}


	public String getNomesituacaotributaria() {
		return nomesituacaotributaria;
	}


	public void setNomesituacaotributaria(String nomesituacaotributaria) {
		this.nomesituacaotributaria = nomesituacaotributaria;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idsituacaotributaria == null) ? 0 : idsituacaotributaria.hashCode());
		result = prime * result + ((nomesituacaotributaria == null) ? 0 : nomesituacaotributaria.hashCode());
		result = prime * result + ((situacaotributaria == null) ? 0 : situacaotributaria.hashCode());
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
		SituacaoTributaria other = (SituacaoTributaria) obj;
		if (idsituacaotributaria == null) {
			if (other.idsituacaotributaria != null)
				return false;
		} else if (!idsituacaotributaria.equals(other.idsituacaotributaria))
			return false;
		if (nomesituacaotributaria == null) {
			if (other.nomesituacaotributaria != null)
				return false;
		} else if (!nomesituacaotributaria.equals(other.nomesituacaotributaria))
			return false;
		if (situacaotributaria == null) {
			if (other.situacaotributaria != null)
				return false;
		} else if (!situacaotributaria.equals(other.situacaotributaria))
			return false;
		return true;
	}

	
   
}
