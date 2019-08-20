package org.jk.smartRoom.services.device;

public interface DeviceStateListener {
    void stateChanged(DeviceType type, DeviceState state);
}
