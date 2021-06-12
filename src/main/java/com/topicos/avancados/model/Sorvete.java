package com.topicos.avancados.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity @Table(name = "sorvete")
@AllArgsConstructor @NoArgsConstructor @Builder
@Getter @Setter @EqualsAndHashCode @ToString
public class Sorvete {

    @Id @GeneratedValue
    @Column(name = "id_sorvete")
    private UUID id;

    @Column(name = "identificacao", nullable = false)
    @Size(min = 0, max = 30)
    private String identificacao;

    @Column(name = "descricao", nullable = false)
    @Size(min = 0, max = 50)
    private String descricao;

    @Column(name = "data_compra", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCompra;

    @Column(name = "preco", nullable = false)
    @Digits(integer = 6, fraction = 4)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;

}
