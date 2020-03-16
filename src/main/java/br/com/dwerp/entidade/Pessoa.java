package br.com.dwerp.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="tbpessoa")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer idpessoa;
	@Column(nullable=false,columnDefinition="varchar(250)")
	private String nome;
	@Column(nullable=false) 
	@Temporal(TemporalType.DATE)
	private Date dtcadastro;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	private Boolean situacao;
	@Column(nullable=false)
	private String tipojf;
	
	//pessoa fisica
	@Column(nullable=false, columnDefinition="varchar(14)", unique=true)
	private String cpf;
	@Column(nullable=false, columnDefinition="varchar(14)")
	private String rg;
	
	//pessoa juridica
	@Column(nullable=false, columnDefinition="varchar(18)")
	private String cnpj;
	@Column(nullable=false, columnDefinition="varchar(14)")
	private String insc_estadual;
	@Column(nullable=false, columnDefinition="varchar(250)")
	private String razao_social;
	
	
	//usuario
	@Column(nullable=false, columnDefinition="varchar(10)")
	private String login;
	@Column(nullable=false, columnDefinition="varchar(10)")
	private String senha;
	
	public Pessoa() {
		super();
	}  
	
	 public Integer getIdpessoa() {
		return idpessoa;
	}


	public void setIdpessoa(Integer idpessoa) {
		this.idpessoa = idpessoa;
	}


	public String getNome() {
		return nome;
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


	public String getTipojf() {
		return tipojf;
	}


	public void setTipojf(String tipojf) {
		this.tipojf = tipojf;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idpessoa == null) ? 0 : idpessoa.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (idpessoa == null) {
			if (other.idpessoa != null)
				return false;
		} else if (!idpessoa.equals(other.idpessoa))
			return false;
		return true;
	}

   
}
