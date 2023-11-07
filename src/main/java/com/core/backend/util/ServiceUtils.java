package com.core.backend.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ServiceUtils {

	public static byte[] readFileFromStream(InputStream inputStream) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		byte[] data = new byte[4096];
		int count = inputStream.read(data);
		while (count != -1) {
			dos.write(data, 0, count);
			count = inputStream.read(data);
		}
		return baos.toByteArray();
	}

	public static String parseString(List<String> list, String separator) {
		String resul = "";
		Iterator<String> iterator = list.iterator();
		if (iterator.hasNext())
			resul = iterator.next();
		while (iterator.hasNext())
			resul = String.valueOf(resul) + separator + (String) iterator.next();
		return resul;
	}

	public static double safeDouble(Double objeto) {
		double valor = 0.0D;
		if (objeto != null)
			valor = objeto.doubleValue();
		return valor;
	}

	@SuppressWarnings("removal")
	public static Double safeAdd(Double a, Double b) {
		Double valor = null;
		if (a != null || b != null)
			valor = new Double(safeDouble(a) + safeDouble(b));
		return valor;
	}

	@SuppressWarnings("removal")
	public static Double safeMinus(Double a, Double b) {
		Double valor = null;
		if (a != null || b != null)
			valor = new Double(safeDouble(a) - safeDouble(b));
		return valor;
	}

	@SuppressWarnings("removal")
	public static Double safeMul(Double a, Double b) {
		Double valor = null;
		if (a != null && b != null)
			valor = new Double(safeDouble(a) * safeDouble(b));
		return valor;
	}

	@SuppressWarnings("removal")
	public static Double safeMul(Double a, int b) {
		Double valor = null;
		if (a != null)
			valor = new Double(safeDouble(a) * b);
		return valor;
	}

	public static Double safeAbs(Double a) {
		Double valor = null;
		if (a != null)
			valor = Double.valueOf(Math.abs(a.doubleValue()));
		return valor;
	}

	@SuppressWarnings("removal")
	public static Double safeDiv(Double a, Double b) {
		Double valor = null;
		if (a != null && b != null && b.doubleValue() != 0.0D)
			valor = new Double(safeDouble(a) / safeDouble(b));
		return valor;
	}

	public static boolean isNullZero(Double a) {
		return !(a != null && a.doubleValue() != 0.0D);
	}

	@SuppressWarnings("removal")
	public static Double safeRound(Double x) {
		if (x == null)
			return null;
		return new Double(roundDouble(x.doubleValue(), 0));
	}

	@SuppressWarnings("removal")
	public static Double safeRound(Double x, int y) {
		if (x == null)
			return null;
		return new Double(roundDouble(x.doubleValue(), y));
	}

	public static Double min(Double x, Double y) {
		Double min = null;
		if (x == null && y == null) {
			min = null;
		} else if (x == null) {
			min = y;
		} else if (y == null) {
			min = x;
		} else {
			min = (x.doubleValue() - y.doubleValue() >= 0.0D) ? y : x;
		}
		return min;
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		return cal;
	}

	public static double roundDouble(double x, int precision) {
		double p = Math.pow(10.0D, precision);
		double dou_parcial1 = x * p;
		double dou_parcial2 = dou_parcial1 + 0.5D;
		return Math.floor(dou_parcial2) / p;
	}

	public static int validateFile(String fileName, int maxLength, List<String> mimesAllowed) {
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String mimes = "";
		for (String mime : mimesAllowed)
			mimes = String.valueOf(mimes) + mime;
		if (fileName.length() > maxLength)
			return 1;
		if (mimes.indexOf(ext) == -1)
			return 2;
		return 0;
	}

	/*
	^(?=.*[A-Z])(?=.*\d).{6}$
	 ↑          ↑       ↑
	 │          │       six characters in total
	 │          at least one digit
	 at least one upper-case letter
	*/
	
	public static boolean matchesPolicy(String pwd) {	
		String pattern = "^(?=.*[A-Z])(?=.*\\d).{6,}$";
		if(pwd.matches(pattern)) {
			return true;	
		}
		return false;
	}
	
}
