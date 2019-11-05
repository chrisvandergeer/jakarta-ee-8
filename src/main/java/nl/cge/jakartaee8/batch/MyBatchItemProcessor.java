package nl.cge.jakartaee8.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class MyBatchItemProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object item) throws Exception {
        Integer number = (Integer) item;
        int result = number * 1000;
        System.out.println("processItem from " + number + " to " + result);
        return result;
    }
}
