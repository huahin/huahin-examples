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
package org.huahin.examples.userranking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.huahin.core.io.Record;
import org.huahin.core.util.StringUtil;
import org.huahin.examples.userranking.FirstFilter;
import org.huahin.examples.userranking.FirstSummarizer;
import org.huahin.examples.userranking.SecondSummarizer;
import org.huahin.unit.JobDriver;
import org.junit.Test;

/**
 *
 */
public class UserRankingJobToolTest extends JobDriver {
    private final String[] LABELS = new String[] { "USER", "DATE", "REFERER", "URL" };

    @Test
    public void test()
            throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        addJob(LABELS, StringUtil.TAB, false).setFilter(FirstFilter.class)
                                             .setSummaizer(FirstSummarizer.class);
        addJob().setSummaizer(SecondSummarizer.class);

        List<String> input = new ArrayList<String>();
        input.add(createInputData("1", "/index.html"));
        input.add(createInputData("1", "/index.html"));
        input.add(createInputData("1", "/index.html"));
        input.add(createInputData("1", "/index.html"));
        input.add(createInputData("1", "/index.html"));

        input.add(createInputData("2", "/index.html"));
        input.add(createInputData("2", "/index.html"));
        input.add(createInputData("2", "/index.html"));
        input.add(createInputData("2", "/index.html"));

        input.add(createInputData("3", "/index.html"));
        input.add(createInputData("3", "/index.html"));
        input.add(createInputData("3", "/index.html"));

        input.add(createInputData("4", "/index.html"));
        input.add(createInputData("4", "/index.html"));

        input.add(createInputData("5", "/index.html"));

        input.add(createInputData("5", "/index2.html"));
        input.add(createInputData("5", "/index2.html"));
        input.add(createInputData("5", "/index2.html"));
        input.add(createInputData("5", "/index2.html"));
        input.add(createInputData("5", "/index2.html"));

        input.add(createInputData("4", "/index2.html"));
        input.add(createInputData("4", "/index2.html"));
        input.add(createInputData("4", "/index2.html"));
        input.add(createInputData("4", "/index2.html"));

        input.add(createInputData("3", "/index2.html"));
        input.add(createInputData("3", "/index2.html"));
        input.add(createInputData("3", "/index2.html"));

        input.add(createInputData("2", "/index2.html"));
        input.add(createInputData("2", "/index2.html"));

        input.add(createInputData("1", "/index2.html"));

        List<Record> output = new ArrayList<Record>();
        output.add(createOutputData("/index.html", "1", 1, 5));
        output.add(createOutputData("/index.html", "2", 2, 4));
        output.add(createOutputData("/index.html", "3", 3, 3));
        output.add(createOutputData("/index2.html", "5", 1, 5));
        output.add(createOutputData("/index2.html", "4", 2, 4));
        output.add(createOutputData("/index2.html", "3", 3, 3));

        run(input, output, true);
    }

    private String createInputData(String user, String path) {
        return user + "\t2000-01-01 00:00:00\t\thttp://localdomain.local" + path;
    }

    private Record createOutputData(String path, String user, int rank, int pv) {
        Record record = new Record();
        record.addGrouping("PATH", path);
        record.addSort(pv, Record.SORT_UPPER, 1);
        record.addValue("RANK", rank);
        record.addValue("USER", user);
        record.addValue("PV", pv);
        return record;
    }
}
