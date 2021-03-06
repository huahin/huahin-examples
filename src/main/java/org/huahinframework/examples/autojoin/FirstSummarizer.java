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
package org.huahinframework.examples.autojoin;

import java.io.IOException;

import org.huahinframework.core.Summarizer;
import org.huahinframework.core.writer.Writer;

/**
 *
 */
public class FirstSummarizer extends Summarizer {
    @Override
    public void init() {
    }

    @Override
    public void summarize(Writer writer)
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
}
