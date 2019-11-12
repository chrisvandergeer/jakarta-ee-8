package nl.cge.jakartaee8.batch.control.batch;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Named
public class MyBatchItemReader extends AbstractItemReader {

    private List<Integer> numbers;
    private int count;

    @Inject
    JobContext jobContext;

    @Override
    public Integer readItem() throws Exception {
        if (count >= numbers.size()) {
            return null;
        }
        jobContext.setTransientUserData(count);
        Integer item = numbers.get(count++);
        System.out.println(new Date() + "ReadItem: " + item);
        return item;
    }

    @Override
    public void open(Serializable checkpoint) throws Exception {
        numbers = IntStream.range(1, 100).boxed().collect(Collectors.toList());
        count = 0;
    }


}
