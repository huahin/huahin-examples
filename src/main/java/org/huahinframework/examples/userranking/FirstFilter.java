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
package org.huahinframework.examples.userranking;

import java.io.IOException;
import java.net.URI;

import org.huahinframework.core.Filter;
import org.huahinframework.core.Writer;
import org.huahinframework.core.io.Record;

/**
 *
 */
public class FirstFilter extends Filter {
    @Override
    public void init() {
    }

    @Override
    public void filter(Record record, Writer writer)
            throws IOException, InterruptedException {
        String url = record.getValueString("URL");
        URI uri = URI.create(url);
        String path = uri.getPath();
        if (path == null) {
            return;
        }

        String user = record.getValueString("USER");

        Record emitRecord = new Record();
        emitRecord.addGrouping("PATH", path);
        emitRecord.addSort(user, Record.SORT_LOWER, 1);
        emitRecord.addValue("USER", user);

        writer.write(emitRecord);
    }

    @Override
    public void filterSetup() {
    }
}
