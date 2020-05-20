package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.SituacaoTributaria;


@FacesConverter(forClass = SituacaoTributaria.class,value="conversorSituacaoTributaria")
public class SituacaoTributariaConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (SituacaoTributaria) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof SituacaoTributaria) {
        	SituacaoTributaria entity= (SituacaoTributaria) value;
            if (entity != null && entity instanceof SituacaoTributaria && entity.getIdsituacaotributaria() != null) {
                uiComponent.getAttributes().put( entity.getIdsituacaotributaria().toString(), entity);
                return entity.getIdsituacaotributaria().toString();
            }
        }
        return "";
    }
}