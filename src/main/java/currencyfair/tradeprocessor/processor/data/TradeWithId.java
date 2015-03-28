package currencyfair.tradeprocessor.processor.data;

import java.io.Serializable;
import java.time.LocalDateTime;

import currencyfair.tradeprocessor.web.data.TradeInput;

public class TradeWithId implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String tradeId;
	private final String userId;
	private final String currencyFrom;
	private final String currencyTo;
	private final double amountSell;
	private final double amountBuy;
	private final String originatingCountry;
	private final LocalDateTime timePlaced;

	public TradeWithId(String tradeId, String userId, String currencyFrom,
			String currencyTo, double amountSell, double amountBuy,
			String originatingCountry, LocalDateTime timePlaced) {
		this.tradeId = tradeId;
		this.userId = userId;
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.amountSell = amountSell;
		this.amountBuy = amountBuy;
		this.originatingCountry = originatingCountry;
		this.timePlaced = timePlaced;
	}

	public TradeWithId(String generateId, TradeInput tradeInput) {
		this(generateId, tradeInput.getUserId(), tradeInput.getCurrencyFrom(),
				tradeInput.getCurrencyTo(), tradeInput.getAmountSell(),
				tradeInput.getAmountBuy(), tradeInput.getOriginatingCountry(),
				tradeInput.getTimePlaced());
	}

	public String getTradeId() {
		return tradeId;
	}

	public String getUserId() {
		return userId;
	}

	public String getCurrencyFrom() {
		return currencyFrom;
	}

	public String getCurrencyTo() {
		return currencyTo;
	}

	public double getAmountSell() {
		return amountSell;
	}

	public double getAmmoutBuy() {
		return amountBuy;
	}

	public String getOriginatingCountry() {
		return originatingCountry;
	}

	public LocalDateTime getTimePlaced() {
		return timePlaced;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amountBuy);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(amountSell);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((currencyFrom == null) ? 0 : currencyFrom.hashCode());
		result = prime * result
				+ ((currencyTo == null) ? 0 : currencyTo.hashCode());
		result = prime
				* result
				+ ((originatingCountry == null) ? 0 : originatingCountry
						.hashCode());
		result = prime * result
				+ ((timePlaced == null) ? 0 : timePlaced.hashCode());
		result = prime * result + ((tradeId == null) ? 0 : tradeId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TradeWithId other = (TradeWithId) obj;
		if (Double.doubleToLongBits(amountBuy) != Double
				.doubleToLongBits(other.amountBuy))
			return false;
		if (Double.doubleToLongBits(amountSell) != Double
				.doubleToLongBits(other.amountSell))
			return false;
		if (currencyFrom == null) {
			if (other.currencyFrom != null)
				return false;
		} else if (!currencyFrom.equals(other.currencyFrom))
			return false;
		if (currencyTo == null) {
			if (other.currencyTo != null)
				return false;
		} else if (!currencyTo.equals(other.currencyTo))
			return false;
		if (originatingCountry == null) {
			if (other.originatingCountry != null)
				return false;
		} else if (!originatingCountry.equals(other.originatingCountry))
			return false;
		if (timePlaced == null) {
			if (other.timePlaced != null)
				return false;
		} else if (!timePlaced.equals(other.timePlaced))
			return false;
		if (tradeId == null) {
			if (other.tradeId != null)
				return false;
		} else if (!tradeId.equals(other.tradeId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
