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

import org.huahin.core.Summarizer;
import org.huahin.core.Writer;
import org.huahin.core.io.Record;

/**
 *
 */
public class FirstSummarizer extends Summarizer {
    private int pv;
    private int uu;
    private String previousUser;

    @Override
    public void init() {
        pv = 0;
        uu = 0;
        previousUser = null;
    }

    @Override
    public boolean summarizer(Record record, Writer writer)
            throws IOException, InterruptedException {
        String user = record.getValueString("USER");
        if (!user.equals(previousUser)) {
            uu++;
            previousUser = user;
        }

        pv++;
        return false;
    }

    @Override
    public void end(Record record, Writer writer)
            throws IOException, InterruptedException {
        Record emitRecord = new Record();
        emitRecord.addGrouping("DATE", record.getGroupingString("DATE"));

        emitRecord.addSort(uu, Record.SORT_UPPER, 1);

        emitRecord.addValue("PATH", record.getGroupingString("PATH"));
        emitRecord.addValue("PV", pv);
        emitRecord.addValue("UU", uu);

        writer.write(emitRecord);
    }

    @Override
    public void summarizerSetup() {
    }
}
