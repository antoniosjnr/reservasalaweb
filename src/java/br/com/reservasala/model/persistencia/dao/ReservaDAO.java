package br.com.reservasala.model.persistencia.dao;

import br.com.reservasala.model.Reserva;
import java.util.Date;
import java.util.List;

public interface ReservaDAO extends DAO<Reserva,Integer>{
    
    public List<Reserva> buscaReservaPeriodo(Date dataReserva,String periodo, int idSala);  
    
}
