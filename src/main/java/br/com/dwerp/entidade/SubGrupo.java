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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idsubgrupo == null) ? 0 : idsubgrupo.hashCode());
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
		SubGrupo other = (SubGrupo) obj;
		if (idsubgrupo == null) {
			if (other.idsubgrupo != null)
				return false;
		} else if (!idsubgrupo.equals(other.idsubgrupo))
			return false;
		return true;
	}

   
}
