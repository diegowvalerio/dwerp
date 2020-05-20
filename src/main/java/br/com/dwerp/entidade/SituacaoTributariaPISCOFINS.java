package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="tbsituacaotributariapiscofins")
public class SituacaoTributariaPISCOFINS implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Expose
	private Integer idsituacaotributariapiscofins;
	
	@Column(nullable=false,columnDefinition="varchar(3)")
	@Expose
	private String situacaotributariapiscofins;
	
	@Column(nullable=false,columnDefinition="varchar(100)")
	@Expose
	private String nomesituacaotributariapiscofins;


	public SituacaoTributariaPISCOFINS() {
		super();
	}


	public Integer getIdsituacaotributariapiscofins() {
		return idsituacaotributariapiscofins;
	}


	public void setIdsituacaotributariapiscofins(Integer idsituacaotributariapiscofins) {
		this.idsituacaotributariapiscofins = idsituacaotributariapiscofins;
	}


	public String getSituacaotributariapiscofins() {
		return situacaotributariapiscofins;
	}


	public void setSituacaotributariapiscofins(String situacaotributariapiscofins) {
		this.situacaotributariapiscofins = situacaotributariapiscofins;
	}


	public String getNomesituacaotributariapiscofins() {
		return nomesituacaotributariapiscofins;
	}


	public void setNomesituacaotributariapiscofins(String nomesituacaotributariapiscofins) {
		this.nomesituacaotributariapiscofins = nomesituacaotributariapiscofins;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idsituacaotributariapiscofins == null) ? 0 : idsituacaotributariapiscofins.hashCode());
		result = prime * result
				+ ((nomesituacaotributariapiscofins == null) ? 0 : nomesituacaotributariapiscofins.hashCode());
		result = prime * result + ((situacaotributariapiscofins == null) ? 0 : situacaotributariapiscofins.hashCode());
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
		SituacaoTributariaPISCOFINS other = (SituacaoTributariaPISCOFINS) obj;
		if (idsituacaotributariapiscofins == null) {
			if (other.idsituacaotributariapiscofins != null)
				return false;
		} else if (!idsituacaotributariapiscofins.equals(other.idsituacaotributariapiscofins))
			return false;
		if (nomesituacaotributariapiscofins == null) {
			if (other.nomesituacaotributariapiscofins != null)
				return false;
		} else if (!nomesituacaotributariapiscofins.equals(other.nomesituacaotributariapiscofins))
			return false;
		if (situacaotributariapiscofins == null) {
			if (other.situacaotributariapiscofins != null)
				return false;
		} else if (!situacaotributariapiscofins.equals(other.situacaotributariapiscofins))
			return false;
		return true;
	}

}
