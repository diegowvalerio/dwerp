package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.TipoCrm;

@FacesConverter(forClass = TipoCrm.class,value="conversorTipoCrm")
public class TipoCrmConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (TipoCrm) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof TipoCrm) {
        	TipoCrm entity= (TipoCrm) value;
            if (entity != null && entity instanceof TipoCrm && entity.getIdtipocrm() != null) {
                uiComponent.getAttributes().put( entity.getIdtipocrm().toString(), entity);
                return entity.getIdtipocrm().toString();
            }
        }
        return "";
    }
}