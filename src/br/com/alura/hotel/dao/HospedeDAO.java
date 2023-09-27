package br.com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import br.com.alura.hotel.modelo.Hospede;


public class HospedeDAO {
    
    private Connection  connection;

    public HospedeDAO(Connection connection) {
        this.connection = connection;
    }


    public void salvar(Hospede hospede) {
        try {
            String sql = "INSERT INTO HOSPEDES (NOME, SOBRENOME, DATANASCIMENTO, NACIONALIDADE, TELEFONE, CodigoReserva) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, hospede.getNome());
                pstm.setString(2, hospede.getSobrenome());
                pstm.setDate(3, java.sql.Date.valueOf(hospede.getDataNascimento()));
                pstm.setString(4, hospede.getNacionalidade());
                pstm.setString(5, hospede.getTelefone());
                pstm.setString(6, hospede.getCodigoReserva());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        hospede.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    




    public List<Hospede> listar() {
        List<Hospede> hospedes = new ArrayList<Hospede>();
        try {

            String sql = "SELECT * FROM HOSPEDES";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                trasformarResultSetEmHospede(hospedes, pstm);
            }
            return hospedes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Integer id) {
        try {
            String sql = "DELETE FROM HOSPEDES WHERE ID = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, id);
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
 
    public void alterar(String nome, String sobrenome, LocalDate dataNascimento, String nacionalidade, String telefone, Integer id) {
        try (PreparedStatement stm = connection
                .prepareStatement("UPDATE HOSPEDES H SET H.NOME = ?, H.SOBRENOME = ? , H.DATANASCIMENTO = ?, H.NACIONALIDADE = ?, H.TELEFONE = ? WHERE ID = ?")) {
            stm.setString(1, nome);
            stm.setString(2, sobrenome);
            stm.setDate(3, java.sql.Date.valueOf(dataNascimento));
            stm.setString(4, nacionalidade);
            stm.setString(5, telefone);
            stm.setInt(6, id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Hospede> buscar(Integer id){
        List<Hospede> hospedes = new ArrayList<Hospede>();
        try {
            
            String sql = "SELECT * FROM HOSPEDES WHERE ID = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, id);
                pstm.execute();

                trasformarResultSetEmHospede(hospedes, pstm);
            }
            return hospedes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void trasformarResultSetEmHospede(List<Hospede> hospedes, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Hospede hospede = new Hospede(
                      
                        rst.getInt(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getDate(4).toLocalDate(),
                        rst.getString(5),
                        rst.getString(6),
                        rst.getString(7)
                );  

                hospedes.add(hospede);
            }
        }
    }


    public Boolean buscarReserva(String codigo) {
        try {
            String sql = "SELECT * FROM HOSPEDES WHERE CODIGORESERVA = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, codigo);
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
}
