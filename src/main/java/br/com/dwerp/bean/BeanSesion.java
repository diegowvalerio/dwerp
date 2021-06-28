package br.com.dwerp.bean;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.dwerp.entidade.Empresa;
import br.com.dwerp.servico.ServicoEmpresa;
import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;



@ManagedBean
@SessionScoped
public class BeanSesion implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Empresa empresa = new Empresa();
	@Inject
	private ServicoEmpresa servicoempresa;
	
	@PostConstruct
	public void carregar() throws NfeException, CertificadoException{
		//pegando a primeira empresa da lista, sempre ter apenas uma empresa por instalação
		List<Empresa> lista= servicoempresa.consultar();
		if(lista.size()>0){
			empresa = lista.get(0);
			ConfiguracoesNfe config = iniciaConfigurações();
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
			session.setAttribute("configuracoesnfe", config);			
		}			
	}
	
	public ConfiguracoesNfe iniciaConfigurações() throws NfeException, CertificadoException {
		Certificado certificado =certifidoA1Pfx(empresa.getCertificadobyte(), empresa.getSenhacertificado());
			
		return ConfiguracoesNfe.criarConfiguracoes(EstadosEnum.getByCodigoIbge(empresa.getCidade().getEstado().getCodigoibge()) , AmbienteEnum.getByCodigo(empresa.getAmbiente()),certificado, "\\lib\\schemas");
	}
	
	 private Certificado certifidoA1Pfx(byte[] bytes, String senha) throws CertificadoException {
	        byte[] certificadoByte = bytes ;
	        
	        return CertificadoService.certificadoPfxBytes(certificadoByte, senha);
	  }

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
