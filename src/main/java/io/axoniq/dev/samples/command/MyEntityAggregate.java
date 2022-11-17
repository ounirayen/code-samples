package io.axoniq.dev.samples.command;

import io.axoniq.dev.samples.api.commands.CreateMyEntityCommand;
import io.axoniq.dev.samples.api.events.MyEntityCreatedEvent;
import io.axoniq.dev.samples.api.events.MyEntityUpdatedEvent;
import io.axoniq.dev.samples.api.commands.UpdateMyEntityCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * @author Sara Pellegrini
 * @author Stefan Dragisic
 */
@Aggregate
public class MyEntityAggregate {

    @AggregateIdentifier
    private String entityId;

    public MyEntityAggregate() {
    }

    @CommandHandler
    public MyEntityAggregate(CreateMyEntityCommand command) {
        apply(new MyEntityCreatedEvent(command.getEntityId()));
    }

    @EventSourcingHandler
    public void on(MyEntityCreatedEvent event) {
        entityId = event.getEntityId();
    }

    @CommandHandler
    public void on(UpdateMyEntityCommand command) {
        apply(new MyEntityUpdatedEvent(command.getEntityId(), command.getName()));
    }

    @EventSourcingHandler
    public void on(MyEntityUpdatedEvent event) {
        entityId = event.getEntityId();
    }
}