package io.axoniq.dev.samples.api.events;

import java.io.Serializable;

/**
 * @author Sara Pellegrini
 * @author Stefan Dragisic
 */
public class MyEntityUpdatedEvent implements Serializable {

    private String entityId;
    private String name;

    public MyEntityUpdatedEvent() {
    }

    public MyEntityUpdatedEvent(String entityId, String name) {
        this.entityId = entityId;
        this.name = name;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}