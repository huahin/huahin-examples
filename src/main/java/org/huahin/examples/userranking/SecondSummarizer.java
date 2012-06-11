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

import org.huahin.core.Summarizer;
import org.huahin.core.Writer;
import org.huahin.core.io.Record;

/**
 *
 */
public class SecondSummarizer extends Summarizer {
    private int rank;

    @Override
    public void init() {
        rank = 1;
    }

    @Override
    public boolean summarizer(Record record, Writer writer)
            throws IOException, InterruptedException {
        if (rank > 3) {
            return true;
        }

        Record emitRecord = new Record();
        emitRecord.addValue("RANK", rank);
        emitRecord.addValue("USER", record.getValueString("USER"));
        emitRecord.addValue("PV", record.getValueInteger("PV"));

        writer.write(emitRecord);

        rank++;
        return false;
    }

    @Override
    public void end(Record values, Writer writer)
            throws IOException, InterruptedException {
    }

    @Override
    public void summarizerSetup() {
    }
}