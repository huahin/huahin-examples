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

import org.huahinframework.core.SimpleJob;
import org.huahinframework.core.SimpleJobTool;
import org.huahinframework.core.util.StringUtil;

/**
 *
 */
public class AutoJoinJobTool extends SimpleJobTool {

    @Override
    protected String setInputPath(String[] args) {
        return args[0];
    }

    @Override
    protected String setOutputPath(String[] args) {
        return args[1];
    }

    /* (non-Javadoc)
     * @see org.huahin.core.SimpleJobTool#setup()
     */
    @Override
    protected void setup() throws Exception {
        final String[] labels = { "USER", "DATE", "REFERER", "URL", "ID1", "ID2" };
        final String[] masterLabels = { "ID1", "ID2", "NAME" };

        SimpleJob job1 = addJob(labels, StringUtil.TAB);
        job1.setFilter(FirstFilter.class);
        job1.setSummarizer(FirstSummarizer.class);
        job1.setJoin(masterLabels, "ID1", "ID1", getArgs()[2]);
    }
}
