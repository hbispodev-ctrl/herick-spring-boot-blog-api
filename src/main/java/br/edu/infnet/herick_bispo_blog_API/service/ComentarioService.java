package br.edu.infnet.herick_bispo_blog_API.service;

import br.edu.infnet.herick_bispo_blog_API.domain.Artigo;
import br.edu.infnet.herick_bispo_blog_API.domain.Comentario;
import br.edu.infnet.herick_bispo_blog_API.exception.RecursoNaoEncontradoException;
import br.edu.infnet.herick_bispo_blog_API.repository.ComentarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComentarioService extends BaseService<Comentario> {

    private final ArtigoService artigoService;
    private final ComentarioRepository comentarioRepository;

    public ComentarioService(ArtigoService artigoService, ComentarioRepository comentarioRepository) {
        this.artigoService = artigoService;
        this.comentarioRepository = comentarioRepository;
    }

    public List<Comentario> obterComentariosPorArtigo(Long idArtigo) {

        Artigo artigo = artigoService.obterPorId(idArtigo);

        return artigo.getComentarios();
    }

    //O Metodo ainda não está sendo usado, ainda busca nos comentarios de cada artigo através do obterComentariosPorArtigo
    public List<Comentario> obterComentario(){
        return comentarioRepository.findAll();
    }

    public void incluir(Long idArtigo, Comentario comentario){
        Artigo artigo = artigoService.obterPorId(idArtigo);
        comentario.setArtigo(artigo);
        comentarioRepository.save(comentario);
    }

    public void alterarDoUsuario(Long id, Comentario comentario) {

        Comentario existente = obterPorId(id);

        existente.setTexto(comentario.getTexto());
        existente.setDataHora(comentario.getDataHora());

        comentarioRepository.save(existente);
    }

    public void alterarDoModerador(Long id, Comentario comentario) {

        Comentario existente = obterPorId(id);

        existente.setAprovado(comentario.isAprovado());

        comentarioRepository.save(existente);
    }

    public void excluir(Long id){

        Comentario comentario = obterPorId(id);

        comentarioRepository.delete(comentario);
    }

    public Comentario obterPorId(Long id){

        return comentarioRepository.findById(id)
                .orElseThrow(()-> new RecursoNaoEncontradoException("Nenhum obejto encontrado com este identificador " +id+ "."));
    }

    //Busca os comentários ainda não moderados
    public List<Comentario> obterNaoModerados(){

        return comentarioRepository.findByAprovadoFalse();
    }
}
