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
package org.huahinframework.examples.pathranking;

import java.io.IOException;

import org.huahinframework.core.Summarizer;
import org.huahinframework.core.io.Record;
import org.huahinframework.core.writer.Writer;

/**
 *
 */
public class SecondSummarizer extends Summarizer {
    @Override
    public void init() {
    }

    @Override
    public void summarize(Writer writer)
            throws IOException, InterruptedException {
        int rank = 1;
        while (hasNext()) {
            if (rank > 50) {
                break;
            }

            Record record = next(writer);
            Record emitRecord = new Record();
            emitRecord.addValue("PATH", record.getValueString("PATH"));
            emitRecord.addValue("RANK", rank);
            emitRecord.addValue("PV", record.getValueInteger("PV"));
            emitRecord.addValue("UU", record.getValueInteger("UU"));

            writer.write(emitRecord);
            rank++;
        }
    }

    @Override
    public void summarizerSetup() {
    }
}
