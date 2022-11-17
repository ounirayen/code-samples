package io.axoniq.dev.samples.api.events;

import java.io.Serializable;

/**
 * @author Sara Pellegrini
 * @author Stefan Dragisic
 */
public class MyEntityCreatedEvent implements Serializable {

    private String entityId;

    public MyEntityCreatedEvent() {
    }

    public MyEntityCreatedEvent(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}