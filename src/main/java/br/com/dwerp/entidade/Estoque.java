package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="tbestoque")
public class Estoque implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer idestoque;
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Almoxarifado almoxarifado;

	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double saldo;
	
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double saldo_minimo;
	
	@Column(nullable=false,columnDefinition="varchar(100)")
	private String localizacao;
	
	public Estoque() {
		super();
	}

	public Integer getIdestoque() {
		return idestoque;
	}

	public void setIdestoque(Integer idestoque) {
		this.idestoque = idestoque;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Almoxarifado getAlmoxarifado() {
		return almoxarifado;
	}

	public void setAlmoxarifado(Almoxarifado almoxarifado) {
		this.almoxarifado = almoxarifado;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getSaldo_minimo() {
		return saldo_minimo;
	}

	public void setSaldo_minimo(double saldo_minimo) {
		this.saldo_minimo = saldo_minimo;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((almoxarifado == null) ? 0 : almoxarifado.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
		Estoque other = (Estoque) obj;
		if (almoxarifado == null) {
			if (other.almoxarifado != null)
				return false;
		} else if (!almoxarifado.equals(other.almoxarifado))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	
	
	
}
