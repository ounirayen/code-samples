package io.axoniq.dev.samples.query;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Sara Pellegrini
 * @author Stefan Dragisic
 */
@Entity
public class MyEntity implements Serializable {

    @Id
    private String id;

    private String name;

    public MyEntity() {
    }

    public MyEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}