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
public class AlumnoDao {

    public void indroducirAlumno(String nombre, String contrasena, String correo) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Alumno n = new Alumno();

        n.setSNombre(nombre);
        n.setSContrasenha(contrasena);
        n.setSCorreo(correo);

        try {
            session.beginTransaction();
            session.save(n);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error del dao de Alumno");
            session.getTransaction().rollback();
        }
    }

}
