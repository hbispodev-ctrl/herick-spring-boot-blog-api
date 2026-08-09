package br.edu.infnet.herick_bispo_blog_API.domain.model;

import java.time.LocalDateTime;

public class Comentario {

    private Long id;
    private String texto;
    private LocalDateTime dataHora;
    private boolean aprovado;

    private Usuario autorComentario;

    public Comentario(){}

    public Comentario(String texto) {

        this.texto = texto;
    }

    @Override
    public String toString() {

        return String.format("Comentário {texto= '%s', autorComentario= %s, dataHora= %s}",
                texto,
                autorComentario.getNome(),
                dataHora
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public Usuario getAutorComentario() {
        return autorComentario;
    }

    public void setAutorComentario(Usuario autorComentario) {
        this.autorComentario = autorComentario;
    }
}
