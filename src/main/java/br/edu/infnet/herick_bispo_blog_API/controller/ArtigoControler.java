package br.edu.infnet.herick_bispo_blog_API.controller;

import br.edu.infnet.herick_bispo_blog_API.domain.Artigo;
import br.edu.infnet.herick_bispo_blog_API.repository.ArtigoRepository;
import br.edu.infnet.herick_bispo_blog_API.service.ArtigoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/artigos")
public class ArtigoControler {

    private final ArtigoService artigoService;
    private final ArtigoRepository artigoRepository;

    public ArtigoControler(ArtigoService artigoService, ArtigoRepository artigoRepository) {
        this.artigoService = artigoService;
        this.artigoRepository = artigoRepository;
    }

    @Operation(summary = "Lista com todos os artigos.",
            description = "Retorna com todos os comunicados na aplicação")
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

    @GetMapping("/publicados")
    public ResponseEntity<List<Artigo>>  obterPublicados(){

        List<Artigo> comunicados = artigoService.obterPublicados();
        return ResponseEntity.ok(comunicados);
    }

    @GetMapping("/busca")
    public ResponseEntity<List<Artigo>>  obertPorTitulo(@Parameter(description = "Trecho do título do artigo") @RequestParam String titulo){

        List<Artigo> artigos = artigoService.obterPorTitulo(titulo);
        return ResponseEntity.ok(artigos);
    }

    @PostMapping
    public ResponseEntity<Artigo> incluir(@Valid @RequestBody Artigo artigo){

        artigoService.incluir(artigo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(artigo.getId()).toUri();

        return  ResponseEntity.created(location).body(artigo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artigo> alterar(@PathVariable Long id, @Valid @RequestBody Artigo artigo){

        artigo.setId(id);

        artigoService.alterar(id, artigo);

        return ResponseEntity.ok(artigo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){

        artigoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
