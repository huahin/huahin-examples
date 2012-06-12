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
package org.huahin.examples.top10;

import java.io.IOException;
import java.util.regex.Pattern;

import org.huahin.core.Filter;
import org.huahin.core.SimpleJob;
import org.huahin.core.SimpleJobTool;
import org.huahin.core.Summarizer;
import org.huahin.core.Writer;
import org.huahin.core.io.Record;
import org.huahin.core.util.StringUtil;

/**
 *
 */
public class DescSortJobTool extends SimpleJobTool {
    @Override
    protected String setInputPath(String[] args) {
        return args[0];
    }

    @Override
    protected String setOutputPath(String[] args) {
        return args[1];
    }

    public static class URLFilter extends Filter {
        private static final Pattern PATTERN = Pattern.compile("^https?://localdomain.local/index.html");

        @Override
        public void filter(Record record, Writer writer)
                throws IOException, InterruptedException {
            String url = record.getValueString("URL");
            if (!PATTERN.matcher(url).matches()) {
                return;
            }

            String fullDate = record.getValueString("DATE");
            String date = fullDate.split(" ")[0];

            Record emitRecode = new Record();
            emitRecode.addGrouping("DATE", date);
            emitRecode.addSort(fullDate, Record.SORT_UPPER, 1);
            emitRecode.addValue("DATE", fullDate);
            emitRecode.addValue("URL", url);

            writer.write(emitRecode);
        }

        @Override
        public void filterSetup() {
        }

        @Override
        public void init() {
        }
    }

    public static class URLSummarizer extends Summarizer {
        @Override
        public void summarizer(Writer writer)
                throws IOException, InterruptedException {
            int cnt = 0;
            while (hasNext()) {
                if (cnt == 10) {
                    break;
                }

                writer.write(next(writer));
                cnt++;
            }
        }

        @Override
        public void summarizerSetup() {
        }

        @Override
        public void init() {
        }
    }

    @Override
    protected void setup() throws Exception {
        final String[] labels = new String[] { "USER", "DATE", "REFERER", "URL" };

        SimpleJob job = addJob(labels, StringUtil.TAB);
        job.setFilter(URLFilter.class);
        job.setSummaizer(URLSummarizer.class);
    }
}
