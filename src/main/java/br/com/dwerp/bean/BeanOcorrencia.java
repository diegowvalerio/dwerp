package br.com.dwerp.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.dwerp.entidade.CadastroGeral;
import br.com.dwerp.entidade.Canal;
import br.com.dwerp.entidade.Estrutura;
import br.com.dwerp.entidade.Fechamento;
import br.com.dwerp.entidade.ModuloCrm;
import br.com.dwerp.entidade.Ocorrencia;
import br.com.dwerp.entidade.OcorrenciaItem;
import br.com.dwerp.entidade.TipoCrm;
import br.com.dwerp.entidade.Usuario;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoCadastroGeral;
import br.com.dwerp.servico.ServicoCanal;
import br.com.dwerp.servico.ServicoModuloCrm;
import br.com.dwerp.servico.ServicoOcorrencia;
import br.com.dwerp.servico.ServicoTipoCrm;
import br.com.dwerp.servico.ServicoUsuario;

@Named
@ViewScoped
public class BeanOcorrencia implements Serializable {

	private static final long serialVersionUID = 1L;
	private Ocorrencia ocorrencia = new Ocorrencia();
	private OcorrenciaItem item = new OcorrenciaItem();
	@Inject
	private ServicoOcorrencia servico;
	private List<Ocorrencia> lista;
	private List<OcorrenciaItem> listaitem;

	@Inject
	private ServicoCanal servicocanal;
	private Canal canal = new Canal();
	private List<Canal> listacanal;
	@Inject
	private ServicoTipoCrm servicotipocrm;
	private TipoCrm tipoCrm = new TipoCrm();
	private List<TipoCrm> listatipo;
	@Inject
	private ServicoModuloCrm servicomodulocrm;
	private ModuloCrm modulocrm = new ModuloCrm();
	private List<ModuloCrm> listamodulo;
	@Inject
	private ServicoCadastroGeral servicocliente;
	private CadastroGeral cliente = new CadastroGeral();
	private List<CadastroGeral> listacliente;
	@Inject
	private ServicoUsuario servicousuario;
	private List<Usuario> listausuario;

	@PostConstruct
	public void carregar() {
		GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT-3") , new Locale("pt_BR"));
		Date data = cal.getTime();
		data.setHours(data.getHours()-3);
		cal.setTime(data);
		
		lista = servico.consultar();		
		Collections.sort(lista,Collections.reverseOrder(Comparator.comparing(Ocorrencia::getIdocorrencia)));
		
		listacanal = servicocanal.consultarativos();
		listamodulo = servicomodulocrm.consultarativos();
		listatipo = servicotipocrm.consultarativos();
		listacliente = servicocliente.consultar();
		listausuario = servicousuario.consultar();

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = (HttpSession) request.getSession();

		if (session.getAttribute("ocorrenciaAux") != null) {
			this.ocorrencia = (Ocorrencia) session.getAttribute("ocorrenciaAux");
			this.listaitem = this.ocorrencia.getOcorrenciaItems();
			session.removeAttribute("ocorrenciaAux");
		} else {
			this.ocorrencia = this.getOcorrencia();
			this.listaitem = this.ocorrencia.getOcorrenciaItems();
			this.ocorrencia.setData(new Timestamp(cal.getTimeInMillis()));
			this.ocorrencia.setStatus("ABERTO");

			for (Usuario acesso : listausuario) {
				if (acesso.getLogin().equals(usuarioconectado())) {
					this.ocorrencia.setUsuario(acesso);
					// System.out.println(usuario.getLogin());
				}
			}
		}
	}
	
	public void filtrar_minhas_ocorrencias() {
		for (Usuario acesso : listausuario) {
			if (acesso.getLogin().equals(usuarioconectado())) {
				lista.clear();
				lista = servico.consultar_ocorrencia_usuario(acesso.getIdusuario().toString());
				Collections.sort(lista,Collections.reverseOrder(Comparator.comparing(Ocorrencia::getIdocorrencia)));
			}
		}
	}

	public void excluir() {
		try {
			servico.excluir(ocorrencia.getIdocorrencia());
		} catch (Exception e) {
			if (e.getCause().toString().contains("ConstraintViolationException")) {
				FacesMessageUtil
						.addMensagemError("Registro utilizado em outro local! Não foi possível realizar a operação.");
			} else {
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
	}

	public boolean verifica_status(){
		//botão concluir
		if(ocorrencia.getStatus().equals("ABERTO") || ocorrencia.getStatus().equals("ANDAMENTO")) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean verifica_status_2(){
		//botão reabrir
		if(ocorrencia.getStatus().equals("CONCLUIDO")) {
			return true;
		}else {
			return false;
		}
	}
	
	public String salvar() {
		try {
			ocorrencia.setOcorrenciaItems(listaitem);
			if(ocorrencia.getStatus().equals("ABERTO") && listaitem.size()>1) {
				ocorrencia.setStatus("ANDAMENTO");
			}
			servico.salvar(ocorrencia);
			lista = servico.consultar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "lista-ocorrencia";
	}

	public String concluir() {
		GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT-3") , new Locale("pt_BR"));
		Date data = cal.getTime();
		data.setHours(data.getHours()-3);
		cal.setTime(data);
		try {
			ocorrencia.setOcorrenciaItems(listaitem);
			ocorrencia.setStatus("CONCLUIDO");
			ocorrencia.setDataconclusao(new Timestamp(cal.getTimeInMillis()));
			servico.salvar(ocorrencia);
			lista = servico.consultar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "lista-ocorrencia";
	}
	
	public String reabrir() {
		try {
			ocorrencia.setOcorrenciaItems(listaitem);
			if(listaitem.size()>1) {
				ocorrencia.setStatus("ANDAMENTO");
			}else {
				ocorrencia.setStatus("ABERTO");
			}
			ocorrencia.setDataconclusao(null);
			servico.salvar(ocorrencia);
			lista = servico.consultar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "lista-ocorrencia";
	}
	
	public String verifica_status_ocorrencia(String status) {
		if(status.equals("ABERTO")) {
			return "label label-danger";
			
		}else if(status.equals("ANDAMENTO")){
			return "label label-warning";
		}else {
			return "label label-success";
		}
	}
	
	public void novo() {
		GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT-3") , new Locale("pt_BR"));
		Date data = cal.getTime();
		data.setHours(data.getHours()-3);
		cal.setTime(data);
		
		item = new OcorrenciaItem();
		item.setData(new Timestamp(cal.getTimeInMillis()));
		item.setLogin(usuarioconectado());
		item.setOcorrencia(ocorrencia);
	}

	public void remove() {
		int index = listaitem.indexOf(item);
		if (index > -1) {
			this.listaitem.remove(index);
		}
	}

	public void editarsalvar() {

		try {
			int index = listaitem.indexOf(item);
			if (index > -1) {
				listaitem.remove(index);
				item.setOcorrencia(ocorrencia);
				listaitem.add(index, item);
			} else {
				item.setOcorrencia(ocorrencia);
				listaitem.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		item = new OcorrenciaItem();
	}

	public String encaminha() {

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("ocorrenciaAux", this.ocorrencia);

		return "cadastro-ocorrencia";
	}
	
	public String verificalogin(String login) {
		if(usuarioconectado().equals(login)) {
			return "true";
			
		}else {
			return "false";
		}
	}
	
	public int sortByModel(Object car1, Object car2) {
	     return car2.toString().compareTo(car1.toString());
	}
	
	/* pegar usuario conectado */
	public String usuarioconectado() {
		String nome;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			nome = ((UserDetails) principal).getUsername();
		} else {
			nome = principal.toString();
		}
		return nome;
	}

	public OcorrenciaItem getItem() {
		return item;
	}

	public void setItem(OcorrenciaItem item) {
		this.item = item;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public ModuloCrm getModulocrm() {
		return modulocrm;
	}

	public void setModulocrm(ModuloCrm modulocrm) {
		this.modulocrm = modulocrm;
	}

	public List<Usuario> getListausuario() {
		return listausuario;
	}

	public void setListausuario(List<Usuario> listausuario) {
		this.listausuario = listausuario;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public List<Ocorrencia> getLista() {
		return lista;
	}

	public void setLista(List<Ocorrencia> lista) {
		this.lista = lista;
	}

	public List<Canal> getListacanal() {
		return listacanal;
	}

	public void setListacanal(List<Canal> listacanal) {
		this.listacanal = listacanal;
	}

	public List<TipoCrm> getListatipo() {
		return listatipo;
	}

	public void setListatipo(List<TipoCrm> listatipo) {
		this.listatipo = listatipo;
	}

	public List<ModuloCrm> getListamodulo() {
		return listamodulo;
	}

	public void setListamodulo(List<ModuloCrm> listamodulo) {
		this.listamodulo = listamodulo;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public TipoCrm getTipoCrm() {
		return tipoCrm;
	}

	public void setTipoCrm(TipoCrm tipoCrm) {
		this.tipoCrm = tipoCrm;
	}

	public ModuloCrm getModuloCrm() {
		return modulocrm;
	}

	public void setModuloCrm(ModuloCrm modulocrm) {
		this.modulocrm = modulocrm;
	}

	public List<OcorrenciaItem> getListaitem() {
		return listaitem;
	}

	public void setListaitem(List<OcorrenciaItem> listaitem) {
		this.listaitem = listaitem;
	}

	public CadastroGeral getCliente() {
		return cliente;
	}

	public void setCliente(CadastroGeral cliente) {
		this.cliente = cliente;
	}

	public List<CadastroGeral> getListacliente() {
		return listacliente;
	}

	public void setListacliente(List<CadastroGeral> listacliente) {
		this.listacliente = listacliente;
	}
}
