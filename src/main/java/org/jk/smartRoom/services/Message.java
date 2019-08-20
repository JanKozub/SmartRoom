package org.jk.smartRoom.services;

import org.jk.smartRoom.services.device.DeviceType;

import java.time.Instant;
import java.time.LocalDateTime;

public class Message {
    private final Instant time;
    private final String topic;
    private DeviceType type;
    private final boolean state;
    private final String message;
    private final String returnMessage;

    public Message(Instant time, String topic, String message) {
        this.time = time;
        this.topic = topic;
        this.message = message;

        this.state = message.contains("1");

        if (message.contains("clock")) {
            this.type = DeviceType.CLOCK;

            String msg = "";
            LocalDateTime now = LocalDateTime.now();
            int hour = now.getHour();
            int minute = now.getMinute();
            if (hour < 10)
                msg = "0";
            msg = msg + hour;
            if (minute < 10)
                msg = msg + "0";
            msg = msg + minute;
            this.returnMessage = msg;
        } else {
            this.returnMessage = "ACTIVE";
            if (message.contains("relay"))
                this.type = DeviceType.RELAY;
            else if (message.contains("door"))
                this.type = DeviceType.DOOR;
            else if (message.contains("windoweast"))
                this.type = DeviceType.WINDOW_EAST;
            else if (message.contains("windowssouth"))
                this.type = DeviceType.WINDOW_SOUTH;
        }
    }

    public Instant getTime() {
        return time;
    }

    public String getTopic() {
        return topic;
    }

    public DeviceType getType() {
        return type;
    }

    public boolean isState() {
        return state;
    }

    public String getMessage() {
        return message;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

}
