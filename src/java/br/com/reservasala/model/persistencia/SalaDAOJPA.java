package br.com.reservasala.model.persistencia;

import br.com.reservasala.model.Sala;
import br.com.reservasala.model.persistencia.dao.SalaDAO;
import javax.persistence.EntityManager;

public class SalaDAOJPA extends DAOJPA<Sala, Integer> implements SalaDAO {

    public SalaDAOJPA(EntityManager manager) {
        super(manager);
    }

}
