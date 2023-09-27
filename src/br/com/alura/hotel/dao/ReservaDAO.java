package br.com.alura.hotel.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import br.com.alura.hotel.modelo.Reserva;

public class ReservaDAO {
    
    private Connection  connection;

    public ReservaDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void salvar(Reserva reserva){
        try {   
            String sql = "INSERT INTO RESERVAS (Codigo, DataEntrada, DataSaida, Valor,FormaPagamento) VALUES (?,?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, reserva.getCodigo());
                pstm.setDate(2, java.sql.Date.valueOf(reserva.getDataEntrada()));
                pstm.setDate(3, java.sql.Date.valueOf(reserva.getDataSaida()));
                pstm.setBigDecimal(4, reserva.getValor());
                pstm.setString(5, reserva.getFormaPagamento().toString());

                pstm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reserva> listar(){
        List<Reserva> reservas = new ArrayList<Reserva>();
        try {
            
            String sql = "SELECT * FROM RESERVAS";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                trasformarResultSetEmReserva(reservas, pstm);
            }
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reserva> buscar(String id){
        List<Reserva> reservas = new ArrayList<Reserva>();
        try {
            
            String sql = "SELECT * FROM RESERVAS WHERE CODIGO = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, id);
                pstm.execute();

                trasformarResultSetEmReserva(reservas, pstm);
            }
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer pegaCodigoReservaRecente(){
        Integer id = 0;
        try {

            String sql = "SELECT CODIGO FROM reservas ORDER BY CODIGO DESC LIMIT 1";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        id = rst.getInt(1);
                    }
                }
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(String codigo){
        try {
            String sql = "DELETE FROM RESERVAS WHERE CODIGO = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, codigo);
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

  
    public void atualizar(LocalDate dataEntrada, LocalDate dataSaida, BigDecimal valor, String formaPagamento, String codigo){
        try {
            String sql = "UPDATE RESERVAS SET DataEntrada = ?, DataSaida = ?, Valor = ?, FormaPagamento = ? WHERE CODIGO = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
           
                pstm.setDate(1, java.sql.Date.valueOf(dataEntrada));
                pstm.setDate(2, java.sql.Date.valueOf(dataSaida));
                pstm.setBigDecimal(3, valor);
                pstm.setString(4, formaPagamento);
                pstm.setString(5, codigo);
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void trasformarResultSetEmReserva(List<Reserva> reservas, PreparedStatement pstm) throws SQLException{
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Reserva reserva = new Reserva(
                        rst.getInt(1),
                        rst.getString(2),
                        rst.getDate(3).toLocalDate(),
                        rst.getDate(4).toLocalDate(),
                        rst.getBigDecimal(5),
                        rst.getString(6)

                );
                    
                reservas.add(reserva);
            }
        }
    }

    
}
