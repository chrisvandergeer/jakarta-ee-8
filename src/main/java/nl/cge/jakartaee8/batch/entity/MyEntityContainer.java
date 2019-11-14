package nl.cge.jakartaee8.batch.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class MyEntityContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "MYENTITYCONTAINER_ID")
    @OneToMany
    private List<MyEntity> myEntityList = new ArrayList<>();

    public void add(MyEntity myEntity) {
        myEntityList.add(myEntity);
    }
}
