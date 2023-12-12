package com.senac.extensao.resourse;

import com.senac.extensao.models.Pessoa;
import com.senac.extensao.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @PostMapping
    public ResponseEntity create(@RequestBody Pessoa entity) {
        Pessoa save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/pessoas/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll() {
        List<Pessoa> pessoa = service.buscaTodos();
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Pessoa pessoa = service.buscarPorId(id);
        return ResponseEntity.ok(pessoa);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Pessoa entity) {
        Pessoa alterado = service.alterar(id, entity);
        return ResponseEntity.ok().body(alterado);
    }
}
