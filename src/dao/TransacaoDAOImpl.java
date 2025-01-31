package dao;

import connectionDatabase.ConexaoBanco;
import modelo.Transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAOImpl implements TransacaoDAO {

    @Override
    public Transacao buscarPorId(int id) {
        String sql = "SELECT * FROM transacoes WHERE id = ?";
        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Transacao(
                        rs.getInt("id"),
                        rs.getDouble("valor"),
                        rs.getString("descricao"),
                        rs.getDate("data").toLocalDate(),
                        rs.getString("tipo")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar transação por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void adicionar(Transacao transacao) {
        String sql = "INSERT INTO transacoes (descricao, valor, data, tipo) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, transacao.getDescricao());
            stmt.setDouble(2, transacao.getValor());
            stmt.setDate(3, java.sql.Date.valueOf(transacao.getData()));
            stmt.setString(4, transacao.getTipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar transação: " + e.getMessage());
        }
    }

    @Override
    public List<Transacao> listar() {
        String sql = "SELECT * FROM transacoes";
        List<Transacao> transacoes = new ArrayList<>();

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transacao transacao = new Transacao(
                        rs.getDouble("valor"),
                        rs.getString("descricao"),
                        rs.getDate("data").toLocalDate(),
                        rs.getString("tipo")
                );
                transacao.setId(rs.getInt("id"));
                transacoes.add(transacao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar transações: " + e.getMessage());
        }
        return transacoes;
    }

    @Override
    public void alterar(Transacao transacao) {

    }

    @Override
    public void atualizar(Transacao transacao) {
        String sql = "UPDATE transacoes SET descricao = ?, valor = ?, data = ?, tipo = ? WHERE id = ?";

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, transacao.getDescricao());
            stmt.setDouble(2, transacao.getValor());
            stmt.setDate(3, java.sql.Date.valueOf(transacao.getData()));
            stmt.setString(4, transacao.getTipo());
            stmt.setInt(5, transacao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar transação: " + e.getMessage());
        }
    }

    @Override
    public void excluir(int id) {

    }

    @Override
    public boolean deletar(int id) {
        String sql = "DELETE FROM transacoes WHERE id = ?";
        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar transação: " + e.getMessage());
        }
        return false;
    }
}
