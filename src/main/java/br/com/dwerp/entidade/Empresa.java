package br.com.dwerp.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tbempresa")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer idempresa;
	@Column(nullable=false,columnDefinition="varchar(250)")
	private String nome;
	@Column(nullable=true, columnDefinition="varchar(250)")
	private String razao_social;
	@Column(nullable=true, columnDefinition="varchar(18)")
	private String cnpj;
	@Column(nullable=true, columnDefinition="varchar(14)")
	private String insc_estadual;
	@Column(nullable=true, columnDefinition="varchar(10)")
	private String insc_suframa;
	
	@Column(nullable=false)
	private Integer regimetributavel;
	
	@Column(nullable=false) 
	@Temporal(TemporalType.DATE)
	private Date dtcadastro;
	
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
	@Column(nullable=false,columnDefinition="varchar(15)")
	private String numerocontato;
	@Column(nullable=false,columnDefinition="varchar(100)")
	private String email;
	
	//certificado
	@Column(nullable=true)
	private byte[] certificadobyte;
	@Column(nullable=true)
	private String senhacertificado;
	@Column(nullable=true) 
	@Temporal(TemporalType.DATE)
	private Date dtvencimentocertificado;
		
	public Empresa() {
		super();
	}

	public byte[] getCertificadobyte() {
		return certificadobyte;
	}

	public void setCertificadobyte(byte[] certificadobyte) {
		this.certificadobyte = certificadobyte;
	}

	public String getInsc_suframa() {
		return insc_suframa;
	}

	public void setInsc_suframa(String insc_suframa) {
		this.insc_suframa = insc_suframa;
	}

	public Integer getRegimetributavel() {
		return regimetributavel;
	}

	public void setRegimetributavel(Integer regimetributavel) {
		this.regimetributavel = regimetributavel;
	}

	public String getNumerocontato() {
		return numerocontato;
	}

	public void setNumerocontato(String numerocontato) {
		this.numerocontato = numerocontato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDtvencimentocertificado() {
		return dtvencimentocertificado;
	}

	public void setDtvencimentocertificado(Date dtvencimentocertificado) {
		this.dtvencimentocertificado = dtvencimentocertificado;
	}

	public String getSenhacertificado() {
		return senhacertificado;
	}

	public void setSenhacertificado(String senhacertificado) {
		this.senhacertificado = senhacertificado;
	}

	public Integer getIdempresa() {
		return idempresa;
	}

	public void setIdempresa(Integer idempresa) {
		this.idempresa = idempresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
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

	public Date getDtcadastro() {
		return dtcadastro;
	}

	public void setDtcadastro(Date dtcadastro) {
		this.dtcadastro = dtcadastro;
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
   
}
