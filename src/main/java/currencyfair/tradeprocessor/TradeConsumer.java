package currencyfair.tradeprocessor;

import currencyfair.tradeprocessor.processor.data.TradeWithId;

public interface TradeConsumer {
	void consume(TradeWithId tradeWithId);
}
