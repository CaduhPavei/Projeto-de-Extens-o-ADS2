package com.senac.extensao.resourse;
import com.senac.extensao.models.Agricultor;
import com.senac.extensao.service.AgricultorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/agricultores")
public class AgricultorController {

    @Autowired
    private AgricultorService service;

    @PostMapping
    public ResponseEntity create(@RequestBody Agricultor entity) {
        Agricultor save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/agricultores/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll() {
        List<Agricultor> agricultores = service.buscaTodos();
        return ResponseEntity.ok(agricultores);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Agricultor agricultor = service.buscarPorId(id);
        return ResponseEntity.ok(agricultor);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Agricultor entity) {
        Agricultor alterado = service.alterar(id, entity);
        return ResponseEntity.ok().body(alterado);
    }
}
