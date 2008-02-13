/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.fpuna.webapp.action;

import com.opensymphony.xwork2.Preparable;
import edu.fpuna.Constants;

/**
 * Action que permite establecer permito de edici�n
 * sobre objetos.
 * @author ghuttemann
 */
public class EditAccessAction extends BaseAction implements Preparable {
    /**
     * Constante que indica si se permite la edici�n
     * de alg�n objeto por el usuario logueado que
     * ejecuta el Action.
     */
    protected Boolean editAccess;
    
    /**
     * Constante que indica si se permite la eliminaci�n
     * de alg�n objeto por el usuario logueado que
     * ejecuta el Action.
     */
    protected Boolean deleteAccess;
        
    /**
     * Retorna si se tiene permiso o no de edici�n
     * @return true o false
     */
    public Boolean getEditAccess() {
        return editAccess;
    }
    
    /**
     * Retorna si se tiene permiso o no de eliminaci�n
     * @return true o false
     */
    public Boolean getDeleteAccess() {
        return deleteAccess;
    }
    
    /*
     * Por defecto, solo se da permiso de edici�n
     * si el usuario est� en el rol ADMIN_ROLE.
     * Si no es este el caso, debemos sobrescribir
     * este m�todo para sustituir el rol o agregar
     * otros roles.
     * Se pueden construir distintas reglas l�gicas
     * sobre los roles en el que deber�a estar el
     * usuario logueado. Por ejemplo:
     *    1. isUserInRole(admin) && isUserInRole(doctor)
     *    2. isUserInRole(doctor) || isUserInRole(paciente)
     */
    protected void setEditAccess() {
        if (getRequest().isUserInRole(Constants.ADMIN_ROLE))
            editAccess = true;
        else
            editAccess = false;
    }
    
    /*
     * Por defecto, solo se da permiso de eliminaci�n
     * si el usuario est� en el rol ADMIN_ROLE.
     * Si no es este el caso, debemos sobrescribir
     * este m�todo para sustituir el rol o agregar
     * otros roles.
     * Se pueden construir distintas reglas l�gicas
     * sobre los roles en el que deber�a estar el
     * usuario logueado. Por ejemplo:
     *    1. isUserInRole(admin) && isUserInRole(doctor)
     *    2. isUserInRole(doctor) || isUserInRole(paciente)
     */
    protected void setDeleteAccess() {
        if (getRequest().isUserInRole(Constants.ADMIN_ROLE))
            deleteAccess = true;
        else
            deleteAccess = false;
    }
    
    /*
     * Por defecto, este m�todo establece el permiso
     * de edici�n y eliminaci�n que tiene el usuario 
     * logueado.
     * Si se debe sobrescribir este m�todo y se desea
     * tener el mismo comportamiento, se debe agregar,
     * en la sobrescritura, el c�digo actual del m�todo.
     */
    public void prepare() throws Exception {
        setEditAccess();
        setDeleteAccess();
    }
}
