package br.edu.infnet.herick_bispo_blog_API.domain.service;

import br.edu.infnet.herick_bispo_blog_API.domain.model.Artigo;
import br.edu.infnet.herick_bispo_blog_API.domain.model.Comentario;

public class ComentarioService extends BaseService<Comentario> {

    public void adicionarComentario(Artigo artigo, Comentario comentario) {

        artigo.receberComentario(comentario);

        super.incluir(comentario);
    }

}
