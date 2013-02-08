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
package org.huahinframework.examples.bigjoin;

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
public class BigJoinJobToolTest extends JobDriver {
    private final String[] LABELS = { "ID", "USER", "DATE", "REFERER", "URL" };
    private static final String[] MASTER_LABELS = { "ID", "NAME" };

    private List<String> masterData = new ArrayList<String>();

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        masterData.add("1\tUSER1");
        masterData.add("2\tUSER2");
        masterData.add("3\tUSER3");
        masterData.add("4\tUSER4");
    }

    @Test
    public void test()
            throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, URISyntaxException {
        addJob(LABELS, StringUtil.TAB, false).setFilter(FirstFilter.class);

        List<String> input = new ArrayList<String>();
        input.add(createInputData(1));
        input.add(createInputData(2));
        input.add(createInputData(3));
        input.add(createInputData(4));
        input.add(createInputData(5));

        List<Record> output = new ArrayList<Record>();
        output.add(createOutputData(1, false));
        output.add(createOutputData(2, false));
        output.add(createOutputData(3, false));
        output.add(createOutputData(4, false));
        output.add(createOutputData(5, true));

        setBigJoin(MASTER_LABELS, "ID", "ID", masterData);

        run(input, output, true);
    }

    private String createInputData(int i) {
        return i + "\t"+ i + "\t2000-01-01 00:00:00\t\thttp://localdomain.local/";
    }

    private Record createOutputData(int i, boolean isNull) {
        Record r = new Record();
        r.addGrouping("ID", String.valueOf(i));
        r.addValue("USER", String.valueOf(i));

        if (isNull) {
            r.addValue("NAME", null);
        } else {
            r.addValue("NAME", "USER" + i);
        }
        return r;
    }
}
