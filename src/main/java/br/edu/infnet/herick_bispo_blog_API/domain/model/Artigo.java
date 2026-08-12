package br.edu.infnet.herick_bispo_blog_API.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Artigo implements Identificavel{

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

    private List<Comentario> comentarios = new ArrayList<Comentario>();

    public Artigo(){}

    public Artigo(Long id, String titulo, String conteudo) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

// ------------------------------------------------------------------------------------------------------------------------
    public void receberComentario(Comentario comentario) {
        if(comentario == null){
            throw new IllegalArgumentException("O comentário não pode ser nulo");
        }
        this.comentarios.add(comentario);
        }

    public List<Comentario> filtrarComentariosAprovados() {
        return comentarios.stream()
                .filter(Comentario::isAprovado)
                .toList();
    }

    public List<Comentario> filtrarComentariosPendentes() {
        return comentarios.stream()
                .filter(c -> !c.isAprovado())
                .toList();
    }

    public void removerComentario(Comentario comentario) {
        this.comentarios.remove(comentario);
    }

    public void receberAvaliacaoArtigo(double nota) {

        calcularAvaliacaoArtigo(nota);
        autor.avaliarReputacaoAutor();
    }

    private void calcularAvaliacaoArtigo(double nota) {

        this.quantidadeAvaliacoesArtigo++;
        this.somaAvaliacoesArtigo += nota;
        this.avaliacao = this.somaAvaliacoesArtigo / this.quantidadeAvaliacoesArtigo;
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

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getQuantidadeAvaliacoesArtigo() {
        return quantidadeAvaliacoesArtigo;
    }

    public void setQuantidadeAvaliacoesArtigo(int quantidadeAvaliacoesArtigo) {
        this.quantidadeAvaliacoesArtigo = quantidadeAvaliacoesArtigo;
    }

    public double getSomaAvaliacoesArtigo() {
        return somaAvaliacoesArtigo;
    }

    public void setSomaAvaliacoesArtigo(double somaAvaliacoesArtigo) {
        this.somaAvaliacoesArtigo = somaAvaliacoesArtigo;
    }
}
