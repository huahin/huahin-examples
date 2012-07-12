package org.huahinframework.examples.wordcount.huahin;

import java.io.IOException;

import org.huahinframework.core.Summarizer;
import org.huahinframework.core.Writer;
import org.huahinframework.core.io.Record;

public class WordSummarizer extends Summarizer {
    @Override
    public void summarizerSetup() {
    }

    @Override
    public void init() {
    }

    @Override
    public void summarizer(Writer writer) throws IOException,
            InterruptedException {
        int count = 0;
        while (hasNext()) {
            Record record = next(writer);
            count += record.getValueInteger("COUNT");
        }

        Record emitRecord = new Record();
        emitRecord.addValue("COUNT", count);
        writer.write(emitRecord);
    }
}
