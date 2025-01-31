package modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private String nome;
    private List<Transacao> transacoes;

    public Categoria(String nome){
        this.nome = nome;
        this.transacoes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void adicionarTransacoes(Transacao transacao) {
        this.transacoes.add(transacao);
    }

}
