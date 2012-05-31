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
package org.huahin.examples.pathranking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.huahin.core.io.Record;
import org.huahin.core.util.StringUtil;
import org.huahin.examples.pathranking.FirstFilter;
import org.huahin.examples.pathranking.FirstSummarizer;
import org.huahin.examples.pathranking.SecondSummarizer;
import org.huahin.unit.JobDriver;
import org.junit.Test;

/**
 *
 */
public class PathRankingJobToolTest extends JobDriver {
    private final String[] LABELS = new String[] { "USER", "DATE", "REFERER", "URL" };

    @Test
    public void test()
            throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        addJob(LABELS, StringUtil.TAB, false).setFilter(FirstFilter.class)
                                             .setSummaizer(FirstSummarizer.class);
        addJob().setSummaizer(SecondSummarizer.class);

        List<String> input = new ArrayList<String>();
        int uu = 1;
        for (int i = 1; i <= 100; i++) {
            for (int j = uu; j <= 100; j++) {
                input.add(createInputData(String.valueOf(j), "/index" + i + ".html"));
            }
            uu++;
        }

        List<Record> output = new ArrayList<Record>();
        int num = 100;
        for (int i = 1; i <= 50; i++, num--) {
            output.add(createOutputData("/index" + i + ".html", i, num, num));
        }

        run(input, output, true);
    }

    private String createInputData(String user, String path) {
        return user + "\t2000-01-01 00:00:00\t\thttp://localdomain.local" + path;
    }

    private Record createOutputData(String path, int rank, int pv, int uu) {
        Record record = new Record();
        record.addSort(uu, Record.SORT_UPPER, 1);

        record.addGrouping("DATE", "2000-01-01");
        record.addValue("PATH", path);
        record.addValue("RANK", rank);
        record.addValue("PV", pv);
        record.addValue("UU", uu);
        return record;
    }
}
