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
package org.huahinframework.examples.top10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.huahinframework.core.io.Record;
import org.huahinframework.core.util.StringUtil;
import org.huahinframework.unit.JobDriver;
import org.junit.Test;

import org.huahinframework.examples.top10.DescSortJobTool.URLFilter;
import org.huahinframework.examples.top10.DescSortJobTool.URLSummarizer;

/**
 *
 */
public class DescSortJobToolTest extends JobDriver {
    private final String[] LABELS = new String[] { "USER", "DATE", "REFERER", "URL" };
    @Test
    public void test()
            throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        addJob(LABELS, StringUtil.TAB, false).setFilter(URLFilter.class)
                                             .setSummarizer(URLSummarizer.class);

        List<String> input = new ArrayList<String>();
        for (int i = 23; i >= 0; i--) {
            input.add(createString(i));
        }

        List<Record> output = new ArrayList<Record>();
        for (int i = 23; i > 13; i--) {
            output.add(createRecord(i));
        }

        run(input, output, true);
    }

    private String createString(int hour) {
        return "1\t2000-01-01 " +
               String.format("%02d", hour) +
               ":00:00\t\thttp://localdomain.local/index.html";
    }

    private Record createRecord(int hour) {
        Record record = new Record();
        record.addGrouping("DATE", "2000-01-01");
        record.addSort("2000-01-01 " + hour + ":00:00", Record.SORT_UPPER, 1);
        record.addValue("DATE", "2000-01-01 " + hour + ":00:00");
        record.addValue("URL", "http://localdomain.local/index.html");
        return record;
    }

}
