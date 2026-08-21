package br.edu.infnet.herick_bispo_blog_API.domain;

import java.time.LocalDateTime;

public abstract class Usuario implements Identificavel {

    private Long id;
    private String nome;
    private String email;
    private LocalDateTime dataCadastro;

    public Usuario(){}

    public Usuario(Long id, String nome, String email, LocalDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {

        return String.format("id= %d,  nome= %s, email= %s, dataCadastro= %s",
                id,
                nome,
                email,
                dataCadastro
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
