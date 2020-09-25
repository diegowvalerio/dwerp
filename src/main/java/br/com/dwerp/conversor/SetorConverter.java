package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.Setor;

@FacesConverter(forClass = Setor.class,value="conversorSetor")
public class SetorConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Setor) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Setor) {
        	Setor entity= (Setor) value;
            if (entity != null && entity instanceof Setor && entity.getIdsetor() != null) {
                uiComponent.getAttributes().put( entity.getIdsetor().toString(), entity);
                return entity.getIdsetor().toString();
            }
        }
        return "";
    }
}