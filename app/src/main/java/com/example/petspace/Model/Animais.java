package com.example.petspace.Model;

public class Animais {

    private int Id;
    private String Nome;
    private String Sexo;
    private String Especie;
    private String Raca;
    private String Idade;
    private String Porte;
    private byte[] foto;

    public Animais(String nome, String sexo, String especie, String raca, String idade, String porte, byte[] foto) {
        Nome = nome;
        Sexo = sexo;
        Especie = especie;
        Raca = raca;
        Idade = idade;
        Porte = porte;
        this.foto = foto;
    }

    public Animais(int id, String nome, String sexo, String especie, String raca, String idade, String porte, byte[] foto) {
        Id = id;
        Nome = nome;
        Sexo = sexo;
        Especie = especie;
        Raca = raca;
        Idade = idade;
        Porte = porte;
        this.foto = foto;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getEspecie() {
        return Especie;
    }

    public void setEspecie(String especie) {
        Especie = especie;
    }

    public String getRaca() {
        return Raca;
    }

    public void setRaca(String raca) {
        Raca = raca;
    }

    public String getIdade() {
        return Idade;
    }

    public void setIdade(String idade) {
        Idade = idade;
    }

    public String getPorte() {
        return Porte;
    }

    public void setPorte(String porte) {
        Porte = porte;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
