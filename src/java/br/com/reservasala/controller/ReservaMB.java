package br.com.reservasala.controller;

import br.com.reservasala.model.Reserva;
import br.com.reservasala.model.Responsavel;
import br.com.reservasala.model.Sala;
import br.com.reservasala.model.persistencia.ReservaDAOJPA;
import br.com.reservasala.model.persistencia.ResponsavelDAOJPA;
import br.com.reservasala.model.persistencia.SalaDAOJPA;
import br.com.reservasala.model.persistencia.dao.ReservaDAO;
import br.com.reservasala.model.persistencia.dao.ResponsavelDAO;
import br.com.reservasala.model.persistencia.dao.SalaDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

@ManagedBean
@ViewScoped
public class ReservaMB {

    private Reserva reserva;
    private List<Reserva> reservas;
    private int idSala;
    private int idResponsavel;

    @PostConstruct
    public void novo() {
        reserva = new Reserva();
    }

    public void salvar() {
        EntityManager manager = this.getManager();
        ReservaDAO dao = new ReservaDAOJPA(manager);
        SalaDAO dao2 = new SalaDAOJPA(manager);
        ResponsavelDAO dao3 = new ResponsavelDAOJPA(manager);

        Sala sala = dao2.buscarPorId(Sala.class, idSala);
        Responsavel responsavel = dao3.buscarPorId(Responsavel.class, idResponsavel);

        reserva.setSala(sala);
        reserva.setResponsavel(responsavel);

        if (dao.buscaReservaPeriodo(reserva.getData(), reserva.getPeriodo()) == null) {
            dao.salvar(reserva);
            this.reservas = null;
            novo();
        } else {

        }
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

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(int idResponsavel) {
        this.idResponsavel = idResponsavel;
    }
}
