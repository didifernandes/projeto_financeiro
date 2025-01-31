package dao;

import modelo.Transacao;

import java.util.List;

public interface TransacaoDAO {

    Transacao buscarPorId(int id);

    void adicionar(Transacao transacao);
    List<Transacao> listar();
    void alterar(Transacao transacao);

    void atualizar(Transacao transacao);

    void excluir(int id);

    boolean deletar(int id);
}
