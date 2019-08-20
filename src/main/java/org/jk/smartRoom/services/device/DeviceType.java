package org.jk.smartRoom.services.device;

public enum DeviceType {
    RELAY("relay", "relaySub"),
    DOOR("door", "doorSub"),
    CLOCK("clock", "clockSub"),
    WINDOW_EAST("", ""),
    WINDOW_SOUTH("", ""),
    GATE("", "");



    private final String subTopic;
    private final String pubTopic;

    DeviceType(String subTopic, String pubTopic) {
        this.subTopic = subTopic;
        this.pubTopic = pubTopic;
    }

    public String getSubTopic() {
        return subTopic;
    }

    public String getPubTopic() {
        return pubTopic;
    }
}
