package io.axoniq.dev.samples.api.queries;

/**
 * @author Sara Pellegrini
 * @author Stefan Dragisic
 */
public class GetMyEntityQuery {

    private final String entityId;

    public GetMyEntityQuery(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityId() {
        return entityId;
    }
}