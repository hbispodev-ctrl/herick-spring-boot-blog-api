package br.edu.infnet.herick_bispo_blog_API.service;

import br.edu.infnet.herick_bispo_blog_API.domain.Artigo;
import br.edu.infnet.herick_bispo_blog_API.exception.RecursoNaoEncontradoException;
import br.edu.infnet.herick_bispo_blog_API.repository.ArtigoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtigoService extends BaseService<Artigo>{

    private final ArtigoRepository artigoRepository;

    public ArtigoService(ArtigoRepository artigoRepository) {
        this.artigoRepository = artigoRepository;
    }

    public List<Artigo> obterLista(){
        return artigoRepository.findAll();
    }

    public void incluir(Artigo artigo){
        artigoRepository.save(artigo);
    }

    public void alterar(Long id, Artigo artigo) {

        Artigo existente = obterPorId(id);

        existente.setTitulo(artigo.getTitulo());
        existente.setConteudo(artigo.getConteudo());
        existente.setDataPublicacao(artigo.getDataPublicacao());
        existente.setPublicado(artigo.isPublicado());

        artigoRepository.save(existente);
    }

    public void excluir(Long id){

        Artigo artigo = obterPorId(id);

        artigoRepository.delete(artigo);
    }

    public Artigo obterPorId(Long id){

        return artigoRepository.findById(id)
                .orElseThrow(()-> new RecursoNaoEncontradoException("Nenhum obejto encontrado com este identificador " +id+ "."));
    }

    public List<Artigo> obterPublicados(){

        return artigoRepository.findByPublicadoTrue();
    }

    public List<Artigo> obterPorTitulo(String termo){

        validarTermo(termo);

        return artigoRepository.findByTituloContainsIgnoreCase(termo);
    }

    public List<Artigo> obterListaPublicadosDeclarativo(){
        return obterLista().stream().filter(Artigo::isPublicado).toList();
    }

    public List<Artigo> buscaPorTituloDeclarativa(String termo){

        validarTermo(termo);

        String termoNormalizado = termo.toLowerCase();

        List<Artigo> resultado = obterLista().stream()
                .filter(comunicado -> comunicado
                        .getTitulo()
                        .toLowerCase()
                        .contains(termoNormalizado))
                .toList();
        return resultado;
    }

    private void validarTermo(String termo){

        if(termo == null || termo.isBlank()){

            throw  new IllegalArgumentException("O termo deve ser informado.");
        }
    }

    public List<Artigo> ordenarPorTitulos(){
        //TODO Auto-gerenated method stub
        return new ArrayList<Artigo>();
    }

    public List<String> obterTitulos(){
        //TODO Auto-gerenated method stub
        return new ArrayList<String>();
    }
}
