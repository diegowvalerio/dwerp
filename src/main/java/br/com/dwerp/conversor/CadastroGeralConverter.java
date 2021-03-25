package br.com.dwerp.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dwerp.entidade.CadastroGeral;


@FacesConverter(forClass = CadastroGeral.class,value="conversorCadastroGeral")
public class CadastroGeralConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (CadastroGeral) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof CadastroGeral) {
        	CadastroGeral entity= (CadastroGeral) value;
            if (entity != null && entity instanceof CadastroGeral && entity.getIdcadastrogeral() != null) {
                uiComponent.getAttributes().put( entity.getIdcadastrogeral().toString(), entity);
                return entity.getIdcadastrogeral().toString();
            }
        }
        return "";
    }
}