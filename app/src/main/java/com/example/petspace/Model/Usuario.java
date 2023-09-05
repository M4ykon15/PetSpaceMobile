package com.example.petspace.Model;

public class Usuario {

    private String Id;
    private String Nome;
    private String Email;
    private String Senha;
    private String Telefone;
    private String Cpf;

    public Usuario(String nome, String email, String senha, String telefone, String cpf) {
        Nome = nome;
        Email = email;
        Senha = senha;
        Telefone = telefone;
        Cpf = cpf;
    }

    public Usuario() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }
}
