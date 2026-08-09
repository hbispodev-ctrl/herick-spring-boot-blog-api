package br.edu.infnet.herick_bispo_blog_API.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Artigo {

    private Long id;
    private String titulo;
    private String conteudo;
    private LocalDateTime dataPublicacao;
    private boolean publicado;
    private int visualizacoes = 0;
    private double avaliacao = 0.0;
    private int quantidadeAvaliacoesArtigo = 0;
    private double somaAvaliacoesArtigo = 0.0;

    private Autor autor;

    // Será refatorado com streams para usar apenas uma lista
    private List<Comentario> comentarios = new ArrayList<Comentario>();
    private List<Comentario> comentariosPendentes = new ArrayList<Comentario>();

    public Artigo(){}

    public Artigo(String titulo, String conteudo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

// ------------------------------------------------------------------------------------------------------------------------
public void receberComentario(Comentario comentario) {

    // Será refatorado com streams para usar apenas uma lista
    if (comentario.isAprovado()) {
        comentarios.add(comentario);
    } else {
        comentariosPendentes.add(comentario);
    }
}

    public void receberAvaliacao(double nota) {

        this.quantidadeAvaliacoesArtigo++;
        this.somaAvaliacoesArtigo += nota;
        this.avaliacao = this.somaAvaliacoesArtigo / this.quantidadeAvaliacoesArtigo;

        autor.avaliarReputacaoAutor(avaliacao);
    }

    public void incrementarVisualizacao(){
        this.visualizacoes++;
    }

// -----------------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {

        return String.format("Artigo {titulo= '%s', conteudo= '%s', dataPublicacao= %s,  autor= %s, comentarios= %d}",
                titulo,
                conteudo,
                dataPublicacao,
                autor.getNome(),
                comentarios.size()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    public int getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(int visualizacoes) {
        this.visualizacoes = visualizacoes;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Comentario> getComentariosPendentes() {
        return comentariosPendentes;
    }

    public void setComentariosPendentes(List<Comentario> comentariosPendentes) {
        this.comentariosPendentes = comentariosPendentes;
    }
}
