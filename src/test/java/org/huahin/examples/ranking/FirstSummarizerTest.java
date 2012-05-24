package org.huahin.examples.ranking;

import java.util.ArrayList;
import java.util.List;

import org.huahin.core.Summarizer;
import org.huahin.core.io.Record;
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
        output.add(createOutputRecord("/index.html", 6, 3));

        run(input, output);
    }

    private Record createInputRecord(String path, String user) {
        Record record = new Record();
        record.addGrouping("DATE", "2000-01-01");
        record.addGrouping("PATH", path);
        record.addSort(user, Record.SORT_UPPER, 1);
        record.addValue("USER", user);
        return record;
    }

    private Record createOutputRecord(String path, int pv, int uu) {
        Record record = new Record();
        record.addGrouping("DATE", "2000-01-01");

        record.addSort(uu, Record.SORT_UPPER, 1);

        record.addValue("PATH", path);
        record.addValue("PV", pv);
        record.addValue("UU", uu);
        return record;
    }

    @Override
    public Summarizer getSummarizer() {
        return new FirstSummarizer();
    }
}
