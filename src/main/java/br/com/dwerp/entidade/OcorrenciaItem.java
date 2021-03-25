package br.com.dwerp.entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "tbocorrenciaitem")
public class OcorrenciaItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idocorrenciaitem;

	@Column(nullable = false, columnDefinition = "varchar(500)")
	private String descricao;
	@Column(nullable = false, columnDefinition = "varchar(100)")
	private String contato;
	private Timestamp data;
	@Column(nullable = false, columnDefinition = "varchar(10)")
	private String login;

	@ManyToOne
	private Ocorrencia ocorrencia;

	public OcorrenciaItem() {
		super();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getIdocorrenciaitem() {
		return idocorrenciaitem;
	}

	public void setIdocorrenciaitem(Integer idocorrenciaitem) {
		this.idocorrenciaitem = idocorrenciaitem;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contato == null) ? 0 : contato.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((idocorrenciaitem == null) ? 0 : idocorrenciaitem.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((ocorrencia == null) ? 0 : ocorrencia.hashCode());
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
		OcorrenciaItem other = (OcorrenciaItem) obj;
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idocorrenciaitem == null) {
			if (other.idocorrenciaitem != null)
				return false;
		} else if (!idocorrenciaitem.equals(other.idocorrenciaitem))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (ocorrencia == null) {
			if (other.ocorrencia != null)
				return false;
		} else if (!ocorrencia.equals(other.ocorrencia))
			return false;
		return true;
	}

	

	
}
