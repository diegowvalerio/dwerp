package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.SubGrupo;

@FacesConverter(forClass = SubGrupo.class,value="conversorSubGrupo")
public class SubGrupoConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (SubGrupo) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof SubGrupo) {
        	SubGrupo entity= (SubGrupo) value;
            if (entity != null && entity instanceof SubGrupo && entity.getIdsubgrupo() != null) {
                uiComponent.getAttributes().put( entity.getIdsubgrupo().toString(), entity);
                return entity.getIdsubgrupo().toString();
            }
        }
        return "";
    }
}