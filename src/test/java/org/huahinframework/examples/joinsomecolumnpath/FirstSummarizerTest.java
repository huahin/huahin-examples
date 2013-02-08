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
package org.huahinframework.examples.joinsomecolumnpath;

import java.util.ArrayList;
import java.util.List;

import org.huahinframework.core.Summarizer;
import org.huahinframework.core.io.Record;
import org.huahinframework.unit.SummarizerDriver;
import org.junit.Test;

/**
*
*/
public class FirstSummarizerTest extends SummarizerDriver {

    @Test
    public void test() {
        List<Record> input = new ArrayList<Record>();
        input.add(createInputRecord("TOP PAGE", "1"));
        input.add(createInputRecord("TOP PAGE", "1"));
        input.add(createInputRecord("TOP PAGE", "1"));

        input.add(createInputRecord("TOP PAGE", "2"));
        input.add(createInputRecord("TOP PAGE", "2"));

        input.add(createInputRecord("TOP PAGE", "3"));

        List<Record> output = new ArrayList<Record>();
        output.add(createOutputRecord("TOP PAGE", 6, 3));

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
