package br.com.dwerp.bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;

import javax.inject.Named;

import br.com.dwerp.fabrica.Backup;

@Named
@ViewScoped
public class BeanBackup implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date data;
	
	public void fazerbackup() {
		data = new Date();
		String s = "C:\\Users\\DiegO\\Documents\\bkp.backup";
		String caminho = "C:\\Program Files\\PostgreSQL\\12\\bin\\";
		Backup.fazBackup(s, caminho);
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
