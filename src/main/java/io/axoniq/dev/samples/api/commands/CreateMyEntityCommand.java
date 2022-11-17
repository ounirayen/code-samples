package io.axoniq.dev.samples.api.commands;


import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author Sara Pellegrini
 * @author Stefan Dragisic
 */
public class CreateMyEntityCommand {

    @TargetAggregateIdentifier
    private String entityId;

    public CreateMyEntityCommand() {
    }

    public CreateMyEntityCommand(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}