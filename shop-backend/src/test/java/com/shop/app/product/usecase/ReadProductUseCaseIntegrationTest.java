package com.shop.app.product.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;

import com.shop.app.product.domain.Product;

@SpringBootTest
@EnabledIfSystemProperty(named = "integrationTestEnabled", matches = "true|TRUE|T|t|y|Y|Yes")
@Sql(scripts="classpath:test-integration-data.sql",
config = @SqlConfig(transactionMode = TransactionMode.ISOLATED),
executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
@Sql(scripts="classpath:test-integration-delete-data.sql",
config = @SqlConfig(transactionMode = TransactionMode.ISOLATED),
executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
)
@ActiveProfiles("test")
public class ReadProductUseCaseIntegrationTest {
	
	@Autowired
	private ReadProductUseCase readProductUseCase;
	
	@Test
	public void findByCode() {
		Product product = readProductUseCase.findByCode("P-1");
	}
}
