package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.SituacaoTributariaIPI;

@FacesConverter(forClass = SituacaoTributariaIPI.class,value="conversorSituacaoTributariaIPI")
public class SituacaoTributariaIPIConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (SituacaoTributariaIPI) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof SituacaoTributariaIPI) {
        	SituacaoTributariaIPI entity= (SituacaoTributariaIPI) value;
            if (entity != null && entity instanceof SituacaoTributariaIPI && entity.getIdsituacaotributariaipi() != null) {
                uiComponent.getAttributes().put( entity.getIdsituacaotributariaipi().toString(), entity);
                return entity.getIdsituacaotributariaipi().toString();
            }
        }
        return "";
    }
}