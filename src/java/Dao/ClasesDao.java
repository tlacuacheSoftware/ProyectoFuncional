/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import org.hibernate.Session;

import logica.HibernateUtil;
import modelo.Alumno;

/**
 *
 * @author luis
 */
public class ClasesDao {


    public void indroducirAlumno(int idAlumno, String nombre, String contrasena, String correo) {

        Session session = confHibenate.getSessionFactory().getCurrentSession();

        Alumno n = new Alumno();
        n.setIdAlumno(idAlumno);
        n.setANombre(nombre);
        n.setAContrasenha(contrasena);
        n.setACorreo(correo);

        try {
            session.beginTransaction();
            session.save(n);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("error del dao");
            session.getTransaction().rollback();
        }
    }

    public void indroducirAlumno(String nombre, String contrasena, String correo) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Alumno n = new Alumno();

        n.setANombre(nombre);
        n.setAContrasenha(contrasena);
        n.setACorreo(correo);

        try {
            session.beginTransaction();
            session.save(n);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("error del dao");
            session.getTransaction().rollback();
        }
    }

}
