package org.huahin.examples.userranking;

import java.util.ArrayList;
import java.util.List;

import org.huahin.core.Summarizer;
import org.huahin.core.io.Record;
import org.huahin.examples.userranking.FirstSummarizer;
import org.huahin.unit.SummarizerDriver;
import org.junit.Test;

public class FirstSummarizerTest extends SummarizerDriver {

    @Test
    public void test() {
        List<Record> input = new ArrayList<Record>();
        input.add(createInputRecord("/index.html", "1"));
        input.add(createInputRecord("/index.html", "1"));
        input.add(createInputRecord("/index.html", "1"));

        input.add(createInputRecord("/index.html", "2"));
        input.add(createInputRecord("/index.html", "2"));

        input.add(createInputRecord("/index.html", "3"));

        List<Record> output = new ArrayList<Record>();
        output.add(createOutputRecord("/index.html", "1", 3));
        output.add(createOutputRecord("/index.html", "2", 2));
        output.add(createOutputRecord("/index.html", "3", 1));

        run(input, output);
    }

    private Record createInputRecord(String path, String user) {
        Record record = new Record();
        record.addGrouping("PATH", path);
        record.addSort(user, Record.SORT_UPPER, 1);
        record.addValue("USER", user);
        return record;
    }

    private Record createOutputRecord(String path, String user, int pv) {
        Record record = new Record();
        record.addGrouping("PATH", path);
        record.addSort(pv, Record.SORT_UPPER, 1);
        record.addValue("USER", user);
        record.addValue("PV", pv);
        return record;
    }

    @Override
    public Summarizer getSummarizer() {
        return new FirstSummarizer();
    }
}
