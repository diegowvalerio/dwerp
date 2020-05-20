package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="tbregraimposto")
public class RegraImposto implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Expose
	private Integer idregraimposto;
	@Column(nullable=false,columnDefinition="varchar(100)")
	@Expose
	private String nome;
	
	@Expose
	@OneToMany(mappedBy="regraimposto", cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE },orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
    private List<RegraImpostoItem> regraimpostoitem = new ArrayList<>();

	public RegraImposto() {
		super();
	}

	public Integer getIdregraimposto() {
		return idregraimposto;
	}

	public void setIdregraimposto(Integer idregraimposto) {
		this.idregraimposto = idregraimposto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public List<RegraImpostoItem> getRegraimpostoitem() {
		return regraimpostoitem;
	}

	public void setRegraimpostoitem(List<RegraImpostoItem> regraimpostoitem) {
		this.regraimpostoitem = regraimpostoitem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idregraimposto == null) ? 0 : idregraimposto.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		RegraImposto other = (RegraImposto) obj;
		if (idregraimposto == null) {
			if (other.idregraimposto != null)
				return false;
		} else if (!idregraimposto.equals(other.idregraimposto))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}   
	
	
   
}
