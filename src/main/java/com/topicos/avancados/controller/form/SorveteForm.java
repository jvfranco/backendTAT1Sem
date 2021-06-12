package com.topicos.avancados.controller.form;

import com.topicos.avancados.model.Fornecedor;
import com.topicos.avancados.model.Sorvete;
import com.topicos.avancados.repository.FornecedorRepository;
import com.topicos.avancados.service.FornecedorService;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter
public class SorveteForm {

    @Size(min = 0, max = 30)
    private String identificacao;

    @Size(min = 0, max = 50)
    private String descricao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCompra;

    @Digits(integer = 6, fraction = 4)
    private BigDecimal preco;

    private String id_fornecedor;

    public Sorvete converteEmSorvete(FornecedorService fornecedorService) {
        return Sorvete.builder()
                .id(UUID.randomUUID())
                .identificacao(this.identificacao)
                .descricao(this.descricao)
                .preco(this.preco)
                .dataCompra(this.dataCompra)
                .fornecedor(fornecedorService.findById(UUID.fromString(this.id_fornecedor)))
                .build();
    }

    public Sorvete atualizaSorvete(Sorvete sorvete, FornecedorService fornecedorService) {

        if(!this.identificacao.isEmpty() && this.identificacao != null) {
            sorvete.setIdentificacao(this.identificacao);
        }

        if(!this.descricao.isEmpty() && this.descricao != null) {
            sorvete.setDescricao(this.descricao);
        }

        if(this.preco.compareTo(BigDecimal.ZERO) > 0) {
            sorvete.setPreco(this.preco);
        }

        if(!this.id_fornecedor.isEmpty() && this.id_fornecedor != null) {
            Fornecedor fornecedor = fornecedorService.findById(UUID.fromString(this.id_fornecedor));
            sorvete.setFornecedor(fornecedor);
        }

        return sorvete;
    }
}
