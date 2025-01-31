package modelo;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Transacao {

    private int id;
    private double valor;
    private String descricao;
    private LocalDate data;
    private String tipo;

    public Transacao(int id, double valor, String descricao, LocalDate data, String tipo){
        this.id = id;
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
        this.tipo = tipo;
    }

    public Transacao(double valor, String descricao, LocalDate data, String tipo) {
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor(){
        return valor;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public LocalDate getData(){
        return data;
    }

    public void setData(LocalDate data){
        this.data = data;
    }


    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    @Override
    public String toString(){
        NumberFormat formatarMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format("Descrição: %s | Valor: %s | Data: %s | Tipo: %s", descricao, formatarMoeda.format(valor), data.format(formatarData), tipo);
    }



}
