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
        run(createRecord(100), createRecord(10));
    }

    private List<Record> createRecord(int num) {
        List<Record> l = new ArrayList<Record>();
        for (int i = 1; i <= 10; i++) {
            Record r = new Record();
            r.addGrouping("ID", 1);
            r.addSort(i, Record.SORT_LOWER, 1);
            r.addValue("USER", i);
            r.addValue("NAME", "NAME" + i);
            l.add(r);
        }
        return l;
    }

    @Override
    public Summarizer getSummarizer() {
        return new FirstSummarizer();
    }
}
