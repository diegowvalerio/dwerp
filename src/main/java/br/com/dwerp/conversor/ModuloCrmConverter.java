package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.ModuloCrm;


@FacesConverter(forClass = ModuloCrm.class,value="conversorModuloCrm")
public class ModuloCrmConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (ModuloCrm) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof ModuloCrm) {
        	ModuloCrm entity= (ModuloCrm) value;
            if (entity != null && entity instanceof ModuloCrm && entity.getIdmodulocrm() != null) {
                uiComponent.getAttributes().put( entity.getIdmodulocrm().toString(), entity);
                return entity.getIdmodulocrm().toString();
            }
        }
        return "";
    }
}