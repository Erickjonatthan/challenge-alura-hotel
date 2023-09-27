package br.com.alura.hotel.controller;

import java.awt.Component;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import br.com.alura.hotel.dao.HospedeDAO;
import br.com.alura.hotel.factory.ConnectionFactory;
import br.com.alura.hotel.modelo.Hospede;

public class HospedeController {
    
    private HospedeDAO hospedeDAO; 

    public HospedeController() {
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.hospedeDAO = new HospedeDAO(connection);
    }
    
    public void salvar(Hospede hospede) {
        this.hospedeDAO.salvar(hospede);
    }

    public List<Hospede> listar(){
        return this.hospedeDAO.listar();
    }

    public void alterar( String nome, String sobrenome, LocalDate dataNascimento, String nacionalidade, String telefone, Integer id) {
        this.hospedeDAO.alterar(nome, sobrenome, dataNascimento, nacionalidade, telefone, id);
    }

    public void deletar(Integer id) {
        this.hospedeDAO.deletar(id);
    }

    public List<Hospede> buscar(Integer id){
        return this.hospedeDAO.buscar(id);
    }

    public Boolean buscarReserva(String codigo) {
        return this.hospedeDAO.buscarReserva(codigo);
    }

  

}
