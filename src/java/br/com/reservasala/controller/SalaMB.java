package br.com.reservasala.controller;

import br.com.reservasala.model.Sala;
import br.com.reservasala.model.persistencia.SalaDAOJPA;
import br.com.reservasala.model.persistencia.dao.SalaDAO;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
public class SalaMB {

    private Sala sala;
    private List<Sala> salas;

    @PostConstruct
    public void novo() {
        sala = new Sala();
    }

    public void salvar() {
        EntityManager manager = this.getManager();
        SalaDAO dao = new SalaDAOJPA(manager);
        dao.salvar(sala);
        this.salas = null;
        novo();
    }

    public void buscaPorId() {
        EntityManager manager = this.getManager();
        SalaDAO dao = new SalaDAOJPA(manager);
        this.sala = dao.buscarPorId(Sala.class, sala.getCodigo());
    }

    public void remove() {
        EntityManager manager = this.getManager();
        SalaDAO dao = new SalaDAOJPA(manager);
        dao.remover(Sala.class, sala.getCodigo());
        this.salas = null;
    }

    public List<Sala> getSalas() {
        if (salas != null) {
            EntityManager manager = this.getManager();
            SalaDAO dao = new SalaDAOJPA(manager);
            this.salas = dao.buscarTodos(Sala.class);
        }

        return salas;
    }

    private EntityManager getManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        return (EntityManager) request.getAttribute("EntityManager");
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
