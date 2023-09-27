package br.com.alura.hotel.controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import br.com.alura.hotel.dao.ReservaDAO;
import br.com.alura.hotel.factory.ConnectionFactory;
import br.com.alura.hotel.modelo.Reserva;

public class ReservaController {
    
    private ReservaDAO reservaDAO;

    public ReservaController() {

        Connection connection = new ConnectionFactory().recuperarConexao();
        this.reservaDAO = new ReservaDAO(connection);


    }

    public void salvar(Reserva reserva) {
        this.reservaDAO.salvar(reserva);
    }

    public List<Reserva> listar(){
        return this.reservaDAO.listar();
    }

    public void atualizar(LocalDate dataEntrada, LocalDate dataSaida, BigDecimal valor,String formaPagamento, String codigo) {
        this.reservaDAO.atualizar(dataEntrada, dataSaida,valor, formaPagamento, codigo);
    }

    public void deletar(String codigo) {
        this.reservaDAO.deletar(codigo);
    }

    public List<Reserva> buscar(String codigo){
        return this.reservaDAO.buscar(codigo);
    }

}
