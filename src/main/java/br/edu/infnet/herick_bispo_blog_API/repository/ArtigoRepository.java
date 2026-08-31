package br.edu.infnet.herick_bispo_blog_API.repository;

import br.edu.infnet.herick_bispo_blog_API.domain.Artigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtigoRepository extends JpaRepository<Artigo,Long> {

    List<Artigo> findByPublicadoTrue();

    List<Artigo> findByTituloContainsIgnoreCase(String titulo);
}
