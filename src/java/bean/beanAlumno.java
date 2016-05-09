package bean;

import Dao.AlumnoDao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Alumno;
import modelo.Profesor;
import Dao.ProfesorDao;
/**
 *
 * @author raul__000
 */
@ManagedBean
@RequestScoped
public class beanAlumno {

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
   String cadena;
   String s;
   String SCorreo;
   ProfesorDao dao=new ProfesorDao();
   private Alumno alumno=new Alumno();
   private final HttpServletRequest httpServletRequest;
   private final FacesContext faceContext;
    
   public Alumno getAlumno() {
        return alumno;
   }
    
    public beanAlumno(){
        cadena=null;
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

   public String login(){
        AlumnoDao prof=new AlumnoDao();
        Alumno p = null;
        String resultado;
            p=prof.Verificar(this.alumno);
            if(p!=null){
                httpServletRequest.getSession().setAttribute("sessionSNombre",p.getSNombre());
                httpServletRequest.getSession().setAttribute("idUsuario", p.getIdAlumno());
                  resultado="InicioAlumno";     
            }else{
                resultado="Alumno";
            }
        
        return resultado;
    }
   public String Busqueda(String s){
       boolean bandera=true;
       String cadena="";
       int Nid=1;
       while(bandera){
       if (dao.getByID(Nid)==null) {
           bandera=false;
            return cadena;
        }else{  
            Profesor p = dao.getByID(Nid);   
           if(p.getSNombre().toUpperCase().equals(s.toUpperCase()) || p.getSNombre().toUpperCase().contains(s.toUpperCase())){
               cadena+=" "+p.getSNombre() +" "+p.getSCorreo()+"\n";
           }
       }
        Nid++;
       }
       return cadena;
   }
   public String BusquedaPorNombre(){
       cadena=Busqueda(s);
       if(cadena.equals("")){
           cadena="No hay resultados";
       }
       return "Mostrar";
   }
}