package br.com.reservasala.model.persistencia;

import br.com.reservasala.filtros.JPAFilter;
import br.com.reservasala.model.Reserva;
import br.com.reservasala.model.persistencia.dao.ReservaDAO;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ReservaDAOJPA extends DAOJPA<Reserva, Integer> implements ReservaDAO {
    
    private EntityManager manager;
    
    public ReservaDAOJPA(EntityManager manager) {
        super(manager);
        this.manager = manager;
    }    
    public List<Reserva> buscaReservaPeriodo(Date dataReserva,String periodo){        
        
        Query q = this.manager.createQuery
               ("select r from Reserva r where c.data = :data and periodo = :periodo", Reserva.class);
       q.setParameter("data",dataReserva);
       q.setParameter("periodo",periodo);       
       return q.getResultList();
    }
    
}
