package br.com.dwerp.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="tbcadastrogeral")
public class CadastroGeral implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer idcadastrogeral;
	@Column(nullable=false,columnDefinition="varchar(250)")
	private String nome;
	@Column(nullable=false) 
	@Temporal(TemporalType.DATE)
	private Date dtcadastro;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	private Boolean situacao;
	@Column(nullable=true, columnDefinition="varchar(250)")
	private String observacao;
	@Column(nullable=false)
	private String tipojf;
	
	//tipos de cadastro
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean op_cliente;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean op_fornecedor;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean op_transportadora;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean op_funcionario;
	
	//fiscal
	@Column(nullable=true, columnDefinition="varchar(10)")
	private String insc_suframa;
	@Column(nullable=true, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean desc_icms_suframa;
	@Column(nullable=true, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean desc_cofins_suframa;
	@Column(nullable=true, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean desc_pis_suframa;
	@Column(nullable=true, columnDefinition= "BOOLEAN DEFAULT FALSE")
	private Boolean desc_ipi_suframa;
	@Column(nullable=true, columnDefinition="char(1)")
	private String tipo_tributacao;
	
	
	//pessoa fisica
	@Column(nullable=true, columnDefinition="varchar(14)", unique=true)
	private String cpf;
	@Column(nullable=true, columnDefinition="varchar(14)")
	private String rg;
	
	//pessoa juridica
	@Column(nullable=true, columnDefinition="varchar(18)")
	private String cnpj;
	@Column(nullable=true, columnDefinition="varchar(14)")
	private String insc_estadual;
	@Column(nullable=true, columnDefinition="varchar(250)")
	private String razao_social;
	
	//endereco
	@Column(nullable=false,columnDefinition="varchar(100)")
	private String logadouro;
	@Column(nullable=false,columnDefinition="varchar(10)")
	private String numero;
	@Column(nullable=false,columnDefinition="varchar(10)")
	private String cep;
	@Column(nullable=false,columnDefinition="varchar(40)")
	private String bairro;
	@Column(nullable=true,columnDefinition="varchar(150)")
	private String complemento;
	@ManyToOne
	private Cidade cidade;	
	
	//contato
	@Column(nullable=false,columnDefinition="varchar(150)")
	private String nomecontato;
	@Column(nullable=false,columnDefinition="varchar(100)")
	private String email;
	@Column(nullable=false,columnDefinition="varchar(150)")
	private String observacaocontato;
	@Column(nullable=false,columnDefinition="varchar(15)")
	private String numerocontato;
	
	//dados do funcionario
	
	@ManyToOne
	private Empresa empresa;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=true)
	private Date dtadmissao;
	
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double salario;
	
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double insalubridade;
	
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double adicionalnoturno;
	
	@Column(nullable=true)
	private int cargahoraria_mensal;
	
	@ManyToOne
	private Funcao funcao;	
	
	@ManyToOne
	private Setor setor;
	
	@Column(nullable=true,columnDefinition="varchar(14)")
	private String pispasep;
	
	@Column(nullable=true,columnDefinition="varchar(14)") // MASCULINO - FEMININO
	private String sexo;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=true)
	private Date dtnascimento;
	
	@ManyToOne
	private Cidade naturalidade;
	
	@Column(nullable=true,columnDefinition="varchar(150)")
	private String escolaridade;
	
	@Column(nullable=true,columnDefinition="varchar(10)")
	private String rg_orgaoemissor;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=true)
	private Date rg_dtemissao;
	
	@Column(nullable=true,columnDefinition="varchar(12)")
	private String carteiratrabalho_numero;
	
	@Column(nullable=true,columnDefinition="varchar(12)")
	private String carteiratrabalho_serie;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=true)
	private Date carteiratrabalho_dtemissao;
	
	@ManyToOne
	private Estado carteiratrabalho_uf;
	
	@Column(nullable=true,columnDefinition="varchar(10)")
	private String tiporemige;
	
	//gratificações fixas -valor total
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double adicionalfixo;

	
	public CadastroGeral() {
		super();
	}  

	public String getNome() {
		return nome;
	}

	public Integer getIdcadastrogeral() {
		return idcadastrogeral;
	}

	public void setIdcadastrogeral(Integer idcadastrogeral) {
		this.idcadastrogeral = idcadastrogeral;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getDtcadastro() {
		return dtcadastro;
	}


	public void setDtcadastro(Date dtcadastro) {
		this.dtcadastro = dtcadastro;
	}

	public Boolean getSituacao() {
		return situacao;
	}


	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}


	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getTipojf() {
		return tipojf;
	}


	public void setTipojf(String tipojf) {
		this.tipojf = tipojf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInsc_estadual() {
		return insc_estadual;
	}

	public void setInsc_estadual(String insc_estadual) {
		this.insc_estadual = insc_estadual;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public String getLogadouro() {
		return logadouro;
	}

	public void setLogadouro(String logadouro) {
		this.logadouro = logadouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getNomecontato() {
		return nomecontato;
	}

	public void setNomecontato(String nomecontato) {
		this.nomecontato = nomecontato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObservacaocontato() {
		return observacaocontato;
	}

	public void setObservacaocontato(String observacaocontato) {
		this.observacaocontato = observacaocontato;
	}

	public String getNumerocontato() {
		return numerocontato;
	}

	public void setNumerocontato(String numerocontato) {
		this.numerocontato = numerocontato;
	}

	public Boolean getOp_cliente() {
		return op_cliente;
	}

	public void setOp_cliente(Boolean op_cliente) {
		this.op_cliente = op_cliente;
	}

	public Boolean getOp_fornecedor() {
		return op_fornecedor;
	}

	public void setOp_fornecedor(Boolean op_fornecedor) {
		this.op_fornecedor = op_fornecedor;
	}

	public Boolean getOp_transportadora() {
		return op_transportadora;
	}

	public void setOp_transportadora(Boolean op_transportadora) {
		this.op_transportadora = op_transportadora;
	}

	public Boolean getOp_funcionario() {
		return op_funcionario;
	}

	public void setOp_funcionario(Boolean op_funcionario) {
		this.op_funcionario = op_funcionario;
	}

	public String getInsc_suframa() {
		return insc_suframa;
	}

	public void setInsc_suframa(String insc_suframa) {
		this.insc_suframa = insc_suframa;
	}

	public Boolean getDesc_icms_suframa() {
		return desc_icms_suframa;
	}

	public void setDesc_icms_suframa(Boolean desc_icms_suframa) {
		this.desc_icms_suframa = desc_icms_suframa;
	}

	public Boolean getDesc_cofins_suframa() {
		return desc_cofins_suframa;
	}

	public void setDesc_cofins_suframa(Boolean desc_cofins_suframa) {
		this.desc_cofins_suframa = desc_cofins_suframa;
	}

	public Boolean getDesc_ipi_suframa() {
		return desc_ipi_suframa;
	}

	public void setDesc_ipi_suframa(Boolean desc_ipi_suframa) {
		this.desc_ipi_suframa = desc_ipi_suframa;
	}
	public String getTipo_tributacao() {
		return tipo_tributacao;
	}

	public void setTipo_tributacao(String tipo_tributacao) {
		this.tipo_tributacao = tipo_tributacao;
	}

	public Boolean getDesc_pis_suframa() {
		return desc_pis_suframa;
	}

	public void setDesc_pis_suframa(Boolean desc_pis_suframa) {
		this.desc_pis_suframa = desc_pis_suframa;
	}
	

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getDtadmissao() {
		return dtadmissao;
	}

	public void setDtadmissao(Date dtadmissao) {
		this.dtadmissao = dtadmissao;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getPispasep() {
		return pispasep;
	}

	public void setPispasep(String pispasep) {
		this.pispasep = pispasep;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDtnascimento() {
		return dtnascimento;
	}

	public void setDtnascimento(Date dtnascimento) {
		this.dtnascimento = dtnascimento;
	}

	public Cidade getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(Cidade naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getRg_orgaoemissor() {
		return rg_orgaoemissor;
	}

	public void setRg_orgaoemissor(String rg_orgaoemissor) {
		this.rg_orgaoemissor = rg_orgaoemissor;
	}

	public Date getRg_dtemissao() {
		return rg_dtemissao;
	}

	public void setRg_dtemissao(Date rg_dtemissao) {
		this.rg_dtemissao = rg_dtemissao;
	}

	public double getInsalubridade() {
		return insalubridade;
	}

	public void setInsalubridade(double insalubridade) {
		this.insalubridade = insalubridade;
	}

	public double getAdicionalnoturno() {
		return adicionalnoturno;
	}

	public void setAdicionalnoturno(double adicionalnoturno) {
		this.adicionalnoturno = adicionalnoturno;
	}

	public int getCargahoraria_mensal() {
		return cargahoraria_mensal;
	}

	public void setCargahoraria_mensal(int cargahoraria_mensal) {
		this.cargahoraria_mensal = cargahoraria_mensal;
	}

	public String getCarteiratrabalho_numero() {
		return carteiratrabalho_numero;
	}

	public void setCarteiratrabalho_numero(String carteiratrabalho_numero) {
		this.carteiratrabalho_numero = carteiratrabalho_numero;
	}

	public String getCarteiratrabalho_serie() {
		return carteiratrabalho_serie;
	}

	public void setCarteiratrabalho_serie(String carteiratrabalho_serie) {
		this.carteiratrabalho_serie = carteiratrabalho_serie;
	}

	public Date getCarteiratrabalho_dtemissao() {
		return carteiratrabalho_dtemissao;
	}

	public void setCarteiratrabalho_dtemissao(Date carteiratrabalho_dtemissao) {
		this.carteiratrabalho_dtemissao = carteiratrabalho_dtemissao;
	}

	public Estado getCarteiratrabalho_uf() {
		return carteiratrabalho_uf;
	}

	public void setCarteiratrabalho_uf(Estado carteiratrabalho_uf) {
		this.carteiratrabalho_uf = carteiratrabalho_uf;
	}

	public String getTiporemige() {
		return tiporemige;
	}

	public void setTiporemige(String tiporemige) {
		this.tiporemige = tiporemige;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idcadastrogeral == null) ? 0 : idcadastrogeral.hashCode());
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
		CadastroGeral other = (CadastroGeral) obj;
		if (idcadastrogeral == null) {
			if (other.idcadastrogeral != null)
				return false;
		} else if (!idcadastrogeral.equals(other.idcadastrogeral))
			return false;
		return true;
	}

   
}
