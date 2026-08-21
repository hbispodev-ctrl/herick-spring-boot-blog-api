package br.edu.infnet.herick_bispo_blog_API.controller;

import br.edu.infnet.herick_bispo_blog_API.domain.Artigo;
import br.edu.infnet.herick_bispo_blog_API.service.ArtigoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/artigos")
public class ArtigoControler {

    private final ArtigoService artigoService;

    public ArtigoControler(ArtigoService artigoService) {
        this.artigoService = artigoService;
    }

    @GetMapping
    public ResponseEntity< List<Artigo>> obterLista(){

        List<Artigo> artigos = artigoService.obterLista();

        return ResponseEntity.ok(artigos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artigo>  obterPorId(@PathVariable Long id){

        Artigo artigo = artigoService.obterPorId(id);

        return ResponseEntity.ok(artigo);
    }

    @GetMapping(params = "titulo")
    public ResponseEntity<List<Artigo>>  obertPorTitulo(@RequestParam String titulo){

        List<Artigo> artigos = artigoService.obterPorTitulo(titulo);
        return ResponseEntity.ok(artigos);
    }

    @PostMapping
    public ResponseEntity<Artigo> incluir(@RequestBody Artigo artigo){

        artigoService.incluir(artigo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(artigo.getId()).toUri();

        return  ResponseEntity.created(location).body(artigo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artigo> alterar(@PathVariable Long id, @RequestBody Artigo artigo){

        artigo.setId(id);

        artigoService.alterar(artigo);

        return ResponseEntity.ok(artigo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){

        artigoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
