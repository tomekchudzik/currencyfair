package currencyfair.tradeprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.util.UuidUtil;

import currencyfair.tradeprocessor.web.data.TradeInput;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		// Just be able to easily run everything in one process we start
		// hazelcast node here
		Config cfg = new Config();
		Hazelcast.newHazelcastInstance(cfg);

		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public TradeConsumer tradeConsumer() {
		return new HazelcastTradeConsumer(hazelcastInstance());
	}
		
	@Bean(initMethod="init")
	public TradeProcessor tradeProcessor(){
		return new HazelcastTradeProcessor(hazelcastInstance());
	}

	@Bean
	public HazelcastInstance hazelcastInstance() {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.getNetworkConfig().addAddress("127.0.0.1");
		return HazelcastClient.newHazelcastClient(clientConfig);
	}
	
	@Bean
	public TradeIdGenerator tradeIdGenerator() {
		return new TradeIdGenerator() {	
			@Override
			public String generateId(TradeInput input) {
				return UuidUtil.buildRandomUUID().toString();
			}
		};
	}

}
