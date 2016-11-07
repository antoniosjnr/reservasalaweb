package br.com.reservasala.model.persistencia;

import br.com.reservasala.model.Responsavel;
import br.com.reservasala.model.persistencia.dao.ResponsavelDAO;
import javax.persistence.EntityManager;

public class ResponsavelDAOJPA extends DAOJPA<Responsavel, Integer> implements ResponsavelDAO {
    
    public ResponsavelDAOJPA(EntityManager manager) {
        super(manager);
    }
    
}
