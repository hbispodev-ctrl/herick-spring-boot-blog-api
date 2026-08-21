package br.edu.infnet.herick_bispo_blog_API.controller;

import br.edu.infnet.herick_bispo_blog_API.domain.Artigo;
import br.edu.infnet.herick_bispo_blog_API.domain.Comentario;
import br.edu.infnet.herick_bispo_blog_API.service.ArtigoService;
import br.edu.infnet.herick_bispo_blog_API.service.ComentarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/artigos")
public class ComentarioControler {

    private final ArtigoService artigoService;
    private final ComentarioService comentarioService;

    public ComentarioControler(ArtigoService artigoService, ComentarioService comentarioService) {
        this.artigoService = artigoService;
        this.comentarioService = comentarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Comentario>>  obterComentariosPorArtigo(@PathVariable Long idArtigo){

        List<Comentario> comentarios = comentarioService.obterComentariosPorArtigo(idArtigo);
        return ResponseEntity.ok(comentarios);
    }

    @PostMapping
    public ResponseEntity<Comentario> incluir(@RequestBody Comentario comentario){

        comentarioService.incluir(comentario);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comentario.getId()).toUri();

        return  ResponseEntity.created(location).body(comentario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> alterar(@PathVariable Long id, @RequestBody Comentario comentario){

        comentario.setId(id);

        comentarioService.alterar(comentario);

        return ResponseEntity.ok(comentario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){

        comentarioService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
