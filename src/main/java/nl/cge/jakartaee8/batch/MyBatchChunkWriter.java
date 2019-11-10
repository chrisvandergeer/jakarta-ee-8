package nl.cge.jakartaee8.batch;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
public class MyBatchChunkWriter extends AbstractItemWriter {

    List<Integer> processed = new ArrayList<>();

    @Override
    public void writeItems(List<Object> items) throws Exception {
        String result = items.stream().map(String.class::cast)
                .reduce(new Date() + " WriteItems:", (a, b) -> a + " " + b);
        System.out.println(result);
    }
}
