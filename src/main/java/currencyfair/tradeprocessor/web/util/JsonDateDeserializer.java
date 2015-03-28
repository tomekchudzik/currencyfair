package currencyfair.tradeprocessor.web.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonDateDeserializer extends JsonDeserializer<LocalDateTime> {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
			.parseCaseInsensitive().appendPattern("dd-MMM-yy HH:mm:ss")
			.toFormatter(Locale.US);

	@Override
	public LocalDateTime deserialize(JsonParser jsonparser,
			DeserializationContext arg1) {

		try {
			String text = jsonparser.getText();

			LocalDateTime localDateTime = LocalDateTime.parse(text,
					DATE_TIME_FORMATTER);

			return localDateTime;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
