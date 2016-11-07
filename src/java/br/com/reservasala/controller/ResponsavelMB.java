package br.com.reservasala.controller;

import br.com.reservasala.model.Responsavel;
import br.com.reservasala.model.persistencia.ResponsavelDAOJPA;
import br.com.reservasala.model.persistencia.dao.ResponsavelDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Antônio
 */
@ManagedBean
@ViewScoped
public class ResponsavelMB {

    private Responsavel responsavel;
    private List<Responsavel> responsaveis;

    @PostConstruct
    public void novo() {
        responsavel = new Responsavel();
    }

    public void salvar() {
        EntityManager manager = this.getManager();
        ResponsavelDAO dao = new ResponsavelDAOJPA(manager);
        dao.salvar(responsavel);
        this.responsaveis = null;
        novo();
    }

    public void buscaPorId() {
        EntityManager manager = this.getManager();
        ResponsavelDAO dao = new ResponsavelDAOJPA(manager);
        this.responsavel = dao.buscarPorId(Responsavel.class, responsavel.getCodigo());
    }

    public void remove() {
        EntityManager manager = this.getManager();
        ResponsavelDAO dao = new ResponsavelDAOJPA(manager);
        dao.remover(Responsavel.class, responsavel.getCodigo());
        this.responsaveis = null;
    }

    public List<Responsavel> getResponsaveis() {
        if (responsaveis == null) {
            EntityManager manager = this.getManager();
            ResponsavelDAO dao = new ResponsavelDAOJPA(manager);
            this.responsaveis = dao.buscarTodos(Responsavel.class);
        }

        return responsaveis;
    }

    private EntityManager getManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        return (EntityManager) request.getAttribute("EntityManager");
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
}
