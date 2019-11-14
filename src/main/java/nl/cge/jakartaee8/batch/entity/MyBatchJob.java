package nl.cge.jakartaee8.batch.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class MyBatchJob {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "MYBATCHJOB_ID")
    @OneToMany
    private List<MyEntityContainer> myEntityContainerList = new ArrayList<>();

}
