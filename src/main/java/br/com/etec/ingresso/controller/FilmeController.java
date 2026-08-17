package br.com.etec.ingresso.controller;

import br.com.etec.ingresso.entity.Filme;
import br.com.etec.ingresso.enums.ClassificacaoIndicadivaEnum;
import br.com.etec.ingresso.enums.SimNaoEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @GetMapping
    public List<Filme> listar(){
        Filme filme1 = Filme.builder()
                .id(1L)
                .nome("Matrix")
                .classificacao(ClassificacaoIndicadivaEnum.A16)
                .emCartaz(SimNaoEnum.S)
                .build();

        Filme filme2 = Filme.builder()
                .id(2L)
                .nome("Homem Aranha")
                .classificacao(ClassificacaoIndicadivaEnum.A16)
                .emCartaz(SimNaoEnum.S)
                .build();
        return List.of(filme1, filme2);
    }
    List<Long> idInexistente = List.of(1L, 2L, 3L);
    @GetMapping("/{id}")
        public ResponseEntity<Filme> buscarPorId(@PathVariable Long id) {
        if(idInexistente.contains(id)) {
            Filme filme1 = Filme.builder()
                    .id(id)
                    .nome("Matrix")
                    .classificacao(ClassificacaoIndicadivaEnum.A16)
                    .emCartaz(SimNaoEnum.S)
                    .build();
            return ResponseEntity.ok(filme1);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Filme> cadastrar(@RequestBody Filme filme){
        filme.setId(199L);
        return ResponseEntity.status(HttpStatus.CREATED).body(filme);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizar(@RequestBody Filme filme,
                                           @PathVariable Long id){
        if(idInexistente.contains(id)) {
            return ResponseEntity.ok(filme);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(idInexistente.contains(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
