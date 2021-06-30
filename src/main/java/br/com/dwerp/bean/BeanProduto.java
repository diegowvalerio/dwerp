package br.com.dwerp.bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.dwerp.entidade.SubGrupo;
import br.com.dwerp.entidade.Unidade;
import br.com.dwerp.entidade.Almoxarifado;
import br.com.dwerp.entidade.Cest;
import br.com.dwerp.entidade.Cidade;
import br.com.dwerp.entidade.Estoque;
import br.com.dwerp.entidade.Estrutura;
import br.com.dwerp.entidade.Ncm;
import br.com.dwerp.entidade.Produto;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoSubGrupo;
import br.com.dwerp.servico.ServicoUnidade;
import br.com.dwerp.servico.ServicoAlmoxarifado;
import br.com.dwerp.servico.ServicoCest;
import br.com.dwerp.servico.ServicoNcm;
import br.com.dwerp.servico.ServicoProduto;

@Named
@ViewScoped
public class BeanProduto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Produto produto = new Produto();
	private Estrutura estrutura = new Estrutura();
	private Estoque estoque = new Estoque();
	
	@Inject
	private ServicoProduto servico;
	private List<Produto> lista;
	
	@Inject
	private ServicoSubGrupo servicoSubGrupo;
	private SubGrupo subGrupo = new SubGrupo();
	private List<SubGrupo> listasubgrupo;
	
	@Inject
	private ServicoCest servicoCest;
	private List<Cest> listacest;
	
	@Inject
	private ServicoNcm servicoNcm;
	private List<Ncm> listancm;
	
	@Inject
	private ServicoUnidade servicoUnidade;
	private List<Unidade> listaunidade;
	
	@Inject
	private ServicoAlmoxarifado servicoAlmoxarifado;
	private List<Almoxarifado> listaalmoxarifado;
	
	private List<Estrutura> estruturas;
	private List<Estoque> estoques;
	
	private String opcao;
	private Date data;
	private Boolean isRederiza = false;
	private Boolean isRederiza2 = false;
	
	public BeanProduto() {
		data = new Date();
	}
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		listasubgrupo = servicoSubGrupo.consultar_ativos();
		listacest = servicoCest.consultar();
		listancm = servicoNcm.consultar();
		listaunidade = servicoUnidade.consultar();	
		listaalmoxarifado = servicoAlmoxarifado.consultar_ativos();
		
		this.produto = this.getProduto();
		this.estruturas = this.produto.getEstruturas();
		this.estoques = this.produto.getEstoques();
		this.produto.setDtcadastro(data);
		
		/*Collections.sort(lista, new Comparator<Produto>() {
	        @Override
	        public int compare(Produto  p1, Produto  p2)
	        {

	            return  p1.getIdproduto().compareTo(p2.getIdproduto());
	        }
	    });*/
		
		Collections.sort(lista, Comparator.comparing(Produto::getIdproduto)); 
	}
	
	public String salvar(){
		if(produto.getIdproduto() == null){
			produto.setDtcadastro(data);
		}
		try{
		servico.salvar(produto);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-produto";
	}

	public String excluir() {
		try{
		servico.excluir(produto.getIdproduto());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();

		return "lista-produto";
	}
	
	public void addNovoEstrutura() {
		if (this.produto.getTipoproduto().equals("MATERIA-PRIMA")) {
			FacesMessageUtil.addMensagemInfo("Matéria-Prima não pode possuir estrutura");
		}else {
		if (this.produto.getDescricao() == null) {
			throw new RuntimeException("O Nome do Produto não pode ser nulo");
		} else {
			estrutura = new Estrutura();
			estrutura.setProduto(produto);
		}
		}
	}
	
	public void removerEstrutura() {
		int index = estruturas.indexOf(estrutura);
		if (index > -1) {
			this.estruturas.remove(index);
		}
	}
	
	public void editarsalvarEstrutura() {
		if (this.produto.getTipoproduto().equals("MATERIA-PRIMA")) {
			FacesMessageUtil.addMensagemInfo("Matéria-Prima não pode possuir estrutura");
		}else {
		if (estrutura.getProduto_estrutura() == null) {
			FacesMessageUtil.addMensagemError("Preencha os dados corretamente");
		} else {
			try {
				int index = estruturas.indexOf(estrutura);
				if (index > -1) {
					estruturas.remove(index);
					estrutura.setProduto(produto);
					estrutura.setValor_custo_estrutura(estrutura.getProduto_estrutura().getValor_custo());
					estrutura.setTotal_custo_estrutura(estrutura.getQtde_estrutura()*estrutura.getValor_custo_estrutura());
					estruturas.add(index, estrutura);
				} else {
					estrutura.setProduto(produto);
					estrutura.setValor_custo_estrutura(estrutura.getProduto_estrutura().getValor_custo());
					estrutura.setTotal_custo_estrutura(estrutura.getQtde_estrutura()*estrutura.getValor_custo_estrutura());
					estruturas.add(estrutura);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			estrutura = new Estrutura();
		}
		}
		custo_automatico();
	}
	
	//carregar imagem
	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile img = evento.getFile();
			Path img_temp = Files.createTempFile(null, null );
			Files.copy(img.getInputstream(), img_temp, StandardCopyOption.REPLACE_EXISTING);
			
			FileInputStream fis = new FileInputStream(img_temp.toFile());
			byte[] bytes = new byte[(int) img_temp.toFile().length()];
			fis.read(bytes);
			fis.close();
		
			produto.setImagem(bytes);
			Files.deleteIfExists(img_temp);
			
		} catch (IOException e) {
			FacesMessageUtil.addMensagemError("Erro ao realizar Upload da Imagem");
			e.printStackTrace();
		}
	}
	
	public double TotalCusto() {
		double totalcusto = 0;
		for (Estrutura et : getEstruturas()) {
			totalcusto = totalcusto + et.getTotal_custo_estrutura();
		}
		return totalcusto;		
	}
	
	public void custo_automatico() {
		boolean at = produto.getAtualiza_custo_automatico();
		if (at == true) {
			double totalcusto = 0;
			for (Estrutura et : getEstruturas()) {
				totalcusto = totalcusto + et.getTotal_custo_estrutura();
			}
			produto.setValor_custo(totalcusto);
		}
	}
	
public void editarsalvarAlmoxarifado() {
		
		if (estoque.getAlmoxarifado() == null) {
			FacesMessageUtil.addMensagemError("Preencha os dados corretamente");
		} else {
			try {
				int index = estoques.indexOf(estoque);
				if (index > -1) {
					estoques.remove(index);
					estoque.setProduto(produto);
					estoques.add(index, estoque);
				} else {
					estoque.setProduto(produto);
					estoques.add(estoque);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			estoque = new Estoque();
		}
	}
	
	public void addNovoAlmoxarifado() {
		if (this.produto.getDescricao() == null) {
			throw new RuntimeException("O Nome do Produto não pode ser nulo");
		} else {
			estoque = new Estoque();
			estoque.setProduto(produto);
		}
	}
	
	public void removerAlmoxarifado() {
		if (estoque.getSaldo() != 0) {
			int index = estoques.indexOf(estoque);
			if (index > -1) {
				this.estoques.remove(index);
			}
		}else {
			FacesMessageUtil.addMensagemWarn("Não é possivél excluir Almoxarifado que possui saldo diferente de ZERO");
		}
		
	}
	
	public double TotalEstoque() {
		double totalestoque = 0;
		if (getEstoques().size() > 0) {
		for (Estoque et : getEstoques()) {
			totalestoque = totalestoque + et.getSaldo();
		}
		}
		return totalestoque;		
	}
	
	public List<Produto> completaproduto(String nome) {
		return servico.buscaproduto(nome);
	}
	
	/* editar produto */
	public String encaminha() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("produtoAux", this.produto);

		return "edita-produto";
	}

	public List<Unidade> getListaunidade() {
		return listaunidade;
	}

	public void setListaunidade(List<Unidade> listaunidade) {
		this.listaunidade = listaunidade;
	}

	public List<Ncm> getListancm() {
		return listancm;
	}

	public void setListancm(List<Ncm> listancm) {
		this.listancm = listancm;
	}

	public List<Cest> getListacest() {
		return listacest;
	}

	public void setListacest(List<Cest> listacest) {
		this.listacest = listacest;
	}

	public Estrutura getEstrutura() {
		return estrutura;
	}

	public void setEstrutura(Estrutura estrutura) {
		this.estrutura = estrutura;
	}

	public Produto getProduto() {
		return produto;
	}

	public List<Estrutura> getEstruturas() {
		return estruturas;
	}

	public void setEstruturas(List<Estrutura> estruturas) {
		this.estruturas = estruturas;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public SubGrupo getSubGrupo() {
		return subGrupo;
	}

	public void setSubGrupo(SubGrupo subGrupo) {
		this.subGrupo = subGrupo;
	}

	public List<SubGrupo> getListasubgrupo() {
		return listasubgrupo;
	}

	public void setListasubgrupo(List<SubGrupo> listasubgrupo) {
		this.listasubgrupo = listasubgrupo;
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public Boolean getIsRederiza() {
		return isRederiza;
	}

	public void setIsRederiza(Boolean isRederiza) {
		this.isRederiza = isRederiza;
	}

	public Boolean getIsRederiza2() {
		return isRederiza2;
	}

	public void setIsRederiza2(Boolean isRederiza2) {
		this.isRederiza2 = isRederiza2;
	}


	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Produto> getLista() {
		return lista;
	}

	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public List<Almoxarifado> getListaalmoxarifado() {
		return listaalmoxarifado;
	}

	public void setListaalmoxarifado(List<Almoxarifado> listaalmoxarifado) {
		this.listaalmoxarifado = listaalmoxarifado;
	}

	public List<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}
	

}
