package currencyfair.tradeprocessor.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import currencyfair.tradeprocessor.TradeConsumer;
import currencyfair.tradeprocessor.TradeIdGenerator;
import currencyfair.tradeprocessor.processor.data.TradeWithId;

public class TradeInputApplicationControllerTest {

	private static final String TRADE_ID = "1";

	@Mock
	private TradeConsumer tradeConsumer;
	
	@Mock
	private TradeIdGenerator tradeIdGenerator;

	@InjectMocks
	private TradeInputApplicationController controller;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);

		Mockito.when(tradeIdGenerator.generateId(Mockito.any())).thenReturn(TRADE_ID);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}

	@Test
	public void testTradeIsAddedToTheConsumer() throws Exception {

		String input = "{\"userId\": \"134256\", \"currencyFrom\": \"EUR\", \"currencyTo\": \"GBP\", \"amountSell\": 1000, \"amountBuy\": 747.10, \"rate\": 0.7471, \"timePlaced\" : \"24-JAN-15 10:27:44\", \"originatingCountry\" : \"FR\"}";

		mockMvc.perform(
				post("/trade").contentType(MediaType.APPLICATION_JSON).content(
						input)).andExpect(status().isOk());
		
		Mockito.verify(tradeConsumer).consume(new TradeWithId(TRADE_ID, "134256", "EUR", "GBP", 1000, 747.10, "FR", LocalDateTime.of(2015, 1, 24, 10, 27, 44, 0)));

	}

}
