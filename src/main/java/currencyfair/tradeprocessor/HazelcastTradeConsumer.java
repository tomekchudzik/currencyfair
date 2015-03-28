package currencyfair.tradeprocessor;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import currencyfair.tradeprocessor.processor.data.TradeWithId;

public class HazelcastTradeConsumer implements TradeConsumer {

	private final HazelcastInstance hazelcast;
	
	public HazelcastTradeConsumer(HazelcastInstance hazelcast) {
		this.hazelcast = hazelcast;
	}
	
	@Override
	public void consume(TradeWithId tradeWithId) {
		
		IMap<String,TradeWithId> trades = hazelcast.getMap("trades");
		trades.put(tradeWithId.getTradeId(), tradeWithId);
		
	}

}
