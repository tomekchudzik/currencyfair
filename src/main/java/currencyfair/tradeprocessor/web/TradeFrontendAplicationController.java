package currencyfair.tradeprocessor.web;

import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import currencyfair.tradeprocessor.TradeProcessor;

@RestController
public class TradeFrontendAplicationController {

	private final TradeProcessor tradeProcessor;
	private final SimpMessagingTemplate simpleMessageTemplate;

	private final Timer timer;

	@Autowired
	public TradeFrontendAplicationController(TradeProcessor tradeProcessor, SimpMessagingTemplate simpleMessageTemplate ) {
		this.tradeProcessor = tradeProcessor;
		this.simpleMessageTemplate = simpleMessageTemplate;
		timer = new Timer();
	}

	@PostConstruct
	public void init() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {			
				simpleMessageTemplate.convertAndSend("/topic/statusUpdate", tradeProcessor.getTradeStats());
			}
		}, 0, 1000);
	}
}
