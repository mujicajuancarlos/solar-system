package ar.com.mercadolibre.solarsystem.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    /**
     * Esta funcionalidad retorna la cantidad de dias desde #startDate hasta #startDate + #years
     *
     * @param startDate @{@link Date} fecha de inicio
     * @param years     cantidad de años a sumar
     * @return numero de dias
     */
    public static int getDaysFrom(Date startDate, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.YEAR, years);
        Date endDate = calendar.getTime();
        long diff = endDate.getTime() - startDate.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
