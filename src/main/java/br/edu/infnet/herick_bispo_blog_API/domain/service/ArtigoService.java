package br.edu.infnet.herick_bispo_blog_API.domain.service;

import br.edu.infnet.herick_bispo_blog_API.domain.model.Artigo;
import br.edu.infnet.herick_bispo_blog_API.domain.model.Autor;

public class ArtigoService extends BaseService<Artigo>{

    public void publicarArtigo(Autor autor, Artigo artigo) {

        autor.publicarArtigo(artigo);

        super.incluir(artigo);
    }

}
