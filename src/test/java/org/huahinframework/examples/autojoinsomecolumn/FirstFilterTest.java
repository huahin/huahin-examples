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
package org.huahinframework.examples.autojoinsomecolumn;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.huahinframework.core.Filter;
import org.huahinframework.core.io.Record;
import org.huahinframework.core.util.StringUtil;
import org.huahinframework.unit.FilterDriver;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class FirstFilterTest extends FilterDriver {
    private final String[] LABELS = { "USER", "DATE", "REFERER", "URL", "ID1", "ID2" };
    private static final String[] MASTER_LABELS = { "ID1", "ID2", "NAME" };

    private List<String> masterData;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        masterData = JoinPathUtils.createMaster();
    }

    @Test
    public void hit() throws IOException, URISyntaxException {
        String input = "1\t1\t1\t1\t1\t1";

        List<Record> output = new ArrayList<Record>();
        Record record = new Record();
        record.addGrouping("ID", 1);
        record.addSort(1, Record.SORT_LOWER, 1);
        record.addValue("USER", "1");
        record.addValue("NAME", "NAME1");
        output.add(record);

        String[] jm = { "ID1", "ID2"};
        String[] jd = { "ID1", "ID2"};
        setJoin(MASTER_LABELS, jm, jd, true, masterData);

        run(LABELS, StringUtil.TAB, false, input, output);
    }

    @Test
    public void notHit() throws IOException, URISyntaxException {
        String input = "999\t999\t999\t999\t999\t999";
        String[] jm = { "ID1", "ID2"};
        String[] jd = { "ID1", "ID2"};
        setJoin(MASTER_LABELS, jm, jd, true, masterData);
        run(LABELS, StringUtil.TAB, false, input, null);
    }

    @Override
    public Filter getFilter() {
        return new FirstFilter();
    }
}
