package br.edu.infnet.herick_bispo_blog_API.service;

import br.edu.infnet.herick_bispo_blog_API.domain.Identificavel;
import br.edu.infnet.herick_bispo_blog_API.exception.IdentificadorDuplicadoException;
import br.edu.infnet.herick_bispo_blog_API.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public abstract class BaseService<T extends Identificavel> {

    private final Map<Long, T> dados = new LinkedHashMap<Long, T>();

    public void incluir(T objeto){

        validarObjeto(objeto);

        if(dados.containsKey(objeto.getId())){
            throw new IdentificadorDuplicadoException("Já existe um objeto com este identificador");
        }

        dados.put(objeto.getId(), objeto);
    }

    public void alterar(T objeto){

        validarObjeto(objeto);

        verificarExistencia(objeto.getId());

        dados.put(objeto.getId(), objeto);
    }

    public void excluir(Long id){

        verificarExistencia(id);

        dados.remove(id);
    }

    public List<T> obterLista(){

        return new ArrayList<T>(dados.values());
    }

    public T obterPorId(Long id){

        verificarExistencia(id);

        return dados.get(id);
    }

    private void validarObjeto(T objeto){

        if(objeto == null){
            throw new IllegalArgumentException("O objeto não pode ser nulo.");
        }

        if(objeto.getId() == null){
            throw new IllegalArgumentException("O identificador do objeto não pode ser nulo.");
        }
    }

    private void verificarExistencia(Long id){

        if(id == null){
            throw new IllegalArgumentException("O identificador não pode ser nulo.");
        }

        if(!dados.containsKey(id)){
            throw new RecursoNaoEncontradoException("Nenhum recurso encontrado para esse identificador");
        }
    }
}
