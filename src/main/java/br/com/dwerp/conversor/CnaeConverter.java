package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.Cnae;


@FacesConverter(forClass = Cnae.class,value="conversorCnae")
public class CnaeConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Cnae) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Cnae) {
        	Cnae entity= (Cnae) value;
            if (entity != null && entity instanceof Cnae && entity.getIdcnae() != null) {
                uiComponent.getAttributes().put( entity.getIdcnae().toString(), entity);
                return entity.getIdcnae().toString();
            }
        }
        return "";
    }
}