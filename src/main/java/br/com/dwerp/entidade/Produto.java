package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="tbproduto")
public class Produto implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproduto;
	@Column(nullable=false,columnDefinition="varchar(120)")
	private String descricao;
	@Column(nullable=false,columnDefinition="varchar(50)")
	private String descabreviada;
	@Column(nullable=false) 
	@Temporal(TemporalType.DATE)
	private Date dtcadastro;
	@Column(nullable=true,columnDefinition="varchar(13)")
	private String ean;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	private Boolean situacao;
	@ManyToOne
	private SubGrupo subgrupo;
	@ManyToOne
	private Cest cest;
	@ManyToOne
	private Ncm ncm;
	@ManyToOne
	private Unidade unidade;
	//acabado
	//materia-prima
	//componente
	@Column(nullable=true)
	private String tipoproduto;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	private Boolean atualiza_custo_automatico;	
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double valor_custo;
	@Column(nullable=true, columnDefinition="numeric(6,2)")
	private double valor_venda;
	
	@Column(nullable=true, columnDefinition="bytea")
	private byte[] imagem;
	
	@OneToMany(mappedBy="produto", cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE },orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
    private List<Estrutura> estruturas = new ArrayList<>();
	
	@OneToMany(mappedBy="produto", cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE },orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
    private List<Estoque> estoques = new ArrayList<>();
	
	@Column(nullable=false,columnDefinition="varchar(1)")
	private String origem_produto;	
	
	private static final long serialVersionUID = 1L;

	public Produto() {
		super();
	}   
	public Integer getIdproduto() {
		return this.idproduto;
	}

	public Boolean getAtualiza_custo_automatico() {
		return atualiza_custo_automatico;
	}
	public void setAtualiza_custo_automatico(Boolean atualiza_custo_automatico) {
		this.atualiza_custo_automatico = atualiza_custo_automatico;
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

	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
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
	
	public Date getDtcadastro() {
		return dtcadastro;
	}
	public void setDtcadastro(Date dtcadastro) {
		this.dtcadastro = dtcadastro;
	}
	
	
	public String getTipoproduto() {
		return tipoproduto;
	}
	public void setTipoproduto(String tipoproduto) {
		this.tipoproduto = tipoproduto;
	}
	
	public List<Estrutura> getEstruturas() {
		return estruturas;
	}
	public void setEstruturas(List<Estrutura> estruturas) {
		this.estruturas = estruturas;
	}
	
	public Cest getCest() {
		return cest;
	}
	public void setCest(Cest cest) {
		this.cest = cest;
	}
	public Ncm getNcm() {
		return ncm;
	}
	public void setNcm(Ncm ncm) {
		this.ncm = ncm;
	}
	public List<Estoque> getEstoques() {
		return estoques;
	}
	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}
	
	public String getOrigem_produto() {
		return origem_produto;
	}
	public void setOrigem_produto(String origem_produto) {
		this.origem_produto = origem_produto;
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
