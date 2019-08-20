package org.jk.smartRoom.services.device;

import org.jk.smartRoom.services.Message;
import org.jk.smartRoom.services.connection.CommService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class DeviceManager {
    private static final Logger log = LoggerFactory.getLogger(DeviceManager.class);

    private final Map<DeviceType, DeviceState> deviceStates = new HashMap<>();
    private final CommService commService;

    private final List<Subscription> subscriptions = new CopyOnWriteArrayList<>();

    DeviceManager(CommService commService) {
        this.commService = commService;

        deviceStates.put(DeviceType.CLOCK, new DeviceState(DeviceType.CLOCK, Duration.ofSeconds(15)));
        deviceStates.put(DeviceType.RELAY, new DeviceState(DeviceType.RELAY, Duration.ofSeconds(15)));
        deviceStates.put(DeviceType.DOOR, new DeviceState(DeviceType.DOOR, Duration.ofSeconds(15)));


        deviceStates.keySet().stream().map(DeviceType::getSubTopic).forEach(commService::connect);
        commService.register(this::updateState);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                validateStates();
            }
        }, 0, 5000);
    }

    public void sendMessage(DeviceType deviceType, String msg) {
        commService.sendMessage(deviceType.getPubTopic(), msg);
    }

    public DeviceState getState(DeviceType type) {
        return deviceStates.get(type);
    }

    public void register(DeviceType type, DeviceStateListener listener) {
        log.info("Registering {} to {}", listener, type);
        subscriptions.add(new Subscription(type, listener));
    }

    public void unregister(DeviceStateListener listener) {
        log.info("Unregistering {}", listener);
        subscriptions.removeIf(p -> p.listener == listener);
    }

    private void updateState(Message message) {
        boolean state = message.isState();
        DeviceType type = message.getType();

        sendMessage(type, message.getReturnMessage());


        log.debug("Updating state of {} to {}", type, state);

        DeviceState deviceState = deviceStates.get(type);
        if (deviceState != null) {
            if (deviceState.update(state)) {
                log.info("State changed for {} to {}. Notifying listeners", type, state);
                notifyStateChanged(type, deviceState);
            } else {
                log.debug("State not changed for {}.", type);
            }
        } else {
            log.warn("Unsupported device type {}", type);
        }
    }

    private void notifyStateChanged(DeviceType type, DeviceState state) {
        subscriptions.stream()
                .filter(p -> p.type == type)
                .map(p -> p.listener)
                .forEach(l -> l.stateChanged(type, state));

    }

    private void validateStates() {
        String message = deviceStates.values().stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n", "\nDevice statuses:\n", ""));

        log.info(message);
    }

    private static class Subscription {
        private final DeviceType type;
        private final DeviceStateListener listener;

        Subscription(DeviceType type, DeviceStateListener listener) {
            this.type = type;
            this.listener = listener;
        }
    }
}
