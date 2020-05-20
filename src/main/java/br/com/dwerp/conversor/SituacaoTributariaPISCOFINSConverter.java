package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.SituacaoTributariaPISCOFINS;

@FacesConverter(forClass = SituacaoTributariaPISCOFINS.class,value="conversorSituacaoTributariaPISCOFINS")
public class SituacaoTributariaPISCOFINSConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (SituacaoTributariaPISCOFINS) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof SituacaoTributariaPISCOFINS) {
        	SituacaoTributariaPISCOFINS entity= (SituacaoTributariaPISCOFINS) value;
            if (entity != null && entity instanceof SituacaoTributariaPISCOFINS && entity.getIdsituacaotributariapiscofins() != null) {
                uiComponent.getAttributes().put( entity.getIdsituacaotributariapiscofins().toString(), entity);
                return entity.getIdsituacaotributariapiscofins().toString();
            }
        }
        return "";
    }
}