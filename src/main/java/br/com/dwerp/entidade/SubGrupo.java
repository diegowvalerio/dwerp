package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

@Entity
@Table(name="tbsubgrupo")
public class SubGrupo implements Serializable {
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer idsubgrupo;
	@Column(nullable=false,columnDefinition="varchar(100)")
	private String nome;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	private Boolean situacao;
	
	@ManyToOne
	@JoinColumn(name = "idgrupo" , referencedColumnName="idgrupo" )
	private Grupo grupo;
	
	private static final long serialVersionUID = 1L;

	public SubGrupo() {
		super();
	}   

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public Boolean getSituacao() {
		return this.situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	public Integer getIdsubgrupo() {
		return idsubgrupo;
	}

	public void setIdsubgrupo(Integer idsubgrupo) {
		this.idsubgrupo = idsubgrupo;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

   
}
