package com.github.vanily.core.formatter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Getter
public enum TimeFormatter {
    YEAR(31104000L, "ano", "anos"),
    MONTH(2592000L, "mês", "meses"),
    DAY(86400L, "dia", "dias"),
    HOUR(3600L, "hora", "horas"),
    MINUTE(60L, "minuto", "minutos"),
    SECOND(1L, "segundo", "segundos");

    public static final List<String> A = Arrays.asList("S", "M", "H", "D");
    private static final TimeFormatter[] VALUES = values();
    private static final Pattern PATTERN = Pattern.compile("^(\\d+\\.?\\d*)(\\D+)");
    private final long timeInSeconds;
    private final String singular;
    private final String plural;

    public static String format(long seconds) {
        Map<TimeFormatter, Integer> timeUnitMap = new LinkedHashMap<>();

        for (TimeFormatter value : VALUES) {
            if (seconds >= value.getTimeInSeconds()) {
                int durationInCurrentTime = (int) (seconds / value.getTimeInSeconds());
                seconds %= value.getTimeInSeconds();
                timeUnitMap.put(value, durationInCurrentTime);
            }
        }

        if (timeUnitMap.isEmpty()) {
            return "0 segundos";
        }

        StringBuilder stringBuilder = new StringBuilder();
        Set<Entry<TimeFormatter, Integer>> entries = timeUnitMap.entrySet();
        int size = entries.size();
        int index = 1;

        for (Entry<TimeFormatter, Integer> entry : entries) {
            Integer value = entry.getValue();
            TimeFormatter key = entry.getKey();

            stringBuilder.append(value)
                    .append(" ")
                    .append(value == 1 ? key.getSingular() : key.getPlural());

            if (index == size - 1 && size > 1) {
                stringBuilder.append(" e ");
            } else if (index != size) {
                stringBuilder.append(", ");
            }
            index++;
        }

        return stringBuilder.toString();
    }
}