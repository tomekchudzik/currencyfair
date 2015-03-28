package currencyfair.tradeprocessor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.util.concurrent.AtomicDouble;
import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import currencyfair.tradeprocessor.processor.data.TradeStatistics;
import currencyfair.tradeprocessor.processor.data.TradeWithId;

/**
 * This class will get notification about trades added to the cluster.
 * 
 * @author user
 *
 */
public class HazelcastTradeProcessor implements TradeProcessor {

	private final HazelcastInstance hazelcastInstance;

	private AtomicInteger tradeCount = new AtomicInteger();

	private ConcurrentHashMap<String, AtomicDouble> volumeByCurrencyPair = new ConcurrentHashMap<>();

	public HazelcastTradeProcessor(HazelcastInstance hazelcastInstance) {
		this.hazelcastInstance = hazelcastInstance;
	}

	// Listen to updates on trade cache and compute statistics
	public void init() {
		IMap<String, TradeWithId> map = hazelcastInstance.getMap("trades");
		map.addEntryListener(new TradeEntryListener(), true);
	}

	@Override
	public TradeStatistics getTradeStats() {

		Map<String, Double> copy = new HashMap<String, Double>();

		volumeByCurrencyPair.forEach((k, v) -> {
			copy.put(k, v.get());
		});

		return new TradeStatistics(tradeCount.get(), copy);
	}

	private class TradeEntryListener implements
			EntryListener<String, TradeWithId> {

		@Override
		public void entryAdded(EntryEvent<String, TradeWithId> event) {

			tradeCount.incrementAndGet();

			TradeWithId value = event.getValue();
			String key = value.getCurrencyFrom() + "/" + value.getCurrencyTo();
			volumeByCurrencyPair.computeIfAbsent(key, k -> new AtomicDouble(0))
					.addAndGet(value.getAmountSell());

		}

		@Override
		public void entryEvicted(EntryEvent<String, TradeWithId> event) {

		}

		@Override
		public void entryRemoved(EntryEvent<String, TradeWithId> event) {

		}

		@Override
		public void entryUpdated(EntryEvent<String, TradeWithId> event) {

		}

	}

}
