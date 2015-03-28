package currencyfair.tradeprocessor;

import currencyfair.tradeprocessor.web.data.TradeInput;

public interface TradeIdGenerator {

	String generateId(TradeInput input);
	
}
