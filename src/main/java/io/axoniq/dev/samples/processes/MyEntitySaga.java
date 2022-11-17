package io.axoniq.dev.samples.processes;

import io.axoniq.dev.samples.api.commands.UpdateMyEntityCommand;
import io.axoniq.dev.samples.api.events.MyEntityCreatedEvent;
import io.axoniq.dev.samples.api.events.MyEntityUpdatedEvent;
import io.axoniq.dev.samples.api.queries.GetMyEntityQuery;
import io.axoniq.dev.samples.query.MyEntity;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Saga
public class MyEntitySaga {

    @Autowired
    private ReactorCommandGateway commandGateway;

    @Autowired
    private ReactorQueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "entityId")
    void handle(MyEntityCreatedEvent event) {
        final MyEntity res = commandGateway.send(new UpdateMyEntityCommand(event.getEntityId(), "test")).then(sendAndReturnUpdate(event.getEntityId())).block();
        System.out.println(res);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "entityId")
    void handle(MyEntityUpdatedEvent event) {
    }

    public Mono<MyEntity> sendAndReturnUpdate(String entityId) {
        return queryGateway.subscriptionQuery(new GetMyEntityQuery(entityId), ResponseTypes.instanceOf(Void.class), ResponseTypes.instanceOf(MyEntity.class))
                .flatMap(queryResult -> queryResult.updates().next().timeout(Duration.ofSeconds(15)).doFinally(it -> queryResult.close())
                );
    }
}
