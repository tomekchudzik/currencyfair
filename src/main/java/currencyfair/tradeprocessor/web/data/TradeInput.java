package currencyfair.tradeprocessor.web.data;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import currencyfair.tradeprocessor.web.util.JsonDateDeserializer;

public class TradeInput {

	private String userId;
	private String currencyFrom;
	private String currencyTo;
	private double amountSell;
	private double amountBuy;
	private double rate;
	private String originatingCountry;
	private LocalDateTime timePlaced;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public String getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}

	public double getAmountSell() {
		return amountSell;
	}

	public void setAmountSell(double amountSell) {
		this.amountSell = amountSell;
	}

	public double getAmountBuy() {
		return amountBuy;
	}

	public void setAmountBuy(double amountBuy) {
		this.amountBuy = amountBuy;
	}

	public String getOriginatingCountry() {
		return originatingCountry;
	}

	public void setOriginatingCountry(String originatingCountry) {
		this.originatingCountry = originatingCountry;
	}

	public LocalDateTime getTimePlaced() {
		return timePlaced;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setTimePlaced(LocalDateTime timePlaced) {
		this.timePlaced = timePlaced;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}
