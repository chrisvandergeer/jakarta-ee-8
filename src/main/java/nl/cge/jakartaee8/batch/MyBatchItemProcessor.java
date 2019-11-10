package nl.cge.jakartaee8.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;
import java.util.Date;

@Named
public class MyBatchItemProcessor implements ItemProcessor {

    @Override
    public String processItem(Object item) throws Exception {
        Integer number = (Integer) item;
        System.out.println(new Date() +" ProcessItem: " + number);
        Thread.sleep(1000);
        return String.format("'%s'", number.toString());
    }

}
