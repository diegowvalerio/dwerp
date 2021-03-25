package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "tbocorrencia")
public class Ocorrencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idocorrencia;
	@Column(nullable = false, columnDefinition = "varchar(100)")
	private String resumo;
	@Column(nullable = false, columnDefinition = "varchar(100)")
	private String contato;
	@Column(nullable = false, columnDefinition = "varchar(100)")
	private String status;
	private Timestamp data;
	private Timestamp dataconclusao;


	@ManyToOne
	private CadastroGeral cliente;
	@ManyToOne
	private TipoCrm tipocrm;
	@ManyToOne
	private Canal canal;
	@ManyToOne
	private ModuloCrm modulocrm;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Usuario usuariodestino;
	
	@OneToMany(mappedBy="ocorrencia", cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE },orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
    private List<OcorrenciaItem> ocorrenciaItems = new ArrayList<>();
	

	public Ocorrencia() {
		super();
	}
	public Integer getIdocorrencia() {
		return idocorrencia;
	}

	public void setIdocorrencia(Integer idocorrencia) {
		this.idocorrencia = idocorrencia;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getData() {
		return data;
	}

	public Date getDataconclusao() {
		return dataconclusao;
	}

	public CadastroGeral getCliente() {
		return cliente;
	}

	public void setCliente(CadastroGeral cliente) {
		this.cliente = cliente;
	}

	public TipoCrm getTipocrm() {
		return tipocrm;
	}

	public void setTipocrm(TipoCrm tipocrm) {
		this.tipocrm = tipocrm;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public ModuloCrm getModulocrm() {
		return modulocrm;
	}

	public void setModulocrm(ModuloCrm modulocrm) {
		this.modulocrm = modulocrm;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuariodestino() {
		return usuariodestino;
	}

	public void setUsuariodestino(Usuario usuariodestino) {
		this.usuariodestino = usuariodestino;
	}

	public List<OcorrenciaItem> getOcorrenciaItems() {
		return ocorrenciaItems;
	}

	public void setOcorrenciaItems(List<OcorrenciaItem> ocorrenciaItems) {
		this.ocorrenciaItems = ocorrenciaItems;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}
	public void setDataconclusao(Timestamp dataconclusao) {
		this.dataconclusao = dataconclusao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idocorrencia == null) ? 0 : idocorrencia.hashCode());
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
		Ocorrencia other = (Ocorrencia) obj;
		if (idocorrencia == null) {
			if (other.idocorrencia != null)
				return false;
		} else if (!idocorrencia.equals(other.idocorrencia))
			return false;
		return true;
	}

}
