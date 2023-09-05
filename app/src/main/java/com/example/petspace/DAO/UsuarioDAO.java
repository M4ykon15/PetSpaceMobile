package com.example.petspace.DAO;

import com.example.petspace.Conexao;
import com.example.petspace.Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {
    private static final String SQL_VERIFICAR_LOGIN = "SELECT * FROM usuarios WHERE emaill = ? AND senha = ?";
    private static final String SQL_CADASTRAR_FUN = "INSERT INTO usuarios (nome, emaill, senha, telefone, cpf) VALUES (?, ?, ?, ?, ?)";

    public static Usuario verificaLogin(String usuario, String password) {

        try {
            Connection conn = Conexao.conectar();

            if (conn != null) {
                String sql = "SELECT * FROM usuarios WHERE emaill = ? AND senha = ?";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, usuario);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Usuario usu = new Usuario();
                    usu.setEmail(rs.getString(2));
                    usu.setSenha(rs.getString(3));

                    conn.close();
                    return usu;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int inserirUsuario(Usuario usuario) {
        int resposta = 0;
        Connection connection = null;
        PreparedStatement pst = null;

        try {
            // Obtém a conexão do método Conexao.conectar()
            connection = Conexao.conectar();

            // Prepara a consulta SQL usando PreparedStatement
            pst = connection.prepareStatement(SQL_CADASTRAR_FUN);
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getSenha());
            pst.setString(4, usuario.getTelefone());
            pst.setString(5, usuario.getCpf());

            // Executa a atualização e obtém o número de linhas afetadas
            resposta = pst.executeUpdate();
        } catch (SQLException e) {
            // Trate exceções SQL aqui, registre ou lide com elas adequadamente
            e.printStackTrace();
        } finally {
            // Libera recursos, fechando PreparedStatement e Connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Lide com exceções de fechamento, registre ou ignore
                e.printStackTrace();
            }
        }

        return resposta;
    }
}