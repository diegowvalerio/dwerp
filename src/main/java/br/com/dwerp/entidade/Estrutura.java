package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

@Entity
@Table(name="tbestrutura")
public class Estrutura implements Serializable {
	   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer idestrutura;
	
	@ManyToOne
	@JoinColumn(name = "idproduto" , referencedColumnName="idproduto" )
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "idproduto_estrutura" , referencedColumnName="idproduto" )
	private Produto produto_estrutura;
	
	private float qtde_estrutura;
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double valor_custo_estrutura;
	
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double total_custo_estrutura;
	
	
	private static final long serialVersionUID = 1L;
	public Estrutura() {
		super();
	}
	public Integer getIdestrutura() {
		return idestrutura;
	}
	public void setIdestrutura(Integer idestrutura) {
		this.idestrutura = idestrutura;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Produto getProduto_estrutura() {
		return produto_estrutura;
	}
	public void setProduto_estrutura(Produto produto_estrutura) {
		this.produto_estrutura = produto_estrutura;
	}
	public float getQtde_estrutura() {
		return qtde_estrutura;
	}
	public void setQtde_estrutura(float qtde_estrutura) {
		this.qtde_estrutura = qtde_estrutura;
	}
	
	public double getValor_custo_estrutura() {
		return valor_custo_estrutura;
	}
	public void setValor_custo_estrutura(double valor_custo_estrutura) {
		this.valor_custo_estrutura = valor_custo_estrutura;
	}
	
	public double getTotal_custo_estrutura() {
		return total_custo_estrutura;
	}
	public void setTotal_custo_estrutura(double total_custo_estrutura) {
		this.total_custo_estrutura = total_custo_estrutura;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((produto_estrutura == null) ? 0 : produto_estrutura.hashCode());
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
		Estrutura other = (Estrutura) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (produto_estrutura == null) {
			if (other.produto_estrutura != null)
				return false;
		} else if (!produto_estrutura.equals(other.produto_estrutura))
			return false;
		return true;
	}
	
	
   
}
