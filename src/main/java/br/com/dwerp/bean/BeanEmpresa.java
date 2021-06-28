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

@Named
@ViewScoped
public class BeanEmpresa implements Serializable{
	private static final long serialVersionUID = 1L;

	private Empresa empresa = new Empresa();
	@Inject
	private ServicoEmpresa servico;
	private List<Empresa> lista;
	
	@Inject
	private ServicoCidade servicoCidade;
	
	private String opcao;
	private Date data;
	
	
	
	public BeanEmpresa() {
		data = new Date();
	}
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.empresa = this.getEmpresa();
		this.empresa.setDtcadastro(data);
		
	}
	
	public String salvar(){
		if(empresa.getIdempresa() == null){
			empresa.setDtcadastro(data);
		}
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
	
	/* editar cadastro */
	public String encaminha() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("empresaAux", this.empresa);

		return "edita-empresa";
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
	
	public List<Cidade> completaCidade(String nome) {
		return servicoCidade.buscacidadenome(nome);
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getLista() {
		return lista;
	}

	public void setLista(List<Empresa> lista) {
		this.lista = lista;
	}


}
