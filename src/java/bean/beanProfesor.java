/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     
   public beanProfesor(){
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
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
            p.setSContrasenha(PContrasenha);
            resultado = dao.update(p);
        }catch(Exception e){    
            return "modificar";
        }
        
        return "LoginProfesor";
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
