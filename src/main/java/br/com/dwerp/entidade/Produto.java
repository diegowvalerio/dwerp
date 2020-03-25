package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

@Entity
@Table(name="tbproduto")
public class Produto implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproduto;
	@Column(nullable=false,columnDefinition="varchar(300)")
	private String descricao;
	@Column(nullable=false,columnDefinition="varchar(100)")
	private String descabreviada;
	@Column(nullable=true,columnDefinition="varchar(13)")
	private String ean;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	private Boolean situacao;
	@ManyToOne
	private SubGrupo subgrupo;
	
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double valor_custo;
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double valor_venda;
	@Column(nullable=true, columnDefinition="bytea")
	private byte[] imagem;
	
	//exemplo de como salvar e buscar imagem
	//http://pgdocptbr.sourceforge.net/pg74/jdbc-binary-data.html
	
	private static final long serialVersionUID = 1L;

	public Produto() {
		super();
	}   
	public Integer getIdproduto() {
		return this.idproduto;
	}

	public void setIdproduto(Integer idproduto) {
		this.idproduto = idproduto;
	}   
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}   
	public String getDescabreviada() {
		return this.descabreviada;
	}

	public void setDescabreviada(String descabreviada) {
		this.descabreviada = descabreviada;
	}   
	public String getEan() {
		return this.ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}   
	public Boolean getSituacao() {
		return this.situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	public double getValor_custo() {
		return valor_custo;
	}
	public void setValor_custo(double valor_custo) {
		this.valor_custo = valor_custo;
	}
	public double getValor_venda() {
		return valor_venda;
	}
	public void setValor_venda(double valor_venda) {
		this.valor_venda = valor_venda;
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	
	public SubGrupo getSubgrupo() {
		return subgrupo;
	}
	public void setSubgrupo(SubGrupo subgrupo) {
		this.subgrupo = subgrupo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idproduto == null) ? 0 : idproduto.hashCode());
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
		Produto other = (Produto) obj;
		if (idproduto == null) {
			if (other.idproduto != null)
				return false;
		} else if (!idproduto.equals(other.idproduto))
			return false;
		return true;
	}
   
}
