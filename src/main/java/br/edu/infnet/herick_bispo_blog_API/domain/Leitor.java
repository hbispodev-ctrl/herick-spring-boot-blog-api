package br.edu.infnet.herick_bispo_blog_API.domain;

import java.time.LocalDateTime;

public class Leitor extends Usuario {

    private boolean inscritoNewsletter;

    public Leitor(){}

    public Leitor(Long id, String nome, String email) {
        super(id, nome, email, LocalDateTime.now());
    }

// -------------------------------------------------------------------------------------------------------------------------

    public void avaliar(Artigo artigo, double nota) {

        //A nota deverá ser entre 1 e 5
        if(nota < 1 || nota > 5){
            throw new IllegalArgumentException("A nota deve ser entre 1 e 5");
        }
        artigo.receberAvaliacaoArtigo(nota);
    }

    public void assinarNewsLetter(Boolean inscrever){
        if(inscrever){
            this.inscritoNewsletter = true;
        }else {
            this.inscritoNewsletter = false;
        }
    }
// -----------------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {

        return String.format("Leitor {%s, inscritoNewsletter= '%s'}",
                super.toString(),
                inscritoNewsletter ? "sim": "não"
        );
    }

    public boolean isInscritoNewsletter() {
        return inscritoNewsletter;
    }

    public void setInscritoNewsletter(boolean inscritoNewsletter) {
        this.inscritoNewsletter = inscritoNewsletter;
    }
}
