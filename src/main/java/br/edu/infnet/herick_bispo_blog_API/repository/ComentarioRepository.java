package br.edu.infnet.herick_bispo_blog_API.repository;

import br.edu.infnet.herick_bispo_blog_API.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario,Long> {

    List<Comentario> findByAprovadoFalse();
}
