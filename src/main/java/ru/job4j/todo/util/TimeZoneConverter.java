package ru.job4j.todo.util;

import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.time.ZoneId;
import java.util.TimeZone;

public final class TimeZoneConverter {

    private TimeZoneConverter() {

    }

    public static void convert(Task task, User user) {
        if (user.getTimezone() == null) {
            user.setTimezone(TimeZone.getDefault().getID());
        }
        var time = task.getCreated()
                .atZone(ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.of(user.getTimezone()))
                .toLocalDateTime();
        task.setCreated(time);
    }
}
