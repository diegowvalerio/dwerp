package br.com.dwerp.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


import br.com.dwerp.entidade.Cidade;
import br.com.dwerp.entidade.Empresa;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoCidade;
import br.com.dwerp.servico.ServicoEmpresa;
import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TRetConsStatServ;

@Named
@ViewScoped
public class BeanEmpresaEdita implements Serializable{
	private static final long serialVersionUID = 1L;

	private Empresa empresa = new Empresa();
	@Inject
	private ServicoEmpresa servico;
	private List<Empresa> lista;
	
	@Inject
	private ServicoCidade servicoCidade;
	
	private ConfiguracoesNfe config;
	
	private String opcao;
	private Date data;
	private Boolean isRederiza = false;
	private Boolean isRederiza2 = false;
	
	private String retorno;
	
	@PostConstruct
	public void ini(){
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) request.getSession();
		this.empresa = (Empresa) session.getAttribute("empresaAux");
		
		session.removeAttribute("empresaAux");
		//pega o config da session
		this.config = (ConfiguracoesNfe) session.getAttribute("configuracoesnfe");
	}
	
	public String salvar(){
		try{
		servico.salvar(empresa);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-empresa";
	}

	public String excluir() {
		try{
		servico.excluir(empresa.getIdempresa());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();

		return "lista-empresa";
	}
		
	public List<Cidade> completaCidade(String nome) {
		return servicoCidade.buscacidadenome(nome);
	}
	
	public void upload_certificado(FileUploadEvent evento) {
		
		try {
			UploadedFile cert = evento.getFile();
			Path cert_temp = Files.createTempFile(null, null );
			Files.copy(cert.getInputstream(), cert_temp, StandardCopyOption.REPLACE_EXISTING);
			
			FileInputStream fis = new FileInputStream(cert_temp.toFile());
			byte[] bytes = new byte[(int) cert_temp.toFile().length()];
			fis.read(bytes);
			fis.close();
			
			empresa.setCertificadobyte(bytes);
			Files.deleteIfExists(cert_temp);			
			certificado(bytes,empresa.getSenhacertificado());        
			FacesMessageUtil.addMensagemInfo("Certificado enviado com Sucesso !");
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessageUtil.addMensagemError("Erro ao enviar certificado, favor verifique");
			
		}
			
    }
	
	public void certificado(byte[] bytes, String senha) throws FileNotFoundException {
		try {
			Certificado certificado = certifidoA1Pfx(bytes, senha);
			empresa.setDtvencimentocertificado(Date.from(certificado.getVencimento().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			
		} catch (CertificadoException e){
            //System.err.println(e.getMessage());
            e.printStackTrace();
        }
	}
	
	 private Certificado certifidoA1Pfx(byte[] bytes, String senha) throws CertificadoException {
	        byte[] certificadoByte = bytes ;
	        
	        return CertificadoService.certificadoPfxBytes(certificadoByte, senha);
	  }
	 
	 public void statusservicosefaz() throws NfeException, CertificadoException {
		 try {
		 		 
		//Efetua Consulta
         TRetConsStatServ retorno = Nfe.statusServico(config, DocumentoEnum.NFE);
         
         System.out.println();
         System.out.println("# Status: " + retorno.getCStat() + " - " + retorno.getXMotivo());
         this.retorno = retorno.getCStat()+"-"+retorno.getXMotivo();
		 }catch (Exception e) {
             System.err.println("# Erro: "+e.getMessage());
         }
	 }
	

	public ConfiguracoesNfe getConfig() {
		return config;
	}

	public void setConfig(ConfiguracoesNfe config) {
		this.config = config;
	}

	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Empresa> getLista() {
		return lista;
	}

	public void setLista(List<Empresa> lista) {
		this.lista = lista;
	}

	public Empresa getCadastroGeral() {
		return empresa;
	}

	public void setCadastroGeral(Empresa empresa) {
		this.empresa = empresa;
	}
	

}
