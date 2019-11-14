package nl.cge.jakartaee8.batch.control.prepare;

import nl.cge.jakartaee8.batch.entity.MyBatchJob;
import nl.cge.jakartaee8.batch.entity.MyEntity;
import nl.cge.jakartaee8.batch.entity.MyEntityContainer;

import javax.batch.api.AbstractBatchlet;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Named
public class PrepareBatch extends AbstractBatchlet {

    @PersistenceContext(name = "my-pu")
    private EntityManager em;

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public String process() {
        List<MyEntityContainer> myEntityContainerList = IntStream.range(0, 100).boxed()
                .map(i -> new MyEntityContainer())
                .collect(Collectors.toList());
        for (MyEntityContainer container : myEntityContainerList) {
           IntStream.range(0, 100).boxed()
                   .forEach(i -> {
                       MyEntity myEntity = new MyEntity();
                       em.persist(myEntity);
                       container.add(myEntity);
                   });
           em.persist(container);
        }
        MyBatchJob myBatchJob = new MyBatchJob();
        myBatchJob.setMyEntityContainerList(myEntityContainerList);
        em.persist(myBatchJob);
        return "COMPLETED";
    }

}
