package br.edu.infnet.herick_bispo_blog_API.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Autor extends Usuario{

    private double reputacao;
    private int quantidadeAvaliacoesEmArtigos = 0;
    private double somaAvaliacoesEmArtigos = 0.0;

    private List<Artigo> artigos = new ArrayList<>();

    public Autor(){}

    public Autor(String nome, String email) {
        super(1L, nome, email, LocalDateTime.now());
    }

// --------------------------------------------------------------------------------------------------------------------------

    public void publicarArtigo(Artigo artigo){

        if(artigo == null){
            throw new IllegalArgumentException("O artigo não pode ser nulo");
        }

        artigo.setDataPublicacao(LocalDateTime.now());
        artigos.add(artigo);
        artigo.setAutor(this);
        artigo.setPublicado(true);
    }

    public void excluirArtigo(Artigo artigo, boolean excluir){
        if (excluir) {
            artigos.remove(artigo);
        }
    }

    public void avaliarReputacaoAutor(double avaliacao){

        quantidadeAvaliacoesEmArtigos ++;
        this.somaAvaliacoesEmArtigos += avaliacao;
        this.reputacao = this.somaAvaliacoesEmArtigos / this.quantidadeAvaliacoesEmArtigos;
    }

    public void moderarComentario(Artigo artigo, Comentario comentario, boolean aprovado) {

        // O autor só pode moderar os próprios comentários
        if (!this.equals(artigo.getAutor())) {
            throw new IllegalArgumentException("O autor só pode moderar comentários dos próprios artigos.");
        }

        if (aprovado) {
            this.aprovar(artigo, comentario);
        } else {
            this.rejeitar(artigo, comentario);
        }
    }

    // Será refatorado com streams para usar apenas uma lista
    private void aprovar(Artigo artigo, Comentario comentario) {
        comentario.setAprovado(true);
        artigo.getComentariosPendentes().remove(comentario);
        artigo.getComentarios().add(comentario);
    }

    // Será refatorado com streams para usar apenas uma lista
    private void rejeitar(Artigo artigo, Comentario comentario) {
        comentario.setAprovado(false);
        artigo.getComentariosPendentes().remove(comentario);
    }

// -----------------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {

        return String.format("Autor {%s, reputacao= '%s', artigos= '%d'}",
                super.toString(),
                reputacao,
                artigos.size()
        );
    }

    public double getReputacao() {
        return reputacao;
    }

    public void setReputacao(double reputacao) {
        this.reputacao = reputacao;
    }

    public List<Artigo> getArtigos() {
        return artigos;
    }

    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos;
    }
}
