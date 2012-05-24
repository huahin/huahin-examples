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
package org.huahin.examples.ranking;

import java.util.ArrayList;
import java.util.List;

import org.huahin.core.Summarizer;
import org.huahin.core.io.Record;
import org.huahin.unit.SummarizerDriver;
import org.junit.Test;

/**
 *
 */
public class SecondSummarizerTest extends SummarizerDriver {

    @Test
    public void test() {
        int j = 1000;

        List<Record> input = new ArrayList<Record>();
        for (int i = 100; i > 0; i--) {
            input.add(createInputRecord("/index" + i + ".html", j--, i));
        }

        List<Record> output = new ArrayList<Record>();
        j = 1000;
        int rank = 1;
        for (int i = 100; i > 50; i--) {
            output.add(createOutputRecord("/index" + i + ".html", rank++, j--, i));
        }

        run(input, output);
    }

    private Record createInputRecord(String path, int pv, int uu) {
        Record record = new Record();
        record.addGrouping("DATE", "2000-01-01");

        record.addSort(2, Record.SORT_UPPER, 1);

        record.addValue("PATH", path);
        record.addValue("PV", pv);
        record.addValue("UU", uu);
        return record;
    }

    private Record createOutputRecord(String path, int rank, int pv, int uu) {
        Record record = new Record();
        record.addSort(2, Record.SORT_UPPER, 1);

        record.addGrouping("DATE", "2000-01-01");
        record.addValue("PATH", path);
        record.addValue("RANK", rank);
        record.addValue("PV", pv);
        record.addValue("UU", uu);
        return record;
    }

    @Override
    public Summarizer getSummarizer() {
        return new SecondSummarizer();
    }
}
