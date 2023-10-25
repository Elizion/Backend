package com.core.backend.util;

import java.util.Calendar;
import java.util.Date;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.poi.ss.usermodel.DateUtil;

public class JxlsDateConverter implements Converter {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object convert(Class type, Object value) {
		double date;
		if (value == null)
			throw new ConversionException("No value specified");
		if (value instanceof Double) {
			date = ((Double) value).doubleValue();
		} else if (value instanceof Number) {
			date = ((Number) value).doubleValue();
		} else if (value instanceof String) {
			try {
				date = Double.parseDouble((String) value);
			} catch (NumberFormatException e) {
				throw new ConversionException(e);
			}
		} else {
			if (value instanceof Date)
				return value;
			throw new ConversionException("No value specified");
		}
		int wholeDays = (int) Math.floor(date);
		double excelSeconds = Double.valueOf(Math.round((date - Math.floor(date)) * 24.0D * 60.0D * 60.0D)).intValue();
		double adjustedDate = wholeDays + excelSeconds / 86400.0D + 5.0E-12D;
		Calendar calendar = DateUtil.getJavaCalendarUTC(adjustedDate, false);
		Date ret = calendar.getTime();
		return ret;
	}

}
