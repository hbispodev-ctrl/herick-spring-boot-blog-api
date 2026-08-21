package br.edu.infnet.herick_bispo_blog_API.service;

import br.edu.infnet.herick_bispo_blog_API.domain.Artigo;
import br.edu.infnet.herick_bispo_blog_API.domain.Autor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtigoService extends BaseService<Artigo>{

    public void publicarArtigo(Autor autor, Artigo artigo) {

        autor.publicarArtigo(artigo);

        super.incluir(artigo);
    }

    public List<Artigo> obterListaPublicadosDeclarativo(){
        return obterLista().stream().filter(Artigo::isPublicado).toList();
    }

    public List<Artigo> obterPorTitulo(String termo){

        //validarTermo(termo);

        List<Artigo> resultado = obterLista().stream()
                .filter(artigo -> artigo
                .getTitulo()
                .toLowerCase()
                .contains(termo.toLowerCase()))
                .toList();
        return resultado;
    }
}
