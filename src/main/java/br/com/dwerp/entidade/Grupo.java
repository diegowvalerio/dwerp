package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="tbgrupo")
public class Grupo implements Serializable {
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer idgrupo;
	@Column(nullable=false,columnDefinition="varchar(100)")
	private String nome;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	private Boolean situacao;
	
	@OneToMany(mappedBy="grupo", cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE },orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
    private List<SubGrupo> subgrupos = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;

	public Grupo() {
		super();
	}   
  
	public Integer getIdgrupo() {
		return idgrupo;
	}

	public void setIdgrupo(Integer idgrupo) {
		this.idgrupo = idgrupo;
	}
	

	public List<SubGrupo> getSubgrupos() {
		return subgrupos;
	}

	public void setSubgrupos(List<SubGrupo> subgrupos) {
		this.subgrupos = subgrupos;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idgrupo == null) ? 0 : idgrupo.hashCode());
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
		Grupo other = (Grupo) obj;
		if (idgrupo == null) {
			if (other.idgrupo != null)
				return false;
		} else if (!idgrupo.equals(other.idgrupo))
			return false;
		return true;
	}
	
   
}
