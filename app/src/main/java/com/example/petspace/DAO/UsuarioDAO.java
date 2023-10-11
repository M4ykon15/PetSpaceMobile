package com.example.petspace.DAO;

import com.example.petspace.Conexao;
import com.example.petspace.Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final String SQL_CADASTRAR_FUN = "INSERT INTO usuarios (nome, email, senha, telefone, cpf, nivel_acesso) VALUES (?, ?, ?, ?, ?, 1)";

    public static Usuario verificaLogin(String usuario, String password) {

        try {
            Connection conn = Conexao.conectar();

            if (conn != null) {
                String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";

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

    public static Usuario validarEmailCad(String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Conexao.conectar();
            if (conn != null) {
                String sql = "SELECT * FROM usuarios WHERE email = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, email); // Define o valor do parâmetro

                rs = ps.executeQuery();
                if (rs.next()) {
                    Usuario valiEmail = new Usuario();
                    valiEmail.setId(rs.getString(1));

                    return valiEmail;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static int getIdByEmail(String email) {
        String sql = "SELECT id FROM usuarios WHERE email = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static List<Usuario> getUsuario(int id){
        //lista os brechos
        List<Usuario> lista = null;
        //objeto de preparacao
        PreparedStatement pst;

        try {
            //cria a declaracao
            pst = Conexao.conectar().prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            pst.setInt(1 ,id);
            //executa o comando sql
            ResultSet res = pst.executeQuery();

            lista = new ArrayList<>();
            while(res.next()){
                lista.add(new Usuario(
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public static Usuario validarTelefoneUpdate(String telefone, int id) {
        try (Connection conn = Conexao.conectar()) {
            if (conn != null) {
                String sql = "SELECT * FROM usuarios WHERE telefone = ? AND id != ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, telefone);
                    ps.setInt(2, id);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            Usuario valiusu = new Usuario();
                            valiusu.setNome(rs.getString("nome"));
                            return valiusu;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Usuario validarEmailUpate(String usuario, int id) {
        try (Connection conn = Conexao.conectar()) {
            if (conn != null) {
                String sql = "SELECT * FROM usuarios WHERE email = ? AND id != ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, usuario);
                    ps.setInt(2, id);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            Usuario valiusu = new Usuario();
                            valiusu.setNome(rs.getString("nome"));
                            return valiusu;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            // Handle or log the exception here
            e.printStackTrace();
        }
        return null;
    }

    public static int atualizarUsuarios(int id,String nome, String email, String senha, String telefone, String cpf) {
        Connection conn = Conexao.conectar();
        PreparedStatement preparedStatement = null;

        try {
            // Consulta SQL de atualização
            String updateQuery = "UPDATE usuarios SET nome = ?, senha = ?, email = ?, telefone = ?, cpf = ? WHERE id = ?";

            // Cria o PreparedStatement com a consulta
            preparedStatement = conn.prepareStatement(updateQuery);

            // Define os parâmetros da consulta
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, senha);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, telefone);
            preparedStatement.setString(5, cpf);
            preparedStatement.setInt(6, id);

            // Executa a consulta de atualização
            int linhasAfetadas = preparedStatement.executeUpdate();

            return linhasAfetadas;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deletarUser(int id) {

        try {
            Connection conn = Conexao.conectar();

            // Query para deletar o usuário pelo ID
            String deleteQuery = "DELETE FROM usuarios WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}