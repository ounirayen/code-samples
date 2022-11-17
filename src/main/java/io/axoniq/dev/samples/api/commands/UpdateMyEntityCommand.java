package io.axoniq.dev.samples.api.commands;


import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author Sara Pellegrini
 * @author Stefan Dragisic
 */
public class UpdateMyEntityCommand {

    @TargetAggregateIdentifier
    private String entityId;
    private String name;

    public UpdateMyEntityCommand() {
    }

    public UpdateMyEntityCommand(String entityId, String name) {
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