package br.edu.infnet.herick_bispo_blog_API;

import br.edu.infnet.herick_bispo_blog_API.domain.model.Artigo;
import br.edu.infnet.herick_bispo_blog_API.domain.model.Autor;
import br.edu.infnet.herick_bispo_blog_API.domain.model.Comentario;
import br.edu.infnet.herick_bispo_blog_API.domain.model.Leitor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Loader implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

// - Instanciação Autor e Leitor ----------------------------------------------------------------------------------------------------------------------------------------------------------------
        Autor autor1 = new Autor("herick", "herick@gmail.com'");

        Leitor leitor1 = new Leitor("hater", "hater@gmail.com");
        leitor1.assinarNewsLetter(true);

        System.out.println("\n");
        System.out.println(autor1);
        System.out.println(leitor1);

// - Artigos ----------------------------------------------------------------------------------------------------------------------------------------------------------------

        //Publicar Artigo
        Artigo artigo1 = new Artigo("Estudar depois da aula", "Estudar depois da aula é importante para fixar o conteúdo.");
        autor1.publicarArtigo(artigo1);

        //Listar Todos os Artigos - Pssivelmente será refatorado com streams
        List<Artigo> repositorioDeArtigos = new ArrayList<>();
        repositorioDeArtigos.add(artigo1);

        //Excluir Artigos
        var excluirArtigo = false;

        if (excluirArtigo == true) {
            autor1.excluirArtigo(artigo1, excluirArtigo);
            repositorioDeArtigos.remove(artigo1);
        }

        System.out.println("\n");
        System.out.println(artigo1);


// - Comentários ----------------------------------------------------------------------------------------------------------------------------------------------------------------

        leitor1.comentar(artigo1,"Seu texto está cheio de erros de portugês (¬_¬)" );

        leitor1.avaliar(artigo1, 1.0);

        System.out.println("\nLista de pendentes antes da moderação: ");
        artigo1.getComentariosPendentes().forEach(System.out::println);

        System.out.println("\nLista de aprovados antes da moderação: ");
        artigo1.getComentarios().forEach(System.out::println);

// - Moderar Comentários ----------------------------------------------------------------------------------------------------------------------------------------------------------------

        Comentario comentario1 = artigo1.getComentariosPendentes().get(0);
        autor1.moderarComentario(artigo1, comentario1, true);

        System.out.println("\nLista de pendentes depois da moderação: ");
        artigo1.getComentariosPendentes().forEach(System.out::println);

        System.out.println("\nLista de aprovados depois da moderação: ");
        artigo1.getComentarios().forEach(System.out::println);

        //Artigo impresso com os comentários moderados
        System.out.println("\n");
        System.out.println(artigo1);

// - Exibir Artigos ----------------------------------------------------------------------------------------------------------------------------------------------------------------

        // Possivelmente será refadorado com streams para usar apenaa uma lista
        repositorioDeArtigos.forEach(System.out::println);
    }
}

