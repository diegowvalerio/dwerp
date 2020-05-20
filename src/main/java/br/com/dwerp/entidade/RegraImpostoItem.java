package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="tbregraimpostoitem")
public class RegraImpostoItem implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Expose
	private Integer idregraimpostoitem;
	
	@ManyToOne
	@JoinColumn(name = "idregraimposto", referencedColumnName="idregraimposto",insertable =false,updatable=false)
	private RegraImposto regraimposto;
	
	@Expose
	@ManyToOne
	private Estado estado;
	
	@Column(nullable=false, columnDefinition="numeric(6,2)")
	@Expose
	private double mvanormal;
	
	@Column(nullable=false, columnDefinition="numeric(6,2)")
	@Expose
	private double mvasimples;
	
	@Column(nullable=false, columnDefinition="numeric(6,2)")
	@Expose
	private double icms;
	
	@Column(nullable=false, columnDefinition="numeric(6,2)")
	@Expose
	private double icmsst;
	
	@Expose
	@ManyToOne
	private SituacaoTributaria situacaotributaria ;

	public RegraImpostoItem() {
		super();
	}

	public Integer getIdregraimpostoitem() {
		return idregraimpostoitem;
	}

	public void setIdregraimpostoitem(Integer idregraimpostoitem) {
		this.idregraimpostoitem = idregraimpostoitem;
	}

	public RegraImposto getRegraimposto() {
		return regraimposto;
	}

	public void setRegraimposto(RegraImposto regraimposto) {
		this.regraimposto = regraimposto;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public double getMvanormal() {
		return mvanormal;
	}

	public void setMvanormal(double mvanormal) {
		this.mvanormal = mvanormal;
	}

	public double getMvasimples() {
		return mvasimples;
	}

	public void setMvasimples(double mvasimples) {
		this.mvasimples = mvasimples;
	}

	public double getIcms() {
		return icms;
	}

	public void setIcms(double icms) {
		this.icms = icms;
	}

	public double getIcmsst() {
		return icmsst;
	}

	public void setIcmsst(double icmsst) {
		this.icmsst = icmsst;
	}

	public SituacaoTributaria getSituacaotributaria() {
		return situacaotributaria;
	}

	public void setSituacaotributaria(SituacaoTributaria situacaotributaria) {
		this.situacaotributaria = situacaotributaria;
	}
	
	   
}
