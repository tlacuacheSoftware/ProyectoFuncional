/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;
import dao.ProfesorDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
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
   private String PContrasenha1;
   private String PContrasenha2;
   private String PCorreo;
   private final ProfesorDao dao = new ProfesorDao();
     
   private String messa;
   
   public beanProfesor(){
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        messa = "";
    }
    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
    public String login(){
        ProfesorDao prof=new ProfesorDao();
        Profesor p = null;
        String resultado;
            p=prof.Verificar(this.profesor);
            if(p!=null){
                
                httpServletRequest.getSession().setAttribute("idUsuario", p.getIdProfesor());
                httpServletRequest.getSession().setAttribute("sessionSNombre", this.profesor.getSCorreo());
                resultado="InicioProfesor";
            }else{
                resultado="Profesor";
            }

        return resultado;
    }
   
   private void defineProfesor(){
        int id;
        id = (int) httpServletRequest.getSession().getAttribute("idUsuario");
        System.out.println("El id es " + id);
        idProfesor = id;
        profesor = dao.getByID(idProfesor);
    }
   
   public String modificarProfesor(){
        defineProfesor();      
        boolean resultado;
        Profesor p;
        p = new Profesor();
        p.setIdProfesor(profesor.getIdProfesor());
        
        try{
            checkNombre();
            checkCorreo();
            checkContrasenha();
            p.setSNombre(PNombre);
            p.setSCorreo(PCorreo);
            p.setSContrasenha(PContrasenha1);
            resultado = dao.update(p);
        }catch(Exception e){  
            messa = cutException(e.toString());
            return "Modificar";
        }
        messa = "";
        return "Profesor";
    }
   
    private String cutException(String e){
        int i = e.indexOf(":");
        return e.substring(i+1);
    }
   
    private void checkNombre(){
        String s;
        String check = "QWERTYUIOPASDFGHJKLÑZXCVBNMqwertyuiopasdfghjklñzxcvbnm";
        if(PNombre == null || PNombre.equals("")){
            throw new NullPointerException("El nombre esta vacio.");
        }
        for(int i = 0; i < PNombre.length(); i++){
            s = PNombre.substring(i, i+1);
            if(!check.contains(s)){
                throw new NullPointerException("El nombre contiene caracteres invalidos. Solo letras.");
            }
        }
    }
    
    private void checkCorreo(){
        String s;
        String check = "QWERTYUIOPASDFGHJKLÑZXCVBNMqwertyuiopasdfghjklñzxcvbnm1234567890.-_@";
        if(PCorreo == null || PCorreo.equals("")){
            throw new NullPointerException("El correo esta vacio.");
        }
        for(int i = 0; i < PCorreo.length(); i++){
            s = PCorreo.substring(i, i+1);
            if(!check.contains(s)){
                throw new NullPointerException("El correo contiene caracteres invalidos. Solo alfanumericos y . - _ @");
            }
        }
    }

    private void checkContrasenha(){
        String s;
        String check = "QWERTYUIOPASDFGHJKLÑZXCVBNMqwertyuiopasdfghjklñzxcvbnm1234567890";
        if(PContrasenha1 == null || PContrasenha1.equals("")){
            throw new NullPointerException("La contraseña esta vacia.");
        }
        if(PContrasenha2 == null || PContrasenha2.equals("")){
            throw new NullPointerException("La contraseña esta vacia.");
        }
        if(!PContrasenha1.equals(PContrasenha2)){
            throw new NullPointerException("Las contraseña no son iguales.");
        }
        for(int i = 0; i < PContrasenha1.length(); i++){
            s = PContrasenha1.substring(i, i+1);
            if(!check.contains(s)){
                throw new NullPointerException("La contraseña contiene caracteres invalidos. Solo alfanumericos.");
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

    public String getPContrasenha1() {
        return PContrasenha1;
    }

    public void setPContrasenha1(String PContrasenha) {
        this.PContrasenha1 = PContrasenha;
    }

    public String getPContrasenha2() {
        return PContrasenha2;
    }

    public void setPContrasenha2(String PContrasenha2) {
        this.PContrasenha2 = PContrasenha2;
    }

    public String getPCorreo() {
        return PCorreo;
    }

    public void setPCorreo(String PCorreo) {
        this.PCorreo = PCorreo;
    }

    public String getMessa() {
        return messa;
    }

    public void setMessa(String messa) {
        this.messa = messa;
    }
    
}
