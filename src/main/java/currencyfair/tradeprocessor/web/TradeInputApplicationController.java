package currencyfair.tradeprocessor.web;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import currencyfair.tradeprocessor.TradeConsumer;
import currencyfair.tradeprocessor.TradeIdGenerator;
import currencyfair.tradeprocessor.processor.data.TradeWithId;
import currencyfair.tradeprocessor.web.data.TradeInput;

@RestController
public class TradeInputApplicationController {

	private final TradeIdGenerator tradeIdGenerator;

	private final TradeConsumer tradeConsumer;

	// Picking an arbitrary number because those threads will be IO bounded
	// (they will write to distributed cache).
	// This number should probably be tuned based on setup
	private ThreadPoolExecutor consumer = new ThreadPoolExecutor(5, 5, 0,
			TimeUnit.DAYS, new ArrayBlockingQueue<Runnable>(50));

	@Autowired
	public TradeInputApplicationController(TradeIdGenerator tradeIdGenerator,
			TradeConsumer tradeConsumer) {
		this.tradeIdGenerator = tradeIdGenerator;
		this.tradeConsumer = tradeConsumer;
	}

	@RequestMapping("/trade")
	public DeferredResult<String> consumeTrade(
			@RequestBody TradeInput tradeInput) {

		DeferredResult<String> result = new DeferredResult<>();

		try {

			consumer.execute(() -> {

				String generateId = tradeIdGenerator.generateId(tradeInput);

				tradeConsumer.consume(new TradeWithId(generateId, tradeInput));

				result.setResult(generateId);

			}, 5, TimeUnit.SECONDS);

		} catch (RejectedExecutionException e) {
			result.setErrorResult(new ServiceUnavailableException());
		}

		return result;

	}

	@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
	public class ServiceUnavailableException extends RuntimeException {

		private static final long serialVersionUID = 1L;

	}

}
