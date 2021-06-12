package com.topicos.avancados.service;

import com.topicos.avancados.controller.form.SorveteForm;
import com.topicos.avancados.model.Fornecedor;
import com.topicos.avancados.model.Sorvete;
import com.topicos.avancados.repository.SorveteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

@Service
public class SorveteService {

    @Autowired
    private SorveteRepository sorveteRepository;

    @Autowired
    private FornecedorService fornecedorService;

    public Page<Sorvete> findAll(Pageable paginacao) {
        return this.sorveteRepository.findAll(paginacao);
    }

    public Sorvete findById(UUID id_sorvete) {
        return this.sorveteRepository.findById(id_sorvete)
                .orElseThrow(() -> new EntityNotFoundException("Sorvete de id: " + id_sorvete.toString() + " não encontrado!"));
    }

    public URI save(SorveteForm sorveteForm, UriComponentsBuilder uriBuilder) {
        Sorvete sorvete = sorveteForm.converteEmSorvete(this.fornecedorService);
        this.sorveteRepository.save(sorvete);
        return uriBuilder.path("/sorvete/{id}").buildAndExpand(sorvete.getId()).toUri();
    }

    public Sorvete update(UUID id_sorvete, SorveteForm sorveteForm) {
        Sorvete sorvete = this.findById(id_sorvete);

        Sorvete sorveteAtualizado = sorveteForm.atualizaSorvete(sorvete, this.fornecedorService);

        return this.sorveteRepository.save(sorveteAtualizado);
    }

    public void delete(UUID id_sorvete) {
        try {
            this.sorveteRepository.deleteById(id_sorvete);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
