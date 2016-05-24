
package converter;

import dao.AreaDao;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Area;

/**
 *
 * @author esmeralda
 */
@FacesConverter("areaConverter")
public class AreaConverter implements Converter {

    private final AreaDao dao;
    private final List<Area> list;
    
    public AreaConverter() {
        dao = new AreaDao();
        list = dao.obtenerLista();
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Area t;
        for(int i = 0; i < list.size(); i++){
            t = list.get(i);
            if(t.getSArea().equals(value)){
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