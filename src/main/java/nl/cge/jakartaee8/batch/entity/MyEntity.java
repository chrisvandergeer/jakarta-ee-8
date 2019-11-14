package nl.cge.jakartaee8.batch.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String uuid;

    @OneToOne
    private MyEntity soulmate;

    public MyEntity() {
        this.uuid = UUID.randomUUID().toString();
    }

    @Override
    public int hashCode() {
        return uuid.substring(0, 5).hashCode();
    }
}
