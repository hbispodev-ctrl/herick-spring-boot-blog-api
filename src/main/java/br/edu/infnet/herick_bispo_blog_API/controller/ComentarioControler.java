package br.edu.infnet.herick_bispo_blog_API.controller;

import br.edu.infnet.herick_bispo_blog_API.domain.Artigo;
import br.edu.infnet.herick_bispo_blog_API.domain.Comentario;
import br.edu.infnet.herick_bispo_blog_API.service.ArtigoService;
import br.edu.infnet.herick_bispo_blog_API.service.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/artigos/{idArtigo}/comentarios")
public class ComentarioControler {

    private final ArtigoService artigoService;
    private final ComentarioService comentarioService;

    public ComentarioControler(ArtigoService artigoService, ComentarioService comentarioService) {
        this.artigoService = artigoService;
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public ResponseEntity<List<Comentario>>  obterComentariosPorArtigo(@PathVariable Long idArtigo){

        List<Comentario> comentarios = comentarioService.obterComentariosPorArtigo(idArtigo);
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/moderacao")
    public ResponseEntity<List<Comentario>>  obterNaoModerados(){

        List<Comentario> comentarios = comentarioService.obterNaoModerados();
        return ResponseEntity.ok(comentarios);
    }

    @PostMapping
    public ResponseEntity<Comentario> incluir(@PathVariable Long idArtigo, @Valid @RequestBody Comentario comentario){


        comentarioService.incluir(idArtigo, comentario);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comentario.getId()).toUri();

        return  ResponseEntity.created(location).body(comentario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> alterarDoUsuario(@PathVariable Long id, @Valid @RequestBody Comentario comentario){

        comentario.setId(id);

        comentarioService.alterarDoUsuario(id, comentario);

        return ResponseEntity.ok(comentario);
    }

    @PutMapping("/moderacao/{id}")
    public ResponseEntity<Comentario> alterarDoModerador(@PathVariable Long id, @RequestBody Comentario comentario){

        comentario.setId(id);

        comentarioService.alterarDoModerador(id, comentario);

        return ResponseEntity.ok(comentario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){

        comentarioService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
