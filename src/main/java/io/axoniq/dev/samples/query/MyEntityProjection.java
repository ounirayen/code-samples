package io.axoniq.dev.samples.query;

import io.axoniq.dev.samples.api.events.MyEntityUpdatedEvent;
import io.axoniq.dev.samples.api.queries.GetMyEntityQuery;
import io.axoniq.dev.samples.api.events.MyEntityCreatedEvent;
import io.axoniq.dev.samples.api.commands.UpdateMyEntityCommand;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

/**
 * @author Sara Pellegrini
 * @author Stefan Dragisic
 */
@Component
class MyEntityProjection {

    private final QueryUpdateEmitter emitter;
    private final MyEntityRepository repository;

    public MyEntityProjection(QueryUpdateEmitter emitter, MyEntityRepository repository) {
        this.emitter = emitter;
        this.repository = repository;
    }

    @QueryHandler
    public MyEntity on(GetMyEntityQuery query) {
        final MyEntity entity = repository.findById(query.getEntityId()).orElse(null);
        return entity;
    }

    @EventHandler
    public void on(MyEntityCreatedEvent event) {
        MyEntity entity = new MyEntity(event.getEntityId());
        repository.save(entity);

        emitter.emit(GetMyEntityQuery.class, query -> query.getEntityId().equals(event.getEntityId()), entity);
    }

    @EventHandler
    public void on(MyEntityUpdatedEvent event) {
        MyEntity entity = repository.findById(event.getEntityId()).orElse(null);
        if (entity != null) {
            entity.setName(event.getName());
            repository.save(entity);
        }
    }
}