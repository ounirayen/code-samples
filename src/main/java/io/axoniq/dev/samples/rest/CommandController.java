package io.axoniq.dev.samples.rest;

import io.axoniq.dev.samples.api.commands.CreateMyEntityCommand;
import io.axoniq.dev.samples.api.commands.UpdateMyEntityCommand;
import io.axoniq.dev.samples.api.queries.GetMyEntityQuery;
import io.axoniq.dev.samples.query.MyEntity;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author Sara Pellegrini
 * @author Stefan Dragisic
 */
@RestController
public class CommandController {

    private final ReactorCommandGateway commandGateway;
    private final ReactorQueryGateway queryGateway;

    public CommandController(ReactorCommandGateway commandGateway, ReactorQueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @GetMapping("/entity/{id}")
    public Mono<MyEntity> get(@PathVariable("id") String entityId) {
        return queryGateway.query(new GetMyEntityQuery(entityId), MyEntity.class);
    }

    @PostMapping("/entities/{id}")
    public void myApi(@PathVariable("id") String entityId) {
        commandGateway.send(new CreateMyEntityCommand(entityId)).block();

        /*final MyEntity res = commandGateway.send(new UpdateMyEntityCommand(entityId, "test")).then(sendAndReturnUpdate(entityId)).block();
        System.out.println(res);*/
    }

    /*public Mono<MyEntity> sendAndReturnUpdate(String entityId) {
        return queryGateway.subscriptionQuery(new GetMyEntityQuery(entityId), ResponseTypes.instanceOf(Void.class), ResponseTypes.instanceOf(MyEntity.class))
                .flatMap(queryResult -> queryResult.updates().next().timeout(Duration.ofSeconds(15)).doFinally(it -> queryResult.close())
        );
    }*/
}