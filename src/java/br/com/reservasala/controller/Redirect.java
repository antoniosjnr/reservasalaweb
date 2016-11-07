package br.com.reservasala.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Redirect {

    public String irReserva(){
        return "reserva";
    }
    
    public String irSala(){
        return "sala";
    }
    
    public String irResponsavel(){
        return "responsavel";
    }
    
}
