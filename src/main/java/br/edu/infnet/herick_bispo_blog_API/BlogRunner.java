package br.edu.infnet.herick_bispo_blog_API;

import br.edu.infnet.herick_bispo_blog_API.domain.Artigo;
import br.edu.infnet.herick_bispo_blog_API.domain.Autor;
import br.edu.infnet.herick_bispo_blog_API.domain.Comentario;
import br.edu.infnet.herick_bispo_blog_API.domain.Leitor;
import br.edu.infnet.herick_bispo_blog_API.service.ArtigoService;
import br.edu.infnet.herick_bispo_blog_API.service.AutorService;
import br.edu.infnet.herick_bispo_blog_API.service.ComentarioService;
import br.edu.infnet.herick_bispo_blog_API.service.LeitorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BlogRunner implements CommandLineRunner {

    private final ArtigoService artigoService;
    private final AutorService autorService;
    private final ComentarioService comentarioService;
    private final LeitorService leitorService;

    private Artigo artigo1;
    private Artigo artigo2;


    public BlogRunner(ArtigoService artigoService,
                      AutorService autorService,
                      ComentarioService comentarioService,
                      LeitorService leitorService){

                this.artigoService = artigoService;
                this.autorService = autorService;
                this.comentarioService = comentarioService;
                this.leitorService = leitorService;
            }

    @Override
    public void run(String... args) throws Exception {

                // Usuarios
                Autor autor1 = new Autor(1L, "herick", "herick@gmail.com");
                autorService.incluir(autor1); // INSERT INTO Autor

                Leitor leitor1 = new Leitor(2L, "hater", "hater@gmail.com");
                leitor1.assinarNewsLetter(true);
                leitorService.incluir(leitor1); // INSERT INTO Leitor
                // -----------------------------------------------------------------------------------------


                // Artigos
                Autor autorBuscado = autorService.obterPorId(1L);

                Artigo artigo1 = new Artigo(3L, "Estudar depois da aula", "Estudar depois da aula é importante para fixar o conteúdo.");
                autorBuscado.publicarArtigo(artigo1);

                Artigo artigo2 = new Artigo(5L, "Preciso de Outro Artigo", "Preciso de outro artigo para pesquisar por identificador.");
                autorBuscado.publicarArtigo(artigo2);

                artigoService.incluir(artigo1);
                artigoService.incluir(artigo2);
                autorService.alterar(autorBuscado);
                // -----------------------------------------------------------------------------------------


                // Comentários e Avaliações
                Leitor leitorBuscado = leitorService.obterPorId(2L);
                Artigo artigoBuscado = artigoService.obterPorId(3L);

                leitorBuscado.avaliar(artigoBuscado, 1.0);

                Comentario novoComentario = new Comentario(4L, "Seu texto está cheio de erros de português (¬_¬)");
                novoComentario.setAutorComentario(leitorBuscado);
                novoComentario.setDataHora(LocalDateTime.now());
                artigo1.incrementarVisualizacao();

                comentarioService.adicionarComentario(artigoBuscado, novoComentario);
                // -----------------------------------------------------------------------------------------


                // Moderação e Comentários
                System.out.println("Comentários Pendentes");
                System.out.println(artigoBuscado.filtrarComentariosPendentes().size());

                System.out.println("Comentários Aprovados");
                System.out.println(artigoBuscado.filtrarComentariosAprovados().size());


                Autor autorModerador = autorService.obterPorId(1L);
                Comentario comentarioParaModerar = comentarioService.obterPorId(4L);
                Artigo artigoParaModerar = artigoService.obterPorId(3L);

                autorModerador.moderarComentario(artigoParaModerar, comentarioParaModerar, true);

                comentarioService.alterar(comentarioParaModerar);
                artigoService.alterar(artigoParaModerar);

                System.out.println("Novo Comentários Pendentes");
                System.out.println(artigoParaModerar.filtrarComentariosPendentes().size());

                System.out.println("Novo Comentários Aprovados:");
                System.out.println(artigoParaModerar.filtrarComentariosAprovados().size());
                // -----------------------------------------------------------------------------------------


                // Puxando as listas do CRUD
                System.out.println("\nAutores:");
                autorService.obterLista().forEach(System.out::println);

                System.out.println("\nLeitores:");
                leitorService.obterLista().forEach(System.out::println);

                System.out.println("\nArtigos:");
                artigoService.obterLista().forEach(System.out::println);

                System.out.println("\nComentarios:");
                comentarioService.obterLista().forEach(System.out::println);
            }
        }