package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tbhoraextra")
public class HoraExtra implements Serializable {
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer idhoraextra;
	
	@ManyToOne
	private Fechamento fechamento;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date qtdehora;
	
	@Column(columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean tipo_50;
	
	@Column(columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean tipo_60;
	
	@Column(columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean tipo_100;
	
	@Column(columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean insalubre;
	
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double valor_unitario;
	
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double valor_total;

	@Column(columnDefinition = "varchar(250)")
	private String observacao;
	
	
	private static final long serialVersionUID = 1L;
	public HoraExtra() {
		super();
	}
	public Integer getIdhoraextra() {
		return idhoraextra;
	}
	public void setIdhoraextra(Integer idhoraextra) {
		this.idhoraextra = idhoraextra;
	}
	public Fechamento getFechamento() {
		return fechamento;
	}
	public void setFechamento(Fechamento fechamento) {
		this.fechamento = fechamento;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getQtdehora() {
		return qtdehora;
	}
	public void setQtdehora(Date qtdehora) {
		this.qtdehora = qtdehora;
	}
	public Boolean getTipo_50() {
		return tipo_50;
	}
	public void setTipo_50(Boolean tipo_50) {
		this.tipo_50 = tipo_50;
	}
	public Boolean getTipo_60() {
		return tipo_60;
	}
	public void setTipo_60(Boolean tipo_60) {
		this.tipo_60 = tipo_60;
	}
	public Boolean getTipo_100() {
		return tipo_100;
	}
	public void setTipo_100(Boolean tipo_100) {
		this.tipo_100 = tipo_100;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public double getValor_unitario() {
		return valor_unitario;
	}
	public void setValor_unitario(double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}
	public double getValor_total() {
		return valor_total;
	}
	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}
	public Boolean getInsalubre() {
		return insalubre;
	}
	public void setInsalubre(Boolean insalubre) {
		this.insalubre = insalubre;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((fechamento == null) ? 0 : fechamento.hashCode());
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
		HoraExtra other = (HoraExtra) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (fechamento == null) {
			if (other.fechamento != null)
				return false;
		} else if (!fechamento.equals(other.fechamento))
			return false;
		return true;
	}	
   
}
