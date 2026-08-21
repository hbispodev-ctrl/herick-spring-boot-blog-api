package br.edu.infnet.herick_bispo_blog_API.service;

import br.edu.infnet.herick_bispo_blog_API.domain.Artigo;
import br.edu.infnet.herick_bispo_blog_API.domain.Comentario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService extends BaseService<Comentario> {

    private final ArtigoService artigoService;

    public ComentarioService(ArtigoService artigoService) {
        this.artigoService = artigoService;
    }

    public void adicionarComentario(Artigo artigo, Comentario comentario) {

        artigo.receberComentario(comentario);

        super.incluir(comentario);
    }

    public List<Comentario> obterComentariosPorArtigo(Long idArtigo) {

        Artigo artigo = artigoService.obterPorId(idArtigo);

        return artigo.getComentarios();
    }
}
