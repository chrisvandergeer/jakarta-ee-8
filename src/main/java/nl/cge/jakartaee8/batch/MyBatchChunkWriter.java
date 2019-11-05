package nl.cge.jakartaee8.batch;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class MyBatchChunkWriter extends AbstractItemWriter {

    List<Integer> processed = new ArrayList<>();

    @Override
    public void writeItems(List<Object> items) throws Exception {
        System.out.print("WriteItems: ");
        items.stream().map(Integer.class::cast).forEach(item -> {
            System.out.print(item + " ");
            processed.add(item);
        });
        System.out.println();
    }
}
