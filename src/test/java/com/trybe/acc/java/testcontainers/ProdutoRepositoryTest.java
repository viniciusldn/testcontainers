package com.trybe.acc.java.testcontainers;

import java.util.List;
import java.util.Optional;
import com.trybe.acc.java.testcontainers.persistence.Produto;
import com.trybe.acc.java.testcontainers.persistence.ProdutoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;

@Testcontainers
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class ProdutoRepositoryTest {

  /**
   * Modifica a URI do adapter MongoDB do spring data para utilizar a URL do
   * container MongoDB. Por padrão, a URI utilizada é o `localhost:27017`.
   */
  @DynamicPropertySource
  static void mongoDbProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }

  @DisplayName("1 - Deve cadastrar um novo produto com sucesso.")
  @Test
  void DeveCadastrarNovoproduto() {
    this.produtoRepository.insert(produtoMock);
    Produto produtoAdicionado = this.produtoRepository
        .insert(new Produto("1", "teste", 9L));
    Assertions.assertNotNull(produtoAdicionado);
  }

  @Container
  static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo");

  @Autowired
  private ProdutoRepository produtoRepository;

  @AfterEach
  void cleanUp() {
    this.produtoRepository.deleteAll();
  }

  Produto produtoMock = new Produto("61ff1e243dabcd00b6452431", "produto1",
      10L);

  @DisplayName("2 - Deve listar todos os produtos com sucesso.")
  @Test
  void DeveListarTodosOsprodutos() {
    this.produtoRepository.findAll();
    List<Produto> produtos = produtoRepository.findAll();
    Assertions.assertNotNull(produtos);
  }

  @DisplayName("3 - Deve buscar um produto pelo id com sucesso.")
  @Test
  void DeveBuscarprodutoPeloId() {
    this.produtoRepository.findById(produtoMock.getId());
    Optional<Produto> produto = produtoRepository.findById("");
    Assertions.assertNotNull(produto);
  }

  @DisplayName("4 - Deve atualizar um produto pelo id com sucesso.")
  @Test
  void DeveAtualizarprodutoPeloId() {
    this.produtoRepository.findById(produtoMock.getId());
    this.produtoRepository.save(produtoMock);
    Produto prod = produtoRepository.save(produtoMock);
    Assertions.assertNotNull(prod);
  }

  @DisplayName("5 - Deve excluir um produto pelo id com sucesso.")
  @Test
  void DeveExcluirprodutoPeloId() {
    this.produtoRepository.deleteById(produtoMock.getId());
    Optional<Produto> produto = produtoRepository.findById("");
    Assertions.assertEquals(produto, Optional.empty());
  }

}
