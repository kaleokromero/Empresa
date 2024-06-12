package com.empresa.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {
    private String nome;
    private LocalDate nascimento;

    public Pessoa(String nome, String nascimento) {
        this.nome = nome;
        this.nascimento = LocalDate.parse(nascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public String getNome() {
        return nome;
    }
    public LocalDate getNascimento() {
        return nascimento;
    }
    public String getNascimentoFormatado() {
        return nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
}