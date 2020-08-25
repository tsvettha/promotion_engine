package com.promotion.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PromotionEngineAppTest {

	PromotionEngineApp promotionEngineApp;

	@BeforeEach
	void setUp() throws Exception {
		promotionEngineApp = new PromotionEngineApp();
	}

	@Test
	public void main() {
		PromotionEngineApp.main(new String[] {});
	}

}
