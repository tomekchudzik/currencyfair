package currencyfair.tradeprocessor.processor.data;

import java.util.Map;

public class TradeStatistics {

	private final int tradeCount;
	
	private final Map<String, Double> volumeByCurrencyPair;

	public TradeStatistics(int tradeCount,
			Map<String, Double> volumeByCurrencyPair) {
		this.tradeCount = tradeCount;
		this.volumeByCurrencyPair = volumeByCurrencyPair;
	}
	
	
	public int getTradeCount() {
		return tradeCount;
	}

	public Map<String, Double> getVolumeByCurrencyPair() {
		return volumeByCurrencyPair;
	}
	
}
