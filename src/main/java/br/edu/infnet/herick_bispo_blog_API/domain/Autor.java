package br.edu.infnet.herick_bispo_blog_API.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Autor extends Usuario{

    private double reputacao = 5.0;
    private int quantidadeAvaliacoesEmArtigos = 0;
    private double somaAvaliacoesEmArtigos = 0.0;

    @JsonManagedReference
    private List<Artigo> artigos = new ArrayList<>();

    public Autor(){}

    public Autor(Long id,String nome, String email) {
        super(id, nome, email, LocalDateTime.now());
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

    public void avaliarReputacaoAutor(){

        this.quantidadeAvaliacoesEmArtigos = this.artigos.size();
        this.somaAvaliacoesEmArtigos = this.artigos.stream()
                .mapToDouble(Artigo::getAvaliacao)
                .sum();
        this.reputacao = this.quantidadeAvaliacoesEmArtigos > 0
                ? this.somaAvaliacoesEmArtigos / this.quantidadeAvaliacoesEmArtigos
                : 5.0;
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

    private void aprovar(Artigo artigo, Comentario comentario) {
        comentario.setAprovado(true);
    }

    private void rejeitar(Artigo artigo, Comentario comentario) {
        artigo.removerComentario(comentario);
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
}
