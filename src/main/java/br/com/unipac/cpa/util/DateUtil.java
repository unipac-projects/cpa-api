package br.com.unipac.cpa.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.unipac.cpa.exception.NotImplementationConstructionException;

public class DateUtil {
	
	private DateUtil(){
		throw new NotImplementationConstructionException("Classe não pode ser instanciada");
	}
	
	public static LocalDateTime convert(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		ZonedDateTime zdt = cal.toZonedDateTime();
		return zdt.toLocalDateTime();
	}

	public static Date convert(LocalDateTime ldt) {
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		GregorianCalendar cal = GregorianCalendar.from(zdt);
		return cal.getTime();
	}

	public static Date convert(String date) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

}
