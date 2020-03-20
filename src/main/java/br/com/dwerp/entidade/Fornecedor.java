package br.com.dwerp.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="tbfornecedor")
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer idfornecedor;
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

	
	public Fornecedor() {
		super();
	}  

	public String getNome() {
		return nome;
	}

	public Integer getIdfornecedor() {
		return idfornecedor;
	}

	public void setIdfornecedor(Integer idfornecedor) {
		this.idfornecedor = idfornecedor;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idfornecedor == null) ? 0 : idfornecedor.hashCode());
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
		Fornecedor other = (Fornecedor) obj;
		if (idfornecedor == null) {
			if (other.idfornecedor != null)
				return false;
		} else if (!idfornecedor.equals(other.idfornecedor))
			return false;
		return true;
	}

}
