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
package org.huahin.examples;

import org.huahin.core.Runner;
import org.huahin.examples.pathranking.PathRankingJobTool;
import org.huahin.examples.top10.DescSortJobTool;
import org.huahin.examples.userranking.UserRankingJobTool;

/**
 *
 */
public class Jobs {
    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("[jobName] args...");
            System.exit(-1);
        }

        // Remove the leading argument and call run
        String jobName = args[0];
        String[] newArgs = new String[args.length - 1];
        for (int i = 1; i < args.length; ++i) {
            newArgs[i - 1] = args[i];
        }

        Runner runner = new Runner();
        runner.addJob("DescSort", DescSortJobTool.class);
        runner.addJob("PathRanking", PathRankingJobTool.class);
        runner.addJob("UserRanking", UserRankingJobTool.class);

        int status = runner.run(jobName, newArgs);
        System.exit(status);
    }
}
