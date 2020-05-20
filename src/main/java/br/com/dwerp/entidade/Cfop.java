package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="tbcfop")
public class Cfop implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Expose
	private Integer idcfop;
	@Column(nullable=false,columnDefinition="varchar(7)")
	@Expose
	private String tipocfop;
	@Column(nullable=false,columnDefinition="varchar(100)")
	@Expose
	private String descricao;
	@Column(nullable=false,columnDefinition="varchar(4)")
	@Expose
	private String cfop;
	@Column(nullable=false,columnDefinition="varchar(20)")
	@Expose
	private String tipooperacaocfop;
	
	@Expose
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean calcula_icms;
	@Expose
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean calcula_icmsst;
	@Expose
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean calcula_ipi;
	@Expose
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean calcula_irpj;
	@Expose
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean calcula_cssl;
	@Expose
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean calcula_estoque;
	@Expose
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean gerafinanceiro;
	@ManyToOne
	@Expose
	private SituacaoTributariaIPI situacaoTributariaIPI;
	@ManyToOne
	@Expose
	private SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS;


	public Cfop() {
		super();
	}


	public Integer getIdcfop() {
		return idcfop;
	}
	
	public Boolean getCalcula_estoque() {
		return calcula_estoque;
	}


	public void setCalcula_estoque(Boolean calcula_estoque) {
		this.calcula_estoque = calcula_estoque;
	}


	public Boolean getGerafinanceiro() {
		return gerafinanceiro;
	}


	public void setGerafinanceiro(Boolean gerafinanceiro) {
		this.gerafinanceiro = gerafinanceiro;
	}


	public void setIdcfop(Integer idcfop) {
		this.idcfop = idcfop;
	}

	public String getTipocfop() {
		return tipocfop;
	}


	public void setTipocfop(String tipocfop) {
		this.tipocfop = tipocfop;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getCfop() {
		return cfop;
	}


	public void setCfop(String cfop) {
		this.cfop = cfop;
	}


	public String getTipooperacaocfop() {
		return tipooperacaocfop;
	}


	public void setTipooperacaocfop(String tipooperacaocfop) {
		this.tipooperacaocfop = tipooperacaocfop;
	}


	public Boolean getCalcula_icms() {
		return calcula_icms;
	}


	public void setCalcula_icms(Boolean calcula_icms) {
		this.calcula_icms = calcula_icms;
	}


	public Boolean getCalcula_icmsst() {
		return calcula_icmsst;
	}


	public void setCalcula_icmsst(Boolean calcula_icmsst) {
		this.calcula_icmsst = calcula_icmsst;
	}


	public Boolean getCalcula_ipi() {
		return calcula_ipi;
	}


	public void setCalcula_ipi(Boolean calcula_ipi) {
		this.calcula_ipi = calcula_ipi;
	}


	public Boolean getCalcula_irpj() {
		return calcula_irpj;
	}


	public void setCalcula_irpj(Boolean calcula_irpj) {
		this.calcula_irpj = calcula_irpj;
	}


	public Boolean getCalcula_cssl() {
		return calcula_cssl;
	}


	public void setCalcula_cssl(Boolean calcula_cssl) {
		this.calcula_cssl = calcula_cssl;
	}


	public SituacaoTributariaIPI getSituacaoTributariaIPI() {
		return situacaoTributariaIPI;
	}


	public void setSituacaoTributariaIPI(SituacaoTributariaIPI situacaoTributariaIPI) {
		this.situacaoTributariaIPI = situacaoTributariaIPI;
	}


	public SituacaoTributariaPISCOFINS getSituacaoTributariaPISCOFINS() {
		return situacaoTributariaPISCOFINS;
	}


	public void setSituacaoTributariaPISCOFINS(SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS) {
		this.situacaoTributariaPISCOFINS = situacaoTributariaPISCOFINS;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idcfop == null) ? 0 : idcfop.hashCode());
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
		Cfop other = (Cfop) obj;
		if (idcfop == null) {
			if (other.idcfop != null)
				return false;
		} else if (!idcfop.equals(other.idcfop))
			return false;
		return true;
	}

	
   
}
