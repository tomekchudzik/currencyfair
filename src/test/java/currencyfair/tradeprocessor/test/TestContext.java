package currencyfair.tradeprocessor.test;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import currencyfair.tradeprocessor.TradeConsumer;


@Configuration
public class TestContext {

	@Bean
	public TradeConsumer tradeConsumer() {
		return Mockito.mock(TradeConsumer.class);
	}
	
}
