package br.com.alura.hotel.modelo;

import java.time.LocalDate;

public class Hospede{

    Integer id;
    String nome;
    String sobrenome;
    LocalDate dataNascimento;
    String nacionalidade;
    String telefone;
    String codigoReserva;
    
    public Hospede(String nome, String sobrenome, LocalDate dataNascimento, String nacionalidade, String telefone,
            String codigoReserva) {
        super();
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.codigoReserva = codigoReserva;
    }

    public Hospede(Integer id, String nome, String sobrenome, LocalDate dataNascimento, String nacionalidade,
            String telefone, String codigoReserva) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.codigoReserva = codigoReserva;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setcodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

}