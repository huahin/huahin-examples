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
    private final String[] LABELS = { "ID", "USER", "DATE", "REFERER", "URL" };
    private static final String[] MASTER_LABELS = { "ID", "NAME" };

    List<String> masterData = new ArrayList<String>();

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        masterData.add("1\tUSER1");
        masterData.add("2\tUSER2");
        masterData.add("3\tUSER3");
    }

    @Test
    public void hit() throws IOException, URISyntaxException {
        String input = "1\t1\t2000-01-01 00:00:00\t\thttp://localdomain.local/";

        List<Record> output = new ArrayList<Record>();
        Record record = new Record();
        record.addGrouping("ID", "1");
        record.addValue("USER", "1");
        record.addValue("NAME", "USER1");
        output.add(record);

        setBigJoin(MASTER_LABELS, "ID", "ID", masterData);
        run(LABELS, StringUtil.TAB, false, input, output);
    }

    @Test
    public void notHit() throws IOException, URISyntaxException {
        String input = "5\t5\t2000-01-01 00:00:00\t\thttp://localdomain.local/";

        List<Record> output = new ArrayList<Record>();
        Record record = new Record();
        record.addGrouping("ID", "5");
        record.addValue("USER", "5");
        record.addValue("NAME", null);
        output.add(record);

        setBigJoin(MASTER_LABELS, "ID", "ID", masterData);
        run(LABELS, StringUtil.TAB, false, input, output);
    }

    @Override
    public Filter getFilter() {
        return new FirstFilter();
    }
}
