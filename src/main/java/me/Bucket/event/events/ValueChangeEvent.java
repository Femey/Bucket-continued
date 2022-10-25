package me.Bucket.event.events;

import me.Bucket.features.setting.Setting;
import me.Bucket.event.EventStage;

public class ValueChangeEvent
        extends EventStage {
    public Setting setting;
    public Object value;

    public ValueChangeEvent(Setting setting, Object value) {
        this.setting = setting;
        this.value = value;
    }
}

