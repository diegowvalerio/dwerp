package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.Unidade;

@FacesConverter(forClass = Unidade.class,value="conversorUnidade")
public class UnidadeConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Unidade) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Unidade) {
        	Unidade entity= (Unidade) value;
            if (entity != null && entity instanceof Unidade && entity.getIdunidade() != null) {
                uiComponent.getAttributes().put( entity.getIdunidade().toString(), entity);
                return entity.getIdunidade().toString();
            }
        }
        return "";
    }
}