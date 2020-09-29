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

	@Column(columnDefinition = "varchar(250)")
	private String observacao;
	
	//salario do momento de fechamento
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double salario;
	
	//adicional fixo do momento de fechamento
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double adicionalfixo;
	
	//adicional noturno do momento de fechamento
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double adicionalnoturno;
	
	//insalubridade do momento de fechamento
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double insalubridade;
	
	//nome funcao do momento de fechamento
	@Column(nullable = false, columnDefinition = "varchar(250)")
	private String funcao;
	
	//nome setor do momento de fechamento
	@Column(nullable = false, columnDefinition = "varchar(250)")
	private String setor;

	// faltas
	@Column(nullable = false)
	private float falta_qtde_dia;

	@Temporal(TemporalType.TIMESTAMP)
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
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaextra_50_qtde_hora;

	@Temporal(TemporalType.TIMESTAMP)
	private Date horaextra_50_qtde_hora_insalubre;
	
	@Column(columnDefinition="numeric(6,2)")
	private double horaextra_50_valor_total_insalubre;
	
	@Column(columnDefinition="numeric(6,2)")
	private double horaextra_50_valor_total;

	@Column(columnDefinition = "varchar(250)")
	private String horaextra_50_observacao;
	//

	// horas extras 60%
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaextra_60_qtde_hora;

	@Temporal(TemporalType.TIMESTAMP)
	private Date horaextra_60_qtde_hora_insalubre;
	
	@Column(columnDefinition="numeric(6,2)")
	private double horaextra_60_valor_total_insalubre;
	
	@Column(columnDefinition="numeric(6,2)")
	private double horaextra_60_valor_total;

	@Column(columnDefinition = "varchar(250)")
	private String horaextra_60_observacao;
	//

	// horas extras 100%
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaextra_100_qtde_hora;

	@Temporal(TemporalType.TIMESTAMP)
	private Date horaextra_100_qtde_hora_insalubre;
	
	@Column(columnDefinition="numeric(6,2)")
	private double horaextra_100_valor_total_insalubre;
	
	@Column(columnDefinition="numeric(6,2)")
	private double horaextra_100_valor_total;

	@Column(columnDefinition = "varchar(250)")
	private String horaextra_100_observacao;
	//

	// horas extras 50% formal
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date horaextraformal_50_qtde_hora;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date horaextraformal_50_qtde_minuto;

	@Column(nullable = false, columnDefinition = "varchar(250)")
	private String horaextraformal_50_observacao;
	//

	// horas extras 60% formal
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date horaextraformal_60_qtde_hora;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date horaextraformal_60_qtde_minuto;

	@Column(nullable = false, columnDefinition = "varchar(250)")
	private String horaextraformal_60_observacao;
	//

	// horas extras 100% formal
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date horaextraformal_100_qtde_hora;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date horaextraformal_100_qtde_minuto;

	@Column(nullable = false, columnDefinition = "varchar(250)")
	private String horaextraformal_100_observacao;
	//
	
	@OneToMany(mappedBy="fechamento", cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE },orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
    private List<HoraExtra> horasextras = new ArrayList<>();
	
	

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
	

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public float getFalta_qtde_dia() {
		return falta_qtde_dia;
	}

	public void setFalta_qtde_dia(float falta_qtde_dia) {
		this.falta_qtde_dia = falta_qtde_dia;
	}

	public Date getFalta_qtde_hora() {
		return falta_qtde_hora;
	}

	public void setFalta_qtde_hora(Date falta_qtde_hora) {
		this.falta_qtde_hora = falta_qtde_hora;
	}

	public String getFalta_observacao() {
		return falta_observacao;
	}

	public void setFalta_observacao(String falta_observacao) {
		this.falta_observacao = falta_observacao;
	}

	public float getDiaria_qtde_dia() {
		return diaria_qtde_dia;
	}

	public void setDiaria_qtde_dia(float diaria_qtde_dia) {
		this.diaria_qtde_dia = diaria_qtde_dia;
	}

	public double getDiaria_valor_unitario() {
		return diaria_valor_unitario;
	}

	public void setDiaria_valor_unitario(double diaria_valor_unitario) {
		this.diaria_valor_unitario = diaria_valor_unitario;
	}

	public double getDiaria_valor_total() {
		return diaria_valor_total;
	}

	public void setDiaria_valor_total(double diaria_valor_total) {
		this.diaria_valor_total = diaria_valor_total;
	}

	public String getDiaria_observacao() {
		return diaria_observacao;
	}

	public void setDiaria_observacao(String diaria_observacao) {
		this.diaria_observacao = diaria_observacao;
	}

	public Date getHoraextra_50_qtde_hora() {
		return horaextra_50_qtde_hora;
	}

	public void setHoraextra_50_qtde_hora(Date horaextra_50_qtde_hora) {
		this.horaextra_50_qtde_hora = horaextra_50_qtde_hora;
	}

	public String getHoraextra_50_observacao() {
		return horaextra_50_observacao;
	}

	public void setHoraextra_50_observacao(String horaextra_50_observacao) {
		this.horaextra_50_observacao = horaextra_50_observacao;
	}

	public Date getHoraextra_60_qtde_hora() {
		return horaextra_60_qtde_hora;
	}

	public void setHoraextra_60_qtde_hora(Date horaextra_60_qtde_hora) {
		this.horaextra_60_qtde_hora = horaextra_60_qtde_hora;
	}

	public String getHoraextra_60_observacao() {
		return horaextra_60_observacao;
	}

	public void setHoraextra_60_observacao(String horaextra_60_observacao) {
		this.horaextra_60_observacao = horaextra_60_observacao;
	}

	public Date getHoraextra_100_qtde_hora() {
		return horaextra_100_qtde_hora;
	}

	public void setHoraextra_100_qtde_hora(Date horaextra_100_qtde_hora) {
		this.horaextra_100_qtde_hora = horaextra_100_qtde_hora;
	}

	public String getHoraextra_100_observacao() {
		return horaextra_100_observacao;
	}

	public void setHoraextra_100_observacao(String horaextra_100_observacao) {
		this.horaextra_100_observacao = horaextra_100_observacao;
	}

	public Date getHoraextraformal_50_qtde_hora() {
		return horaextraformal_50_qtde_hora;
	}

	public void setHoraextraformal_50_qtde_hora(Date horaextraformal_50_qtde_hora) {
		this.horaextraformal_50_qtde_hora = horaextraformal_50_qtde_hora;
	}

	public Date getHoraextraformal_50_qtde_minuto() {
		return horaextraformal_50_qtde_minuto;
	}

	public void setHoraextraformal_50_qtde_minuto(Date horaextraformal_50_qtde_minuto) {
		this.horaextraformal_50_qtde_minuto = horaextraformal_50_qtde_minuto;
	}

	public String getHoraextraformal_50_observacao() {
		return horaextraformal_50_observacao;
	}

	public void setHoraextraformal_50_observacao(String horaextraformal_50_observacao) {
		this.horaextraformal_50_observacao = horaextraformal_50_observacao;
	}

	public Date getHoraextraformal_60_qtde_hora() {
		return horaextraformal_60_qtde_hora;
	}

	public void setHoraextraformal_60_qtde_hora(Date horaextraformal_60_qtde_hora) {
		this.horaextraformal_60_qtde_hora = horaextraformal_60_qtde_hora;
	}

	public Date getHoraextraformal_60_qtde_minuto() {
		return horaextraformal_60_qtde_minuto;
	}

	public void setHoraextraformal_60_qtde_minuto(Date horaextraformal_60_qtde_minuto) {
		this.horaextraformal_60_qtde_minuto = horaextraformal_60_qtde_minuto;
	}

	public String getHoraextraformal_60_observacao() {
		return horaextraformal_60_observacao;
	}

	public void setHoraextraformal_60_observacao(String horaextraformal_60_observacao) {
		this.horaextraformal_60_observacao = horaextraformal_60_observacao;
	}

	public Date getHoraextraformal_100_qtde_hora() {
		return horaextraformal_100_qtde_hora;
	}

	public void setHoraextraformal_100_qtde_hora(Date horaextraformal_100_qtde_hora) {
		this.horaextraformal_100_qtde_hora = horaextraformal_100_qtde_hora;
	}

	public Date getHoraextraformal_100_qtde_minuto() {
		return horaextraformal_100_qtde_minuto;
	}

	public void setHoraextraformal_100_qtde_minuto(Date horaextraformal_100_qtde_minuto) {
		this.horaextraformal_100_qtde_minuto = horaextraformal_100_qtde_minuto;
	}

	public String getHoraextraformal_100_observacao() {
		return horaextraformal_100_observacao;
	}

	public void setHoraextraformal_100_observacao(String horaextraformal_100_observacao) {
		this.horaextraformal_100_observacao = horaextraformal_100_observacao;
	}

	public double getAdicionalfixo() {
		return adicionalfixo;
	}

	public void setAdicionalfixo(double adicionalfixo) {
		this.adicionalfixo = adicionalfixo;
	}

	public double getInsalubridade() {
		return insalubridade;
	}

	public void setInsalubridade(double insalubridade) {
		this.insalubridade = insalubridade;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public double getAdicionalnoturno() {
		return adicionalnoturno;
	}

	public void setAdicionalnoturno(double adicionalnoturno) {
		this.adicionalnoturno = adicionalnoturno;
	}

	public List<HoraExtra> getHorasextras() {
		return horasextras;
	}

	public void setHorasextras(List<HoraExtra> horasextras) {
		this.horasextras = horasextras;
	}
	public double getHoraextra_50_valor_total() {
		return horaextra_50_valor_total;
	}

	public void setHoraextra_50_valor_total(double horaextra_50_valor_total) {
		this.horaextra_50_valor_total = horaextra_50_valor_total;
	}

	public double getHoraextra_60_valor_total() {
		return horaextra_60_valor_total;
	}

	public void setHoraextra_60_valor_total(double horaextra_60_valor_total) {
		this.horaextra_60_valor_total = horaextra_60_valor_total;
	}

	public double getHoraextra_100_valor_total() {
		return horaextra_100_valor_total;
	}

	public void setHoraextra_100_valor_total(double horaextra_100_valor_total) {
		this.horaextra_100_valor_total = horaextra_100_valor_total;
	}

	public Date getHoraextra_50_qtde_hora_insalubre() {
		return horaextra_50_qtde_hora_insalubre;
	}

	public void setHoraextra_50_qtde_hora_insalubre(Date horaextra_50_qtde_hora_insalubre) {
		this.horaextra_50_qtde_hora_insalubre = horaextra_50_qtde_hora_insalubre;
	}

	public double getHoraextra_50_valor_total_insalubre() {
		return horaextra_50_valor_total_insalubre;
	}

	public void setHoraextra_50_valor_total_insalubre(double horaextra_50_valor_total_insalubre) {
		this.horaextra_50_valor_total_insalubre = horaextra_50_valor_total_insalubre;
	}

	public Date getHoraextra_60_qtde_hora_insalubre() {
		return horaextra_60_qtde_hora_insalubre;
	}

	public void setHoraextra_60_qtde_hora_insalubre(Date horaextra_60_qtde_hora_insalubre) {
		this.horaextra_60_qtde_hora_insalubre = horaextra_60_qtde_hora_insalubre;
	}

	public double getHoraextra_60_valor_total_insalubre() {
		return horaextra_60_valor_total_insalubre;
	}

	public void setHoraextra_60_valor_total_insalubre(double horaextra_60_valor_total_insalubre) {
		this.horaextra_60_valor_total_insalubre = horaextra_60_valor_total_insalubre;
	}

	public Date getHoraextra_100_qtde_hora_insalubre() {
		return horaextra_100_qtde_hora_insalubre;
	}

	public void setHoraextra_100_qtde_hora_insalubre(Date horaextra_100_qtde_hora_insalubre) {
		this.horaextra_100_qtde_hora_insalubre = horaextra_100_qtde_hora_insalubre;
	}

	public double getHoraextra_100_valor_total_insalubre() {
		return horaextra_100_valor_total_insalubre;
	}

	public void setHoraextra_100_valor_total_insalubre(double horaextra_100_valor_total_insalubre) {
		this.horaextra_100_valor_total_insalubre = horaextra_100_valor_total_insalubre;
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
