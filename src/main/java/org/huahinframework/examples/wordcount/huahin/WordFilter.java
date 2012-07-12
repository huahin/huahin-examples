package org.huahinframework.examples.wordcount.huahin;

import java.io.IOException;

import org.huahinframework.core.Filter;
import org.huahinframework.core.Writer;
import org.huahinframework.core.io.Record;

public class WordFilter extends Filter {

    @Override
    public void filterSetup() {
    }

    @Override
    public void init() {
    }

    @Override
    public void filter(Record record, Writer writer) throws IOException,
            InterruptedException {
        for (int i = 0; i < record.sizeValue(); i++) {
            Record emitRecord = new Record();
            emitRecord.addGrouping("WORD", record.getValueString(String.valueOf(i)));
            emitRecord.addValue("COUNT", 1);

            writer.write(emitRecord);
        }
    }
}
