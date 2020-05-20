package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="tbsituacaotributariaipi")
public class SituacaoTributariaIPI implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Expose
	private Integer idsituacaotributariaipi;
	
	@Column(nullable=false,columnDefinition="varchar(3)")
	@Expose
	private String situacaotributariaipi;
	
	@Column(nullable=false,columnDefinition="varchar(100)")
	@Expose
	private String nomesituacaotributariaipi;


	public SituacaoTributariaIPI() {
		super();
	}


	public Integer getIdsituacaotributariaipi() {
		return idsituacaotributariaipi;
	}


	public void setIdsituacaotributariaipi(Integer idsituacaotributariaipi) {
		this.idsituacaotributariaipi = idsituacaotributariaipi;
	}


	public String getSituacaotributariaipi() {
		return situacaotributariaipi;
	}


	public void setSituacaotributariaipi(String situacaotributariaipi) {
		this.situacaotributariaipi = situacaotributariaipi;
	}


	public String getNomesituacaotributariaipi() {
		return nomesituacaotributariaipi;
	}


	public void setNomesituacaotributariaipi(String nomesituacaotributariaipi) {
		this.nomesituacaotributariaipi = nomesituacaotributariaipi;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idsituacaotributariaipi == null) ? 0 : idsituacaotributariaipi.hashCode());
		result = prime * result + ((nomesituacaotributariaipi == null) ? 0 : nomesituacaotributariaipi.hashCode());
		result = prime * result + ((situacaotributariaipi == null) ? 0 : situacaotributariaipi.hashCode());
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
		SituacaoTributariaIPI other = (SituacaoTributariaIPI) obj;
		if (idsituacaotributariaipi == null) {
			if (other.idsituacaotributariaipi != null)
				return false;
		} else if (!idsituacaotributariaipi.equals(other.idsituacaotributariaipi))
			return false;
		if (nomesituacaotributariaipi == null) {
			if (other.nomesituacaotributariaipi != null)
				return false;
		} else if (!nomesituacaotributariaipi.equals(other.nomesituacaotributariaipi))
			return false;
		if (situacaotributariaipi == null) {
			if (other.situacaotributariaipi != null)
				return false;
		} else if (!situacaotributariaipi.equals(other.situacaotributariaipi))
			return false;
		return true;
	}

	
   
}
