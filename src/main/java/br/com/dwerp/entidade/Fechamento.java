package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "tbfechamento")
public class Fechamento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idfechamento;

	@ManyToOne
	private CadastroGeral funcionario;

	@Column(nullable = false, columnDefinition = "varchar(2)")
	private String mes;

	@Column(nullable = false, columnDefinition = "varchar(4)")
	private String ano;

	@Column(nullable = false, columnDefinition = "varchar(250)")
	private String observacao;

	// faltas
	@Column(nullable = false)
	private float falta_qtde_dia;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date falta_qtde_hora;

	@Column(nullable = false, columnDefinition = "varchar(250)")
	private String falta_observacao;
	//

	// diarias
	@Column(nullable = false)
	private float diaria_qtde_dia;

	@Column(nullable = true, columnDefinition = "numeric(6,2)")
	private double diaria_valor_unitario;

	@Column(nullable = true, columnDefinition = "numeric(6,2)")
	private double diaria_valor_total;

	@Column(nullable = false, columnDefinition = "varchar(250)")
	private String diaria_observacao;
	//

	// horas extras 50%
	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date horaextra_50_qtde_hora;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date horaextra_50_qtde_minuto;

	@Column(nullable = false, columnDefinition = "varchar(250)")
	private String horaextra_50_observacao;
	//

	// horas extras 60%
	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date horaextra_60_qtde_hora;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date horaextra_60_qtde_minuto;

	@Column(nullable = false, columnDefinition = "varchar(250)")
	private String horaextra_60_observacao;
	//

	// horas extras 100%
	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date horaextra_100_qtde_hora;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date horaextra_100_qtde_minuto;

	@Column(nullable = false, columnDefinition = "varchar(250)")
	private String horaextra_100_observacao;
	//

	// horas extras 50% formal
	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date horaextraformal_50_qtde_hora;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date horaextraformal_50_qtde_minuto;

	@Column(nullable = false, columnDefinition = "varchar(250)")
	private String horaextraformal_50_observacao;
	//

	// horas extras 60% formal
	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date horaextraformal_60_qtde_hora;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date horaextraformal_60_qtde_minuto;

	@Column(nullable = false, columnDefinition = "varchar(250)")
	private String horaextraformal_60_observacao;
	//

	// horas extras 100% formal
	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date horaextraformal_100_qtde_hora;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date horaextraformal_100_qtde_minuto;

	@Column(nullable = false, columnDefinition = "varchar(250)")
	private String horaextraformal_100_observacao;
	//

	private static final long serialVersionUID = 1L;

	public Fechamento() {
		super();
	}

	public Integer getIdfechamento() {
		return idfechamento;
	}

	public void setIdfechamento(Integer idfechamento) {
		this.idfechamento = idfechamento;
	}

	public CadastroGeral getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(CadastroGeral funcionario) {
		this.funcionario = funcionario;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idfechamento == null) ? 0 : idfechamento.hashCode());
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
		Fechamento other = (Fechamento) obj;
		if (idfechamento == null) {
			if (other.idfechamento != null)
				return false;
		} else if (!idfechamento.equals(other.idfechamento))
			return false;
		return true;
	}

}
