package ar.com.mercadolibre.solarsystem.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    /**
     * Esta funcionalidad toma una #startDate y le suma #years
     *
     * @param startDate @{@link Date} fecha de inicio
     * @param years     cantidad de años a sumar
     * @return numero de dias
     */
    public static Date plusYears(Date startDate, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }

    /**
     * Esta funcionalidad retorna la cantidad de dias desde #startDate hasta #startDate + #years
     *
     * @param startDate @{@link Date} fecha de inicio
     * @param years     cantidad de años a sumar
     * @return numero de dias
     */
    public static int getDaysFrom(Date startDate, int years) {
        Date endDate = plusYears(startDate, years);
        return getDaysBetween(startDate, endDate);
    }

    /**
     * Retorna la cantidad de dias entre #startDate y #endDate
     *
     * @param startDate @{@link Date} fecha de inicio
     * @param endDate   @{@link Date} fecha de fin
     * @return
     */
    public static int getDaysBetween(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
