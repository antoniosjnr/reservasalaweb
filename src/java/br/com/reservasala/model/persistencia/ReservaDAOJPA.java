package br.com.reservasala.model.persistencia;

import br.com.reservasala.model.Reserva;
import br.com.reservasala.model.persistencia.dao.ReservaDAO;
import javax.persistence.EntityManager;

public class ReservaDAOJPA extends DAOJPA<Reserva, Integer> implements ReservaDAO {
    
    public ReservaDAOJPA(EntityManager manager) {
        super(manager);
    }
    
}
