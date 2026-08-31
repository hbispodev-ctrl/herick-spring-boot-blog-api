package br.edu.infnet.herick_bispo_blog_API.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
public class Comentario implements Identificavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String texto;
    private LocalDateTime dataHora;
    private boolean aprovado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario autorComentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artigo_id")
    @JsonBackReference(value = "artigo-comentario")
    private Artigo artigo;

    public Comentario(){}

    public Comentario(String texto){
        this.texto = texto;
    }

    public Comentario(Long id, String texto) {
        this(texto);
        this.id = id;
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

    public Artigo getArtigo() {
        return artigo;
    }

    public void setArtigo(Artigo artigo) {
        this.artigo = artigo;
    }
}
