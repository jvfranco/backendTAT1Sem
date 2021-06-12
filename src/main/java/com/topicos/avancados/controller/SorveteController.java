package com.topicos.avancados.controller;

import com.topicos.avancados.controller.form.SorveteForm;
import com.topicos.avancados.model.Sorvete;
import com.topicos.avancados.service.SorveteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;


@RestController
@RequestMapping("/v1/sorvete")
public class SorveteController {

    @Autowired
    private SorveteService sorveteService;

    @GetMapping("/todos")
    public ResponseEntity<Page<Sorvete>> retornaTodosOsSorvetes(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Sorvete> pageSorvetes = this.sorveteService.findAll(paginacao);

        return ResponseEntity.ok(pageSorvetes);
    }

    @GetMapping("/{id_sorvete}")
    public ResponseEntity<Sorvete> retornaSorvetePorId(@PathVariable String id_sorvete) {
        Sorvete sorvete = this.sorveteService.findById(UUID.fromString(id_sorvete));

        return ResponseEntity.ok(sorvete);
    }

    @PostMapping
    public ResponseEntity<SorveteForm> salvarNovoSorvete(@RequestBody @Valid SorveteForm sorveteForm, UriComponentsBuilder uriBuilder) {
        URI uri = this.sorveteService.save(sorveteForm, uriBuilder);

        return ResponseEntity.created(uri).body(sorveteForm);
    }

    @PutMapping("/{id_sorvete}")
    public ResponseEntity<?> atualizarSorvete(@PathVariable String id_sorvete, @RequestBody SorveteForm sorveteForm) {
        Sorvete sorvete = this.sorveteService.update(UUID.fromString(id_sorvete), sorveteForm);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id_sorvete}")
    public ResponseEntity<?> deletarSorvete(@PathVariable String id_sorvete) {
        this.sorveteService.delete(UUID.fromString(id_sorvete));

        return ResponseEntity.noContent().build();
    }

}
