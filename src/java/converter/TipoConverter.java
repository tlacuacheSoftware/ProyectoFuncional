
package converter;

import dao.TipoDao;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Tipo;

/**
 *
 * @author esmeralda
 */
@FacesConverter("tipoConverter")
public class TipoConverter implements Converter {

    private final TipoDao dao;
    private final List<Tipo> list;
    
    public TipoConverter() {
        dao = new TipoDao();
        list = dao.obtenerLista();
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Tipo t;
        for(int i = 0; i < list.size(); i++){
            t = list.get(i);
            if(t.getSTipo().equals(value)){
                return t;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return null;
        }else{
            return value.toString();
        }
    }
    
}
