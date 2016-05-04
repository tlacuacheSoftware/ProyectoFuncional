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

   public String modificarNombreProfesor(){
        if (httpServletRequest.getSession().getAttribute("Id") != null) {
         id=(int)httpServletRequest.getSession().getAttribute("Id");
        }else{
            return "Modificar";
        }
        profesor = dao.getByID(id);     
        Profesor p;
        p = new Profesor();
        p.setIdProfesor(profesor.getIdProfesor());
        httpServletRequest.getSession().setAttribute("sessionSNombre", PNombre);
        this.Nombre=PNombre;
        try{
            String cadena1 = checkNombre();
            System.out.println(cadena1);
            if("Exito".equals(cadena1)){
            p.setSNombre(PNombre);
            p.setSCorreo(profesor.getSCorreo());
            p.setSContrasenha(profesor.getSContrasenha());
            dao.update(p);
            }
        }catch(Exception e){    
        }
        message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Información modificada correctamente", null);
        faceContext.addMessage(null, message);
        return "Modificar";
    }
   public String modificarCorreoProfesor(){
        if (httpServletRequest.getSession().getAttribute("Id") != null) {
         id=(int)httpServletRequest.getSession().getAttribute("Id");
        }else{
            return "Modificar";
        }
        profesor = dao.getByID(id);     
        Profesor p;
        p = new Profesor();
        p.setIdProfesor(profesor.getIdProfesor());
        try{
            String cadena2 = checkCorreo();
            System.out.println(cadena2 );
            if("Exito".equals(cadena2) ){
            p.setSNombre(profesor.getSNombre());
            p.setSCorreo(PCorreo);
            p.setSContrasenha(profesor.getSContrasenha());
            dao.update(p);
            }
        }catch(Exception e){    
            return "Modificar";
        }
        message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Información modificada correctamente", null);
        faceContext.addMessage(null, message);
        return "Modificar";
    }
    public String modificarContraseñaProfesor(){
        if (httpServletRequest.getSession().getAttribute("Id") != null) {
         id=(int)httpServletRequest.getSession().getAttribute("Id");
        }else{
            return "Modificar";
        }
        profesor = dao.getByID(id);     
        Profesor p;
        p = new Profesor();
        p.setIdProfesor(profesor.getIdProfesor());
        httpServletRequest.getSession().setAttribute("sessionSNombre", PNombre);
        try{
            String cadena3 =checkContrasenha();
            System.out.println(cadena3);
            if("Exito".equals(cadena3)){
            p.setSNombre(profesor.getSNombre());
            p.setSCorreo(profesor.getSCorreo());
            p.setSContrasenha(PContrasenha);
            dao.update(p);
            }else{
                return "Modificar";
            }
        }catch(Exception e){    
            return "Modificar";
        }
        message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Información modificada correctamente", null);
        faceContext.addMessage(null, message);
        return "Modificar";
    }
    private String checkNombre(){
        if(PNombre == null || PNombre.equals("")){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nombre no válido", null);
            faceContext.addMessage(null, message);
            return "Modificar";
        }else{
            if(!revisaNombre()){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"El nombre sólo puede contener caracteres", null);
                faceContext.addMessage(null, message);
                return "Modificar";
            }else{
                if(PNombre.length()<4 || PNombre.length()>20){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"El nombre debe tener entre 4 y 20 caracteres", null);
                    faceContext.addMessage(null, message);
                    return "Modificar";
                }else{
                    return "Exito";
                }
            }
        }
        
    }
    
    private boolean revisaNombre(){
        boolean resultado = true;
        String alfa = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        for(int i = 0; i < PNombre.length(); i++){
            String temp = ""+PNombre.charAt(i);
            if(!alfa.contains(temp)){
                resultado = false;
            }
        }
        return resultado;
    }
    
    private String checkCorreo(){
        if(PCorreo == null || PCorreo.equals("")){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Correo no válido", null);
            faceContext.addMessage(null, message);
            return "Modificar";
            }else{
                if(!revisaCorreo()){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"El correo debe de ser del dominio de Ciencias", null);
                    faceContext.addMessage(null, message);
                    return "Modificar";
                }else{
                    return "Exito";
                }
        }
    }
    
    private boolean revisaCorreo(){
        boolean resultado = true;
        String prueba = "@ciencias.unam.mx";
        if(!PCorreo.contains(prueba)){
            resultado = false;
        }
        return resultado;
    }

    private String checkContrasenha(){
        if(PContrasenha == null || PContrasenha.equals("")){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Contraseña no válida", null);
            faceContext.addMessage(null, message);
        return "Modificar";
        }else{
            if(PContrasenha.length() < 6 || PContrasenha.length() > 20){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La contraseña debe tener al menos 6 caracteres y máximo 20", null);
                faceContext.addMessage(null, message);
                return "Modificar"   ;
            }else{
            return "Exito";
            }
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