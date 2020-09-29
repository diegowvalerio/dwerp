package br.com.dwerp.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.dwerp.entidade.CadastroGeral;
import br.com.dwerp.entidade.Fechamento;
import br.com.dwerp.entidade.HoraExtra;
import br.com.dwerp.entidade.SubGrupo;
import br.com.dwerp.msn.FacesMessageUtil;
import br.com.dwerp.servico.ServicoFechamento;


@Named
@ViewScoped
public class BeanFechamento implements Serializable{
	private static final long serialVersionUID = 1L;

	private Fechamento fechamento = new Fechamento();
	@Inject
	private ServicoFechamento servico;
	
	private List<Fechamento> lista;
	
	private HoraExtra horaExtra = new HoraExtra();
	private List<HoraExtra> horasextras;

	
	
	@PostConstruct
	public void ini(){
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) request.getSession();
		this.fechamento.setFuncionario((CadastroGeral) session.getAttribute("cadastrogeralAux"));
		this.fechamento.setSalario(fechamento.getFuncionario().getSalario());
		this.fechamento.setAdicionalfixo(fechamento.getFuncionario().getAdicionalfixo());
		this.fechamento.setInsalubridade(fechamento.getFuncionario().getInsalubridade());
		this.fechamento.setFuncao(fechamento.getFuncionario().getFuncao().getDescricao());
		this.fechamento.setSetor(fechamento.getFuncionario().getSetor().getNome());
		this.fechamento.setAdicionalnoturno(fechamento.getFuncionario().getAdicionalnoturno());
		
		session.removeAttribute("cadastrogeralAux");
		
		Calendar dataatual = GregorianCalendar.getInstance();
		this.fechamento.setMes(Integer.toString(dataatual.get(Calendar.MONTH)+1));
		this.fechamento.setAno(Integer.toString(dataatual.get(Calendar.YEAR)));
		
		this.horasextras = this.fechamento.getHorasextras();
	}
	
	public String salvar(){
		try{
		servico.salvar(fechamento);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-fechamento";
	}
	
	public void calcula_diaria() {
		if (fechamento.getDiaria_qtde_dia() > 0){
			if(fechamento.getFuncionario().getSalario() > 0) {
				fechamento.setDiaria_valor_unitario(fechamento.getFuncionario().getSalario()/30);
				fechamento.setDiaria_valor_total(fechamento.getDiaria_valor_unitario()*fechamento.getDiaria_qtde_dia());
			}
		}
	}
	
	public void calcula_totalhoras() {
		//zerar antes de calcular
		fechamento.setHoraextra_50_valor_total(0.0);
		fechamento.setHoraextra_60_valor_total(0.0);
		fechamento.setHoraextra_100_valor_total(0.0);
		fechamento.setHoraextra_50_valor_total_insalubre(0.0);
		fechamento.setHoraextra_60_valor_total_insalubre(0.0);
		fechamento.setHoraextra_100_valor_total_insalubre(0.0);
		
		Calendar cal_50 = Calendar.getInstance();
		cal_50.set(0000, 0, cal_50.get(Calendar.DATE),00,00);
		Calendar cal_50_insalubre = Calendar.getInstance();
		cal_50_insalubre.set(0000, 0, cal_50.get(Calendar.DATE),00,00);
		
		Calendar cal_60 = Calendar.getInstance();
		cal_60.set(0000, 00, cal_60.get(Calendar.DATE),00,00);
		Calendar cal_60_insalubre = Calendar.getInstance();
		cal_60_insalubre.set(0000, 0, cal_50.get(Calendar.DATE),00,00);
		
		Calendar cal_100 = Calendar.getInstance();
		cal_100.set(0000, 00, cal_100.get(Calendar.DATE),00,00);
		Calendar cal_100_insalubre = Calendar.getInstance();
		cal_100_insalubre.set(0000, 0, cal_50.get(Calendar.DATE),00,00);
		
		double vlunt = 0.0;
		double vlhoras = 0.0;
		double vlminutos = 0.0;
		double minutos = 0.0;
		int verifica_insalubre_marcado = 0;
		
		for (HoraExtra h : getHorasextras()) {	
			vlunt = 0.0;
			vlhoras = 0.0;
			vlminutos = 0.0;
			if(h.getQtdehora() != null) { //verifica se a hora esta nula
			
			if(h.getInsalubre().equals(true) && fechamento.getInsalubridade() < 1) { //verifica insalubre marcado com valor zerado
				verifica_insalubre_marcado += 1 ;
			}
			
			if(h.getTipo_50().equals(true)) {
				
				Calendar cal = Calendar.getInstance();
				cal.set(0000, 0, cal_50.get(Calendar.DATE),00,00);
				
				cal.add(Calendar.HOUR, h.getQtdehora().getHours());
				cal.add(Calendar.MINUTE, h.getQtdehora().getMinutes());
			
				if(h.getInsalubre().equals(true) && fechamento.getInsalubridade()>0) {
					
					vlunt = (fechamento.getSalario()/fechamento.getFuncionario().getCargahoraria_mensal())+
							(fechamento.getInsalubridade()/fechamento.getFuncionario().getCargahoraria_mensal())+
							(((fechamento.getInsalubridade()/fechamento.getFuncionario().getCargahoraria_mensal())+(fechamento.getSalario()/fechamento.getFuncionario().getCargahoraria_mensal()))*0.5);
				}else {
					vlunt = fechamento.getSalario()/fechamento.getFuncionario().getCargahoraria_mensal()+(fechamento.getSalario()/fechamento.getFuncionario().getCargahoraria_mensal())*0.5;
				}
				h.setValor_unitario(vlunt);
				vlhoras = cal.get(Calendar.HOUR) * vlunt;
				minutos = cal.get(Calendar.MINUTE);
				minutos = minutos/60;
				vlminutos = minutos * vlunt;
				h.setValor_total(vlhoras+vlminutos);
				
				//resumo valor
				if(h.getInsalubre().equals(true)) {
					fechamento.setHoraextra_50_valor_total_insalubre(fechamento.getHoraextra_50_valor_total_insalubre()+h.getValor_total());
					cal_50_insalubre.add(Calendar.HOUR, h.getQtdehora().getHours());
					cal_50_insalubre.add(Calendar.MINUTE, h.getQtdehora().getMinutes());
				}else {
					fechamento.setHoraextra_50_valor_total(fechamento.getHoraextra_50_valor_total()+h.getValor_total());
					cal_50.add(Calendar.HOUR, h.getQtdehora().getHours());
					cal_50.add(Calendar.MINUTE, h.getQtdehora().getMinutes());
				}
				
			}
			
			if(h.getTipo_60().equals(true)) {
				
				Calendar cal = Calendar.getInstance();
				cal.set(0000, 0, cal_60.get(Calendar.DATE),00,00);
				
				cal.add(Calendar.HOUR, h.getQtdehora().getHours());
				cal.add(Calendar.MINUTE, h.getQtdehora().getMinutes());
								
				if(h.getInsalubre().equals(true) && fechamento.getInsalubridade()>0) {
					
					vlunt = (fechamento.getSalario()/fechamento.getFuncionario().getCargahoraria_mensal())+
							(fechamento.getInsalubridade()/fechamento.getFuncionario().getCargahoraria_mensal())+
							(((fechamento.getInsalubridade()/fechamento.getFuncionario().getCargahoraria_mensal())+(fechamento.getSalario()/fechamento.getFuncionario().getCargahoraria_mensal()))*0.6);
				}else {
					vlunt = fechamento.getSalario()/fechamento.getFuncionario().getCargahoraria_mensal()+(fechamento.getSalario()/fechamento.getFuncionario().getCargahoraria_mensal())*0.6;
				}
				h.setValor_unitario(vlunt);
				vlhoras = cal.get(Calendar.HOUR) * vlunt;
				minutos = cal.get(Calendar.MINUTE);
				minutos = minutos/60;
				vlminutos = minutos * vlunt;
				h.setValor_total(vlhoras+vlminutos);
				
				//resumo valor
				if(h.getInsalubre().equals(true)) {
					fechamento.setHoraextra_60_valor_total_insalubre(fechamento.getHoraextra_60_valor_total_insalubre()+h.getValor_total());
					cal_60_insalubre.add(Calendar.HOUR, h.getQtdehora().getHours());
					cal_60_insalubre.add(Calendar.MINUTE, h.getQtdehora().getMinutes());
				}else {
					fechamento.setHoraextra_60_valor_total(fechamento.getHoraextra_60_valor_total()+h.getValor_total());
					cal_60.add(Calendar.HOUR, h.getQtdehora().getHours());
					cal_60.add(Calendar.MINUTE, h.getQtdehora().getMinutes());
				}
			}
			
			if(h.getTipo_100().equals(true)) {
				
				Calendar cal = Calendar.getInstance();
				cal.set(0000, 0, cal_100.get(Calendar.DATE),00,00);
				
				cal.add(Calendar.HOUR, h.getQtdehora().getHours());
				cal.add(Calendar.MINUTE, h.getQtdehora().getMinutes());
								
				if(h.getInsalubre().equals(true) && fechamento.getInsalubridade()>0) {
					
					vlunt = (fechamento.getSalario()/fechamento.getFuncionario().getCargahoraria_mensal())+
							(fechamento.getInsalubridade()/fechamento.getFuncionario().getCargahoraria_mensal())+
							(((fechamento.getInsalubridade()/fechamento.getFuncionario().getCargahoraria_mensal())+(fechamento.getSalario()/fechamento.getFuncionario().getCargahoraria_mensal()))*1);
				}else {
					vlunt = fechamento.getSalario()/fechamento.getFuncionario().getCargahoraria_mensal()+(fechamento.getSalario()/fechamento.getFuncionario().getCargahoraria_mensal())*1;
				}
				h.setValor_unitario(vlunt);
				vlhoras = cal.get(Calendar.HOUR) * vlunt;
				minutos = cal.get(Calendar.MINUTE);
				minutos = minutos/60;
				vlminutos = minutos * vlunt;
				h.setValor_total(vlhoras+vlminutos);
				
				//resumo valor
				if(h.getInsalubre().equals(true)) {
					fechamento.setHoraextra_100_valor_total_insalubre(fechamento.getHoraextra_100_valor_total_insalubre()+h.getValor_total());
					cal_100_insalubre.add(Calendar.HOUR, h.getQtdehora().getHours());
					cal_100_insalubre.add(Calendar.MINUTE, h.getQtdehora().getMinutes());
				}else {
					fechamento.setHoraextra_100_valor_total(fechamento.getHoraextra_100_valor_total()+h.getValor_total());
					cal_100.add(Calendar.HOUR, h.getQtdehora().getHours());
					cal_100.add(Calendar.MINUTE, h.getQtdehora().getMinutes());
				}
				
				}
			}
		}
		//cal_50f.setTime(cal_50.getTime());
		//cal_50.set(0000, 00, cal_50.get(Calendar.DATE),cal_50f.get(Calendar.HOUR),00);
		//fechamento.setHoraextra_50_qtde_hora(cal_50.getTime());
		//cal_50.set(0000, 00, cal_50.get(Calendar.DATE),00,cal_50f.get(Calendar.MINUTE));
		//fechamento.setHoraextra_50_qtde_minuto(cal_50.getTime());
		
		fechamento.setHoraextra_50_qtde_hora(cal_50.getTime());
		fechamento.setHoraextra_50_qtde_hora_insalubre(cal_50_insalubre.getTime());
		
		//cal_60f.setTime(cal_60.getTime());
		//cal_60.set(0000, 00, cal_60.get(Calendar.DATE),cal_60f.get(Calendar.HOUR),00);
		//fechamento.setHoraextra_60_qtde_hora(cal_60.getTime());
		//cal_60.set(0000, 00, cal_60.get(Calendar.DATE),00,cal_60f.get(Calendar.MINUTE));
		//fechamento.setHoraextra_60_qtde_minuto(cal_60.getTime());
		
		fechamento.setHoraextra_60_qtde_hora(cal_60.getTime());
		fechamento.setHoraextra_60_qtde_hora_insalubre(cal_60_insalubre.getTime());
		
		//cal_100f.setTime(cal_100.getTime());
		//cal_100.set(0000, 00, cal_100.get(Calendar.DATE),cal_100f.get(Calendar.HOUR),00);
		//fechamento.setHoraextra_100_qtde_hora(cal_100.getTime());
		//cal_100.set(0000, 00, cal_100.get(Calendar.DATE),00,cal_100f.get(Calendar.MINUTE));
		//fechamento.setHoraextra_100_qtde_minuto(cal_100.getTime());
		
		fechamento.setHoraextra_100_qtde_hora(cal_100.getTime());
		fechamento.setHoraextra_100_qtde_hora_insalubre(cal_100_insalubre.getTime());
		
		if(verifica_insalubre_marcado > 0) {
		 FacesMessageUtil.addMensagemWarn("Existem "+ verifica_insalubre_marcado +" Datas marcadas como Insalubre, Verifique valor da Insalubridade !! ");
		}
		
	}
	
	//adicionar hora extra
	public void addNovo() {
		if (this.fechamento.getMes() == null) {
			throw new RuntimeException("O Mês não pode ser nulo");
		} else {
			//limpa dados
			horasextras.clear();
			
			DateFormat simple = new SimpleDateFormat ("dd/MM/yyyy");
			
			GregorianCalendar calendarfinal = new GregorianCalendar();
			GregorianCalendar calendarfor = new GregorianCalendar();
			GregorianCalendar calendar = new GregorianCalendar();
			int dia = 01;
			int mes = Integer.parseInt(fechamento.getMes())-1;
			int ano = Integer.parseInt(fechamento.getAno());
			
			calendar.set(ano, mes, dia);
			calendarfinal.set(ano, mes, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			calendarfor.setTime(calendar.getTime());
			
			for(Date dt = calendar.getTime(); dt.compareTo(calendarfinal.getTime()) <= 0; ) {
				horaExtra = new HoraExtra();
				horaExtra.setFechamento(fechamento);
				horaExtra.setData(dt);
				
				if(fechamento.getInsalubridade()>0) {
					horaExtra.setInsalubre(true);
				}
				
				horasextras.add(horaExtra);
				
				//System.out.println(simple.format(dt));
				calendarfor.add(Calendar.DATE,+1);
				dt = calendarfor.getTime();
			}
			
			horaExtra = new HoraExtra();
			horaExtra.setFechamento(fechamento);
		}
	}
	
	public void remover() {
		int index = horasextras.indexOf(horaExtra);
		if (index > -1) {
			this.horasextras.remove(index);
		}
	}
	
	public void editarsalvar() {

		if (horaExtra.getQtdehora() == null) {
			FacesMessageUtil.addMensagemError("Preencha os dados corretamente");
		} else {
			try {
				int index = horasextras.indexOf(horaExtra);
				if (index > -1) {
					horasextras.remove(index);
					horaExtra.setFechamento(fechamento);
					horasextras.add(index, horaExtra);
				} else {
					horaExtra.setFechamento(fechamento);
					horasextras.add(horaExtra);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			horaExtra = new HoraExtra();
		}
	}
	//fim hora
	

	public Fechamento getFechamento() {
		return fechamento;
	}


	public HoraExtra getHoraExtra() {
		return horaExtra;
	}

	public void setHoraExtra(HoraExtra horaExtra) {
		this.horaExtra = horaExtra;
	}

	public List<HoraExtra> getHorasextras() {
		return horasextras;
	}

	public void setHorasextras(List<HoraExtra> horasextras) {
		this.horasextras = horasextras;
	}

	public void setFechamento(Fechamento fechamento) {
		this.fechamento = fechamento;
	}


	public List<Fechamento> getLista() {
		return lista;
	}


	public void setLista(List<Fechamento> lista) {
		this.lista = lista;
	}

	
}
