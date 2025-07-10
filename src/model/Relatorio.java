package model;

import java.time.YearMonth;

public class Relatorio {
    private YearMonth mes;
    private int quantidade;

    public Relatorio(YearMonth mes, int quantidade) {
        this.mes = mes;
        this.quantidade = quantidade;
    }

    public YearMonth getMes() {
        return mes;
    }

    public void setMes(YearMonth mes) {
        this.mes = mes;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
