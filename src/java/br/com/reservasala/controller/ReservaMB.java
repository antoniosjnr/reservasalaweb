package br.com.reservasala.controller;

import br.com.reservasala.model.Reserva;
import br.com.reservasala.model.Responsavel;
import br.com.reservasala.model.Sala;
import br.com.reservasala.model.persistencia.ReservaDAOJPA;
import br.com.reservasala.model.persistencia.dao.ReservaDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@ViewScoped
public class ReservaMB {

    private Reserva reserva;
    private List<Reserva> reservas;
    private Sala sala;
    private Responsavel responsavel;

    @PostConstruct
    public void novo() {
        reserva = new Reserva();
    }

    public void salvar() {
        EntityManager manager = this.getManager();
        ReservaDAO dao = new ReservaDAOJPA(manager);
        dao.salvar(reserva);
        this.reservas = null;
        novo();
    }

    public void buscaPorId() {
        EntityManager manager = this.getManager();
        ReservaDAO dao = new ReservaDAOJPA(manager);
        this.reserva = dao.buscarPorId(Reserva.class, reserva.getCodigo());
    }

    public void remove() {
        EntityManager manager = this.getManager();
        ReservaDAO dao = new ReservaDAOJPA(manager);
        dao.remover(Reserva.class, reserva.getCodigo());
        this.reservas = null;
    }

    public List<Reserva> getReservas() {
        if (reservas == null) {
            EntityManager manager = this.getManager();
            ReservaDAO dao = new ReservaDAOJPA(manager);
            this.reservas = dao.buscarTodos(Reserva.class);
        }

        return reservas;
    }

    private EntityManager getManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        return (EntityManager) request.getAttribute("EntityManager");
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
    
    
    
    
}
