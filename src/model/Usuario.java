package model;

import utils.SHA256;

public abstract class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String tipo;

    protected Usuario(int id, String nome, String email, String senha, String telefone, String tipo) {
        this.setId(id);
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setTelefone(telefone);
        this.setTipo(tipo);
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = SHA256.aplicar(senha);
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void exibirInformacoes() {
        System.out.println("Usuário");
        System.out.println("Id: " + this.getId());
        System.out.println("Nome: " + this.getNome());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Senha: " + this.getSenha());
        System.out.println("Telefone: " + this.getTelefone());
        System.out.println("Tipo: " + this.getTipo());
    }
}
