package io.axoniq.dev.samples.query;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyEntityRepository extends JpaRepository<MyEntity, String> {
}
