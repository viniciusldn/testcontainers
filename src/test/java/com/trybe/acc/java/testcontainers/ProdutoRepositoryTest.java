package com.trybe.acc.java.testcontainers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import net.bytebuddy.utility.dispatcher.JavaDispatcher.Container;

@Testcontainers
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class ProdutoRepositoryTest {

  /**
   * Modifica a URI do adapter MongoDB do spring data para utilizar a URL do container MongoDB.
   * 
   * Por padrão, a URI utilizada é o `localhost:27017`.
   *
   */
  @DynamicPropertySource
  static void mongoDbProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }

  @DisplayName("1 - Deve cadastrar um novo produto com sucesso.")
  @Test
  void DeveCadastrarNovoproduto() {
    Assertions.fail("Não implementado!");
  }


  @DisplayName("2 - Deve listar todos os produtos com sucesso.")
  @Test
  void DeveListarTodosOsprodutos() {
    Assertions.fail("Não implementado!");
  }



  @DisplayName("3 - Deve buscar um produto pelo id com sucesso.")
  @Test
  void DeveBuscarprodutoPeloId() {
    Assertions.fail("Não implementado!");
  }


  @DisplayName("4 - Deve atualizar um produto pelo id com sucesso.")
  @Test
  void DeveAtualizarprodutoPeloId() {
    Assertions.fail("Não implementado!");
  }



  @DisplayName("5 - Deve excluir um produto pelo id com sucesso.")
  @Test
  void DeveExcluirprodutoPeloId() {
    Assertions.fail("Não implementado!");
  }

}
