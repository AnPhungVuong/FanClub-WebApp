/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.assignemnt2;
import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import javax.faces.context.FacesContext;
/**
 *
 * @author anvp0
 */ 
@Named 
@SessionScoped
public class localization implements Serializable {
       private static final long serialVersionUID = 1L;

    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();

                System.out.println("Setting language to: " + language);

    }
}

