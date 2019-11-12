package nl.cge.jakartaee8.batch.control.prepare;

import nl.cge.jakartaee8.batch.entity.MyEntity;

import javax.batch.api.AbstractBatchlet;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.UUID;
import java.util.stream.IntStream;

@Named
public class PrepareBatch extends AbstractBatchlet {

    @PersistenceContext(name = "my-pu")
    private EntityManager em;

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public String process() throws Exception {
        try {
            return doPrepare();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String doPrepare() {
        IntStream.range(0, 100000).forEach(i -> {
            MyEntity myEntity = new MyEntity();
            em.persist(myEntity);
            if (i % 250 == 0) {
                System.out.println(myEntity.getId());
            }
        });
        return "COMPLETED";
    }
}
