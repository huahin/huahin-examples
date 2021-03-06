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

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.huahinframework.core.io.Record;
import org.huahinframework.core.util.StringUtil;
import org.huahinframework.unit.JobDriver;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class JoinPathJobToolTest extends JobDriver {
    private final String[] LABELS = { "USER", "DATE", "REFERER", "URL", "ID" };
    private static final String[] MASTER_LABELS = { "URL", "ID", "NAME" };

    private List<String> masterData;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        masterData = JoinPathUtils.createMaster();
    }

    @Test
    public void test()
            throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, URISyntaxException {
        addJob(LABELS, StringUtil.TAB, false).setFilter(FirstFilter.class)
                                             .setSummarizer(FirstSummarizer.class);

        List<String> input = new ArrayList<String>();
        createInputData(1, 50, 32, "/", input);
        createInputData(2, 43, 21, "/search?p=foo", input);
        createInputData(3, 35, 20, "/watch/index.html", input);
        createInputData(4, 39, 10, "/bbs.html?page=100", input);
        createInputData(5, 20, 8, "/campaign/1.html", input);
        createInputData(5, 27, 6, "/campaign/2.html", input);
        createInputData(-1, 34, 19, "/foo.html", input);

        List<Record> output = new ArrayList<Record>();
        output.add(createOutputData("BBS", 39, 10));
        output.add(createOutputData("CAMPAIGN", 47, 8));
        output.add(createOutputData("SEARCH", 43, 21));
        output.add(createOutputData("TOP PAGE", 50, 32));
        output.add(createOutputData("WATCH", 35, 20));

        String[] jm = { "URL", "ID" };
        String[] jd = { "URL", "ID" };
        setSimpleJoin(MASTER_LABELS, jm, jd, true, masterData);

        run(input, output, true);
    }

    private void createInputData(int id, int pv, int uu, String path, List<String> input) {
        int i = 1;
        for (; i <= uu; i++) {
            input.add(i + "\t2000-01-01 00:00:00\t\thttp://localdomain.local" + path + "\t" + id);
        }
        for (; i <= pv; i++) {
            input.add(1 + "\t2000-01-01 00:00:00\t\thttp://localdomain.local" + path + "\t" + id);
        }
    }

    private Record createOutputData(String path, int pv, int uu) {
        Record record = new Record();
        record.addGrouping("DATE", "2000-01-01");
        record.addValue("PATH", path);
        record.addValue("PV", pv);
        record.addValue("UU", uu);
        return record;
    }
}
