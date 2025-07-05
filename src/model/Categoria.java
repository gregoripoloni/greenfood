package model;

public class Categoria {
    private String nome;

    // Construtor
    public Categoria(String nome) {
        this.nome = nome;
    }

    // Construtor padrão (necessário caso queira instanciar sem parâmetros)
    public Categoria() {
    }

    // Getter
    public String getNome() {
        return nome;
    }

    // Setter
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Sobrescreve toString() para que o ComboBox exiba o nome
    @Override
    public String toString() {
        return nome;
    }
}
