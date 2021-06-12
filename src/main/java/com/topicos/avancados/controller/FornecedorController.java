package com.topicos.avancados.controller;

import com.topicos.avancados.model.Fornecedor;
import com.topicos.avancados.service.FornecedorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;


@RestController
@RequestMapping("/v1/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping("/todos")
    public ResponseEntity<Page<Fornecedor>> retornaTodosOsFornecedores(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Fornecedor> pageFornecedores = this.fornecedorService.findAll(paginacao);

        return ResponseEntity.ok(pageFornecedores);
    }

    @GetMapping("/{id_fornecedor}")
    public ResponseEntity<Fornecedor> retornaFornecedorPorId(@PathVariable String id_fornecedor) {
        Fornecedor fornecedor = this.fornecedorService.findById(UUID.fromString(id_fornecedor));

        return ResponseEntity.ok(fornecedor);
    }

    @PostMapping
    public ResponseEntity<Fornecedor> salvarNovoFornecedor(@RequestBody @Valid Fornecedor fornecedor, UriComponentsBuilder uriBuilder) {
        URI uri = this.fornecedorService.save(fornecedor, uriBuilder);

        return ResponseEntity.created(uri).body(fornecedor);
    }

    @PutMapping("/{id_fornecedor}")
    public ResponseEntity<?> atualizarFornecedor(@PathVariable String id_fornecedor, @RequestBody Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedor = this.fornecedorService.update(UUID.fromString(id_fornecedor), fornecedorAtualizado);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id_fornecedor}")
    public ResponseEntity<?> deletarFornecedor(@PathVariable String id_fornecedor) {
        this.fornecedorService.delete(UUID.fromString(id_fornecedor));

        return ResponseEntity.noContent().build();
    }

}
