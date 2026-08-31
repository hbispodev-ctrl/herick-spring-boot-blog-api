package br.edu.infnet.herick_bispo_blog_API.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artigos")
public class Artigo implements Identificavel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    @NotBlank(message = "O título deve ser informado.")
    @Size(max = 150, message = "O título deve possuir no máximo 150 caracteres.")
    private String titulo;

    @Column(nullable = false, length = 5000)
    @NotBlank(message = "O conteúdo deve ser informado.")
    @Size(max = 150, message = "O conteúdo deve possuir no máximo 5000 caracteres.")
    private String conteudo;
    private LocalDateTime dataPublicacao;
    private boolean publicado;
    private int visualizacoes = 0;
    private double avaliacao = 0.0;
    private int quantidadeAvaliacoesArtigo = 0;
    private double somaAvaliacoesArtigo = 0.0;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    @JsonBackReference(value = "autor-artigo")
    private Autor autor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "artigo_id")
    private List<Comentario> comentarios = new ArrayList<Comentario>();

    protected Artigo(){}

    public Artigo(String titulo, String conteudo){
        this.titulo = titulo;
        this.conteudo = conteudo;
    }
    public Artigo(Long id, String titulo, String conteudo) {
        this(titulo, conteudo);
        this.id = id;
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
