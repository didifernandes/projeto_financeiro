package servico;

import dao.TransacaoDAO;
import modelo.Transacao;

import java.util.ArrayList;
import java.util.List;

public class ServicoTransacao {

    private final TransacaoDAO transacaoDAO;

    public ServicoTransacao(TransacaoDAO transacaoDAO) {
        this.transacaoDAO = transacaoDAO;
    }

    public void adicionarTransacao(Transacao transacao){
        transacaoDAO.adicionar(transacao);
    }

    public List<Transacao> listarTransacoes(){
        return transacaoDAO.listar();
    }


    public Transacao buscarPorId(int id) {
        return transacaoDAO.buscarPorId(id);
    }

    public void atualizarTransacao(Transacao transacao) {
        transacaoDAO.atualizar(transacao);
    }

    public boolean deletarTransacao(int id) {
        return transacaoDAO.deletar(id);
    }


    public double calcularTotal(){
        double saldo = 0;
        for(Transacao t: listarTransacoes()){
            if (t.getTipo().equalsIgnoreCase("receita")){
                saldo += t.getValor();
            } else if (t.getTipo().equalsIgnoreCase("despesa")) {
                saldo -= t.getValor();
            }
        }
        return saldo;
    }

}
