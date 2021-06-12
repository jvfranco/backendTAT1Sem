package com.topicos.avancados.service;

import com.topicos.avancados.model.Fornecedor;
import com.topicos.avancados.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.UUID;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Page<Fornecedor> findAll(Pageable paginacao) {
        return this.fornecedorRepository.findAll(paginacao);
    }

    public Fornecedor findById(UUID id_fornecedor) {
        return this.fornecedorRepository.findById(id_fornecedor)
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor de id: " + id_fornecedor.toString() + " não encontrado!"));
    }

    public URI save(Fornecedor fornecedor, UriComponentsBuilder uriBuilder) {
        this.fornecedorRepository.save(fornecedor);
        return uriBuilder.path("/fornecedor/{id}").buildAndExpand(fornecedor.getId()).toUri();
    }

    public Fornecedor update(UUID id_fornecedor, Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedor = this.findById(id_fornecedor);

        if(!fornecedorAtualizado.getIdentificacao().isEmpty() && fornecedorAtualizado.getIdentificacao() != null) {
            fornecedor.setIdentificacao(fornecedorAtualizado.getIdentificacao());
        }

        if(!fornecedorAtualizado.getDescricao().isEmpty() && fornecedorAtualizado.getDescricao() != null) {
            fornecedor.setDescricao(fornecedorAtualizado.getDescricao());
        }

        if(!fornecedorAtualizado.getCnpj().isEmpty() && fornecedorAtualizado.getCnpj() != null) {
            fornecedor.setCnpj(fornecedorAtualizado.getCnpj());
        }

        if(!fornecedorAtualizado.getEmail().isEmpty() && fornecedorAtualizado.getEmail() != null) {
            fornecedor.setEmail(fornecedorAtualizado.getEmail());
        }

        if(!fornecedorAtualizado.getTelefone().isEmpty() && fornecedorAtualizado.getTelefone() != null) {
            fornecedor.setTelefone(fornecedorAtualizado.getTelefone());
        }

        this.fornecedorRepository.save(fornecedor);

        return fornecedor;
    }

    public void delete(UUID id_fornecedor) {
        try {
            this.fornecedorRepository.deleteById(id_fornecedor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
