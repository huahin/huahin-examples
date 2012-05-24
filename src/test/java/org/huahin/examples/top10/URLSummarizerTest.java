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
package org.huahin.examples.top10;

import java.util.ArrayList;
import java.util.List;

import org.huahin.core.Summarizer;
import org.huahin.core.io.Record;
import org.huahin.unit.SummarizerDriver;
import org.junit.Test;

import org.huahin.examples.top10.DescSortJobTool.URLSummarizer;


/**
 *
 */
public class URLSummarizerTest extends SummarizerDriver {
    @Test
    public void test() {
        List<Record> input = new ArrayList<Record>();
        for (int i = 23; i >= 0; i--) {
            input.add(createRecord(i));
        }

        List<Record> output = new ArrayList<Record>();
        for (int i = 23; i > 13; i--) {
            output.add(createRecord(i));
        }

        run(input, output);
    }

    private Record createRecord(int hour) {
        Record record = new Record();
        record.addGrouping("DATE", "2000-01-01");
        record.addSort("2000-01-01 " + hour + ":00:00", Record.SORT_UPPER, 1);
        record.addValue("DATE", "2000-01-01 " + hour + ":00:00");
        record.addValue("URL", "http://localdomain.local/index.html");
        return record;
    }

    @Override
    public Summarizer getSummarizer() {
        return new URLSummarizer();
    }
}
