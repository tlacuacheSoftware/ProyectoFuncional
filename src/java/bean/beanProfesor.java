package bean;
import Dao.ProfesorDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Profesor;

/**
 *
 * @author raul__000
 */
@ManagedBean
@RequestScoped
public class beanProfesor {
   private Profesor profesor=new Profesor();
   private final HttpServletRequest httpServletRequest;
   private final FacesContext faceContext;
   private FacesMessage message;
   private int idProfesor;
   private String PNombre;
   private String PContrasenha;
   private String PCorreo;
   private final ProfesorDao dao = new ProfesorDao();
   private int id;
   private String Nombre;
 
   
   public beanProfesor(){
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        this.Nombre = httpServletRequest.getSession().getAttribute("sessionSNombre").toString();
    }
    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
       public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

   public String modificarProfesor(){
        if (httpServletRequest.getSession().getAttribute("Id") != null) {
         id=(int)httpServletRequest.getSession().getAttribute("Id");
        }else{
            return "Modificar";
        }
        profesor = dao.getByID(id);     
        boolean resultado;
        Profesor p;
        p = new Profesor();
        p.setIdProfesor(profesor.getIdProfesor());
        httpServletRequest.getSession().setAttribute("sessionSNombre", PNombre);
        try{
            checkNombre();
            checkCorreo();
            checkContrasenha();
            p.setSNombre(PNombre);            
            p.setSCorreo(PCorreo);
            p.setSContrasenha(PContrasenha);
            dao.update(p);
            this.Nombre = PNombre;
        }catch(Exception e){    
            return "Modificar";
        }
        
        return "Exito";
    }
    
    private void checkNombre(){
        if(PNombre == null || PNombre.equals("")){
            throw new NullPointerException("El nombre esta vacio.");
        }
    }
    
    private void checkCorreo(){
        if(PCorreo == null || PCorreo.equals("")){
            throw new NullPointerException("El correo esta vacio.");
        }
    }

    private void checkContrasenha(){
        if(PContrasenha == null || PContrasenha.equals("")){
            throw new NullPointerException("La contraseña esta vacia.");
        }
    }
    
    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getPNombre() {
        return PNombre;
    }

    public void setPNombre(String PNombre) {
        this.PNombre = PNombre;
    }

    public String getPContrasenha() {
        return PContrasenha;
    }

    public void setPContrasenha(String PContrasenha) {
        this.PContrasenha = PContrasenha;
    }

    public String getPCorreo() {
        return PCorreo;
    }

    public void setPCorreo(String PCorreo) {
        this.PCorreo = PCorreo;
    }
    
}
