package com.exemplo;

import java.util.UUID;

public class Aluno {
    private String id;
    private String nome;

    public Aluno(String nome) {
        this.id = UUID.randomUUID().toString(); // Gera um ID único
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome;
    }
}
