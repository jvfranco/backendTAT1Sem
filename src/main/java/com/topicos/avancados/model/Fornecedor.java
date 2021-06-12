package com.topicos.avancados.model;

import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity @Table(name = "fornecedor")
@AllArgsConstructor @NoArgsConstructor @Builder
@Getter @Setter @EqualsAndHashCode @ToString
public class Fornecedor {

    @Id @GeneratedValue
    @Column(name = "id_fornecedor")
    private UUID id;

    @Column(name = "identificacao", nullable = false)
    @Size(min = 0, max = 30)
    private String identificacao;

    @Column(name = "descricao", nullable = false)
    @Size(min = 0, max = 50)
    private String descricao;

    @Column(name = "cnpj", nullable = false)
    @CNPJ
    private String cnpj;

    @Column(name = "data_cadastro", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "email")
    @Size(min = 0, max = 100)
    @Email
    private String email;

    @Column(name = "telefone", nullable = false)
    @Size(min = 0, max = 20)
    private String telefone;

}
