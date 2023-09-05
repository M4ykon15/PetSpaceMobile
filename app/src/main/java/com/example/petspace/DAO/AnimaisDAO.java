package com.example.petspace.DAO;

import com.example.petspace.Conexao;
import com.example.petspace.Model.Animais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimaisDAO {
    public static List<Animais> pesquisarAnimais() {
        List<Animais> lista = new ArrayList<>();

        try (Connection connection = Conexao.conectar();
             PreparedStatement pst = connection.prepareStatement("SELECT * FROM animais ORDER BY id ASC");
             ResultSet res = pst.executeQuery()) {

            while (res.next()) {
                lista.add(new Animais(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        res.getString(7),
                        res.getBytes(8)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao pesquisar animais: " + e.getMessage(), e);
        }

        return lista;
    }

    public static List<Animais> infoAnimais(String id){
        //lista os brechos
        List<Animais> lista = null;
        //objeto de preparacao
        PreparedStatement pst;

        try {
            //cria a declaracao
            pst = Conexao.conectar().prepareStatement("select * from animais where id = ?");
            //executa o comando sql
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();

            lista = new ArrayList<>();
            while(res.next()){
                lista.add(new Animais(
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        res.getString(7),
                        res.getBytes(8)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    }
