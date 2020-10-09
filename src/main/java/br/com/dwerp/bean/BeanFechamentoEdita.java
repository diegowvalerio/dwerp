package br.com.dwerp.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

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
import br.com.dwerp.relatorio.Relatorio;
import br.com.dwerp.servico.ServicoFechamento;


@Named
@ViewScoped
public class BeanFechamentoEdita implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final Locale LOCAL = new Locale("pt","BR");

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
		
		this.fechamento = (Fechamento) session.getAttribute("fechamentoAux");
		this.horasextras = this.fechamento.getHorasextras();
		
		session.removeAttribute("fechamentoAux");	
		
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
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("cadastrogeralAux", this.fechamento.getFuncionario());
		
		return "consulta-fechamento";
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

		DateFormat simple = new SimpleDateFormat ("HH:mm");
		//zerar antes de calcular
		fechamento.setHoraextra_50_valor_total(0.0);
		fechamento.setHoraextra_60_valor_total(0.0);
		fechamento.setHoraextra_100_valor_total(0.0);
		fechamento.setHoraextra_50_valor_total_insalubre(0.0);
		fechamento.setHoraextra_60_valor_total_insalubre(0.0);
		fechamento.setHoraextra_100_valor_total_insalubre(0.0);
		
		Duration d50 = Duration.ZERO;
		Duration d50i = Duration.ZERO;
		Duration d60 = Duration.ZERO;
		Duration d60i = Duration.ZERO;
		Duration d100 = Duration.ZERO;
		Duration d100i = Duration.ZERO;
		
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
			
			if(h.getTipo_50() != null) {
				Duration d = Duration.ZERO;
				Calendar cal = Calendar.getInstance();
				cal.set(0000, 0, cal_50.get(Calendar.DATE),00,00);
				
				cal.add(Calendar.HOUR, h.getTipo_50().getHours());
				cal.add(Calendar.MINUTE, h.getTipo_50().getMinutes());
						
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

					d = Duration.parse("PT"+cal.get(Calendar.HOUR)+"H"+cal.get(Calendar.MINUTE)+"M");
					d50i = d50i.plus(d);
					
				}else {
					fechamento.setHoraextra_50_valor_total(fechamento.getHoraextra_50_valor_total()+h.getValor_total());
					
					d = Duration.parse("PT"+cal.get(Calendar.HOUR)+"H"+cal.get(Calendar.MINUTE)+"M");
					d50 = d50.plus(d);
				}
				
			}
			
			if(h.getTipo_60() != null) {
				Duration d = Duration.ZERO;
				Calendar cal = Calendar.getInstance();
				cal.set(0000, 0, cal_60.get(Calendar.DATE),00,00);
				
				cal.add(Calendar.HOUR, h.getTipo_60().getHours());
				cal.add(Calendar.MINUTE, h.getTipo_60().getMinutes());
								
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
					d = Duration.parse("PT"+cal.get(Calendar.HOUR)+"H"+cal.get(Calendar.MINUTE)+"M");
					d60i = d60i.plus(d);
					
				}else {
					fechamento.setHoraextra_60_valor_total(fechamento.getHoraextra_60_valor_total()+h.getValor_total());
					
					d = Duration.parse("PT"+cal.get(Calendar.HOUR)+"H"+cal.get(Calendar.MINUTE)+"M");
					d60 = d60.plus(d);
				}
			}
			
			if(h.getTipo_100() != null) {
				Duration d = Duration.ZERO;
				Calendar cal = Calendar.getInstance();
				cal.set(0000, 0, cal_100.get(Calendar.DATE),00,00);
				
				cal.add(Calendar.HOUR, h.getTipo_100().getHours());
				cal.add(Calendar.MINUTE, h.getTipo_100().getMinutes());
								
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
					d = Duration.parse("PT"+cal.get(Calendar.HOUR)+"H"+cal.get(Calendar.MINUTE)+"M");
					d100i = d100i.plus(d);
				}else {
					fechamento.setHoraextra_100_valor_total(fechamento.getHoraextra_100_valor_total()+h.getValor_total());					
					d = Duration.parse("PT"+cal.get(Calendar.HOUR)+"H"+cal.get(Calendar.MINUTE)+"M");
					d100 = d100.plus(d);
				}
				
				}
			}
			//caso seja feriado e pague 100% 
			if (h.getQtdehora() == null && h.getTipo_100() != null) {
				Duration d = Duration.ZERO;
				Calendar cal = Calendar.getInstance();
				cal.set(0000, 0, cal_100.get(Calendar.DATE),00,00);
				
				cal.add(Calendar.HOUR, h.getTipo_100().getHours());
				cal.add(Calendar.MINUTE, h.getTipo_100().getMinutes());
								
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
					d = Duration.parse("PT"+cal.get(Calendar.HOUR)+"H"+cal.get(Calendar.MINUTE)+"M");
					d100i = d100i.plus(d);
				}else {
					fechamento.setHoraextra_100_valor_total(fechamento.getHoraextra_100_valor_total()+h.getValor_total());					
					d = Duration.parse("PT"+cal.get(Calendar.HOUR)+"H"+cal.get(Calendar.MINUTE)+"M");
					d100 = d100.plus(d);
				}
			}
		}
				
		fechamento.setHoraextra_50_qtde_hora(calcula_duracao(d50));
		fechamento.setHoraextra_50_qtde_hora_insalubre(calcula_duracao(d50i));
		
		fechamento.setHoraextra_60_qtde_hora(calcula_duracao(d60));
		fechamento.setHoraextra_60_qtde_hora_insalubre(calcula_duracao(d60i));
		
				
		fechamento.setHoraextra_100_qtde_hora(calcula_duracao(d100));
		fechamento.setHoraextra_100_qtde_hora_insalubre(calcula_duracao(d100i));
		
		gerarobservacao();
		
		if(verifica_insalubre_marcado > 0) {
		 FacesMessageUtil.addMensagemWarn("Existem "+ verifica_insalubre_marcado +" Datas marcadas como Insalubre, Verifique valor da Insalubridade !! ");
		}
		
	}
	
	public String calcula_duracao(Duration duracao) {
		long totalSegundos = duracao.getSeconds();
		long totalHoras = totalSegundos / 3600;
		totalSegundos -= (totalHoras * 3600);
		long totalMinutos = totalSegundos / 60;
		totalSegundos -= (totalMinutos * 60);
		
		return String.format("%02d:%02d:%02d", totalHoras, totalMinutos, totalSegundos);
	}
	
	public void gerarobservacao() {
		DateFormat simple = new SimpleDateFormat ("HH:mm");
		DecimalFormat f = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(LOCAL));
		
		fechamento.setHoraextra_50_observacao("");
		fechamento.setHoraextra_60_observacao("");
		fechamento.setHoraextra_100_observacao("");
		//50%
		if(fechamento.getHoraextra_50_valor_total() > 0) {
			fechamento.setHoraextra_50_observacao("R$ "+f.format(fechamento.getHoraextra_50_valor_total()) +" REFERENTE HÁ "+fechamento.getHoraextra_50_qtde_hora()+" HORAS EXTRAS 50%.");
		}
		if(fechamento.getHoraextra_50_valor_total_insalubre()>0 && fechamento.getHoraextra_50_valor_total() > 0) {
			fechamento.setHoraextra_50_observacao(""+fechamento.getHoraextra_50_observacao()+" E R$ "+f.format(fechamento.getHoraextra_50_valor_total_insalubre()) +" REFERENTE HÁ "+fechamento.getHoraextra_50_qtde_hora_insalubre()+" HORAS EXTRAS INSALUBRE 50%.");
		}
		if(fechamento.getHoraextra_50_valor_total_insalubre()>0 && fechamento.getHoraextra_50_valor_total() == 0) {
			fechamento.setHoraextra_50_observacao("R$ "+f.format(fechamento.getHoraextra_50_valor_total_insalubre()) +" REFERENTE HÁ "+fechamento.getHoraextra_50_qtde_hora_insalubre()+" HORAS EXTRAS INSALUBRE 50%.");
		}
		//60%
		if(fechamento.getHoraextra_60_valor_total() > 0) {
			fechamento.setHoraextra_60_observacao("R$ "+f.format(fechamento.getHoraextra_60_valor_total()) +" REFERENTE HÁ "+fechamento.getHoraextra_60_qtde_hora()+" HORAS EXTRAS 60%.");
		}
		if(fechamento.getHoraextra_60_valor_total_insalubre()>0 && fechamento.getHoraextra_60_valor_total() > 0) {
			fechamento.setHoraextra_60_observacao(""+fechamento.getHoraextra_60_observacao()+" E R$ "+f.format(fechamento.getHoraextra_60_valor_total_insalubre()) +" REFERENTE HÁ "+fechamento.getHoraextra_60_qtde_hora_insalubre()+" HORAS EXTRAS INSALUBRE 60%.");
		}
		if(fechamento.getHoraextra_60_valor_total_insalubre()>0 && fechamento.getHoraextra_60_valor_total() == 0) {
			fechamento.setHoraextra_60_observacao("R$ "+f.format(fechamento.getHoraextra_60_valor_total_insalubre()) +" REFERENTE HÁ "+fechamento.getHoraextra_60_qtde_hora_insalubre()+" HORAS EXTRAS INSALUBRE 60%.");
		}
		//100%
		if(fechamento.getHoraextra_100_valor_total() > 0) {
			fechamento.setHoraextra_100_observacao("R$ "+f.format(fechamento.getHoraextra_100_valor_total()) +" REFERENTE HÁ "+fechamento.getHoraextra_100_qtde_hora()+" HORAS EXTRAS 100%.");
		}
		if(fechamento.getHoraextra_100_valor_total_insalubre()>0 && fechamento.getHoraextra_100_valor_total() > 0) {
			fechamento.setHoraextra_100_observacao(""+fechamento.getHoraextra_100_observacao()+" E R$ "+f.format(fechamento.getHoraextra_100_valor_total_insalubre()) +" REFERENTE HÁ "+fechamento.getHoraextra_100_qtde_hora_insalubre()+" HORAS EXTRAS INSALUBRE 100%.");
		}
		if(fechamento.getHoraextra_100_valor_total_insalubre()>0 && fechamento.getHoraextra_100_valor_total() == 0) {
			fechamento.setHoraextra_100_observacao("R$ "+f.format(fechamento.getHoraextra_100_valor_total_insalubre()) +" REFERENTE HÁ "+fechamento.getHoraextra_100_qtde_hora_insalubre()+" HORAS EXTRAS INSALUBRE 100%.");
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
				horaExtra.setDiadasemana(weekDay(calendarfor));
				
				if(fechamento.getInsalubridade()>0) {
					horaExtra.setInsalubre(true);
				}else {
					horaExtra.setInsalubre(false);
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
	
	public String weekDay(Calendar cal) {
		
		return new DateFormatSymbols().getShortWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
		
	}
	
	public void atualiza() {

		for (HoraExtra h : horasextras) {
			if ((h.getDiadasemana().equals("Sáb") || h.getDiadasemana().equals("Dom")) && h.getQtdehora() != null) {
				h.setTipo_100(h.getQtdehora());
			} else {
				Calendar cal = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				cal.setTime(h.getData());
				cal2.setTime(h.getData());
				cal.setFirstDayOfWeek (Calendar.MONDAY);
				cal2.setFirstDayOfWeek (Calendar.MONDAY);
				int diasemana = cal.get(Calendar.DAY_OF_WEEK);
				cal.add (Calendar.DAY_OF_MONTH, Calendar.MONDAY - diasemana);
				cal2.add (Calendar.DAY_OF_MONTH, Calendar.FRIDAY - diasemana);
				
				Duration totalhoras = Duration.ZERO;
				Duration totalhoras50 = Duration.ZERO;
				Duration totalhoras60 = Duration.ZERO;
				
				for(Date dt = cal.getTime(); dt.compareTo(cal2.getTime()) <= 0; ) {
					for (HoraExtra h2 : horasextras) {
						if(h2.getData().equals(dt) && h2.getQtdehora() != null) {
							Duration d = Duration.ZERO;
							Duration d2 = Duration.ZERO;
							Duration d3 = Duration.ZERO;
							
							d = Duration.parse("PT" + h2.getQtdehora().getHours() + "H" + h2.getQtdehora().getMinutes() + "M");
							totalhoras = totalhoras.plus(d);
																					
							// se totalhoras for maior que 10 hrs (600 minutos)
							if (totalhoras.toMinutes() > 600) {
								if(totalhoras50.toMinutes() < 600) {
								Long min = totalhoras.toMinutes() - 600;
								Long min2 = 600 - totalhoras50.toMinutes() ;
								
										
								Long sec = min * 60;
								Long hr = sec / 3600;
								sec -= hr * 3600;
								min = sec /60;
								sec -= min *60;	    				
				    			
								Calendar x = Calendar.getInstance();
								x.set(0000, 0, Calendar.DATE,Integer.parseInt(hr.toString()),Integer.parseInt(min.toString()));
								h2.setTipo_60(x.getTime());
								
								
								Long sec2 = min2 * 60;
								Long hr2 = sec2 / 3600;
								sec2 -= hr2 * 3600;
								min2 = sec2 /60;
								sec2 -= min2 *60;	
								
								Calendar x2 = Calendar.getInstance();
								x2.set(0000, 0, Calendar.DATE,Integer.parseInt(hr2.toString()),Integer.parseInt(min2.toString()));
								h2.setTipo_50(x2.getTime());
								
																								
								}
								
								if(totalhoras50.toMinutes() == 600) {
									Long min = (totalhoras.toMinutes() - 600) - totalhoras60.toMinutes();
									Long min2 = d2.toMinutes() - min;
											
									Long sec = min * 60;
									Long hr = sec / 3600;
									sec -= hr * 3600;
									min = sec /60;
									sec -= min *60;	    				
					    			
									Calendar x = Calendar.getInstance();
									x.set(0000, 0, Calendar.DATE,Integer.parseInt(hr.toString()),Integer.parseInt(min.toString()));
									h2.setTipo_60(x.getTime());
									
									x.set(0000, 0, Calendar.DATE,00,00);
									h2.setTipo_50(x.getTime());
									
								}
								
							}else {
								h2.setTipo_50(h2.getQtdehora());
								
							}
							
							if(h2.getTipo_60() != null) {
								d3 = Duration.parse("PT" + h2.getTipo_60().getHours() + "H" + h2.getTipo_60().getMinutes() + "M");
								totalhoras60 = totalhoras60.plus(d3);
							}
							
							if(h2.getTipo_50() != null) {
								d2 = Duration.parse("PT" + h2.getTipo_50().getHours() + "H" + h2.getTipo_50().getMinutes() + "M");
								totalhoras50 = totalhoras50.plus(d2);
							}
							
						}
						
					}
					Calendar c = Calendar.getInstance();
			    	c.setTime(dt);
			    	c.add(Calendar.DATE, +1);
			    	dt = c.getTime();
				}
				
			}
		}
	}
	
	public void verifica_total_semana(Date data) {/*
		DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
		Duration ht = Duration.ZERO;
		
		Calendar cal = Calendar.getInstance();
		Calendar cx = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal.setTime(data);
		cal2.setTime(data);
		cx.setTime(data);
					
		cal.setFirstDayOfWeek (Calendar.MONDAY);
		cal2.setFirstDayOfWeek (Calendar.MONDAY);
		int diasemana = cal.get(Calendar.DAY_OF_WEEK);
		cal.add (Calendar.DAY_OF_MONTH, Calendar.MONDAY - diasemana);
		cal2.add (Calendar.DAY_OF_MONTH, Calendar.FRIDAY - diasemana);
			
	    //System.out.println (df.format (cal.getTime()));
	    //System.out.println (df.format (cal2.getTime()));
	    
	    for(HoraExtra h : horasextras) {
	    	
	    	 if((h.getDiadasemana().equals("Sáb") || h.getDiadasemana().equals("Dom")) && h.getQtdehora() != null ) {
	    		 Calendar c1 = Calendar.getInstance();
	 	   		c1.set(0000, 0, cx.get(Calendar.DATE),00,00);
	 				
	 	   		c1.add(Calendar.HOUR, h.getQtdehora().getHours());
	 	   		c1.add(Calendar.MINUTE, h.getQtdehora().getMinutes());
	 			h.setTipo_100(c1.getTime());
	 		}else {
	    	
	    	for(Date dt = cal.getTime(); dt.compareTo(cal2.getTime()) <= 0; ) {
	    			    			
	    		Duration d = Duration.ZERO;
	    		if(h.getData().equals(dt) && h.getTipo_50() != null) {
	    			    			
						
					d = Duration.parse("PT"+h.getTipo_50().getHours()+"H"+h.getTipo_50().getMinutes()+"M");
					ht = ht.plus(d);
	    		}
	    		if(h.getData().equals(data) && h.getQtdehora() != null) {
	    			
				if(ht.toMinutes() > 600 ) {	
					
					Duration d2 = Duration.ZERO;
					d2 = Duration.parse("PT"+h.getQtdehora().getHours()+"H"+h.getQtdehora().getMinutes()+"M");	
					
					Long min = ht.toMinutes() - 600;
					Long min2 = d2.toMinutes() - min;
							
					Long sec = min * 60;
					Long hr = sec / 3600;
					sec -= hr * 3600;
					min = sec /60;
					sec -= min *60;	    				
	    			
					Calendar x = Calendar.getInstance();
					x.set(0000, 0, Calendar.DATE,Integer.parseInt(hr.toString()),Integer.parseInt(min.toString()));
					h.setTipo_60(x.getTime());
								
					//Long min2 = d.toMinutes() - 600;
					Long sec2 = min2 * 60;
					Long hr2 = sec2 / 3600;
					sec2 -= hr2 * 3600;
					min2 = sec2 /60;
					sec2 -= min2 *60;	
								
					x.set(0000, 0, Calendar.DATE,Integer.parseInt(hr2.toString()),Integer.parseInt(min2.toString()));
					h.setTipo_50(x.getTime());
								
				}else {
					
					Calendar c1 = Calendar.getInstance();
				 	c1.set(0000, 0, cx.get(Calendar.DATE),00,00);
								
					c1.add(Calendar.HOUR, h.getQtdehora().getHours());
					c1.add(Calendar.MINUTE, h.getQtdehora().getMinutes());
					h.setTipo_50(c1.getTime());
				}
	    			
	    	}
	    	Calendar c = Calendar.getInstance();
	    	c.setTime(dt);
	    	c.add(Calendar.DATE, +1);
	    	dt = c.getTime();
	    	}	
	 	}
	 }
	    
	System.out.println(calcula_duracao(ht));
	*/
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
	
	//gerar recibos
	
	public void recibo_horas() {
		Relatorio report = new Relatorio();
		report.recibo_horaextra(fechamento.getIdfechamento());
	}
	
	//fim recibos

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
