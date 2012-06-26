/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.huahinframework.examples.userranking;

import java.util.ArrayList;
import java.util.List;

import org.huahinframework.core.Summarizer;
import org.huahinframework.core.io.Record;
import org.huahinframework.unit.SummarizerDriver;
import org.huahinframework.examples.userranking.SecondSummarizer;
import org.junit.Test;

/**
 *
 */
public class SecondSummarizerTest extends SummarizerDriver {

    @Test
    public void test() {
        List<Record> input = new ArrayList<Record>();
        input.add(createInputRecord("/index.html", "1", 90));
        input.add(createInputRecord("/index.html", "2", 80));
        input.add(createInputRecord("/index.html", "3", 70));
        input.add(createInputRecord("/index.html", "4", 60));
        input.add(createInputRecord("/index.html", "5", 50));

        List<Record> output = new ArrayList<Record>();
        output.add(createOutputRecord("/index.html", "1", 1, 90));
        output.add(createOutputRecord("/index.html", "2", 2, 80));
        output.add(createOutputRecord("/index.html", "3", 3, 70));

        run(input, output);
    }

    private Record createInputRecord(String path, String user, int pv) {
        Record record = new Record();
        record.addGrouping("PATH", path);
        record.addSort(pv, Record.SORT_UPPER, 1);
        record.addValue("USER", user);
        record.addValue("PV", pv);
        return record;
    }

    private Record createOutputRecord(String path, String user, int rank, int pv) {
        Record record = new Record();
        record.addGrouping("PATH", path);
        record.addSort(2, Record.SORT_UPPER, 1);
        record.addValue("RANK", rank);
        record.addValue("USER", user);
        record.addValue("PV", pv);
        return record;
    }

    @Override
    public Summarizer getSummarizer() {
        return new SecondSummarizer();
    }
}
