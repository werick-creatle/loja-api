package br.com.gamestore.loja_api.model; // Mantenha o seu 'package'

// Importações do Lombok (para getters, setters, etc.)
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

// Importações do JPA (para mapear a tabela)
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

/* * Anotações do Lombok:
 * @Getter e @Setter: Criam os métodos get/set para todos os campos.
 * @NoArgsConstructor: Cria um construtor vazio (exigido pelo JPA).
 * @AllArgsConstructor: Cria um construtor com todos os campos.
 * @EqualsAndHashCode(of = "id"): Usa apenas o 'id' para diferenciar dois jogos.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

/* * Anotações do JPA:
 * @Entity: Diz ao Spring que esta classe representa uma entidade do banco.
 * @Table(name = "tb_jogo"): Define o nome da tabela no banco de dados.
 */
@Entity
@Table(name = "tb_jogo")
public class Jogo {

    /*
     * @Id: Define que este campo é a Chave Primária (Primary Key).
     * @GeneratedValue(strategy = GenerationType.IDENTITY):
     * Define que o ID será auto-incrementado pelo próprio banco (MySQL).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;       // Usamos BigDecimal para dinheiro (mais preciso)
    private String plataforma;    // (Ex: "PS4", "XBOX", "PC")
    private String genero;
    private String urlImagemCapa;
    private LocalDate dataLancamento; // Para a tela "Novidades"

}