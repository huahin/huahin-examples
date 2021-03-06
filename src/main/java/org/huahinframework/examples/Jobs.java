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
package org.huahinframework.examples;

import org.huahinframework.core.Runner;
import org.huahinframework.examples.autojoin.AutoJoinJobTool;
import org.huahinframework.examples.autojoinsomecolumn.AutoSomeJoinJobTool;
import org.huahinframework.examples.bigjoin.BigJoinJobTool;
import org.huahinframework.examples.bigjoin.BigJoinOnlyJoinJobTool;
import org.huahinframework.examples.bigjoinsomecolumn.SomeBigJoinJobTool;
import org.huahinframework.examples.bigjoinsomecolumn.SomeBigJoinOnlyJoinJobTool;
import org.huahinframework.examples.joinpath.JoinPathJobTool;
import org.huahinframework.examples.joinsomecolumnpath.SomeJoinPathJobTool;
import org.huahinframework.examples.metapathranking.MetaPathRankingJobTool;
import org.huahinframework.examples.pathranking.PathRankingJobTool;
import org.huahinframework.examples.top10.DescSortJobTool;
import org.huahinframework.examples.userranking.UserRankingJobTool;
import org.huahinframework.examples.wordcount.huahin.HuahinWordCountTool;
import org.huahinframework.examples.wordcount.natural.NaturalWordCountTool;

/**
 *
 */
public class Jobs {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.addJob("DescSort", DescSortJobTool.class);
        runner.addJob("PathRanking", PathRankingJobTool.class);
        runner.addJob("MetaPathRanking", MetaPathRankingJobTool.class);
        runner.addJob("UserRanking", UserRankingJobTool.class);
        runner.addJob("HuahinWordCount", HuahinWordCountTool.class);
        runner.addJob("NaturalWordCount", NaturalWordCountTool.class);
        runner.addJob("JoinPath", JoinPathJobTool.class);
        runner.addJob("BigJoinOnlyJoin", BigJoinOnlyJoinJobTool.class);
        runner.addJob("BigJoin", BigJoinJobTool.class);
        runner.addJob("SomeJoin", SomeJoinPathJobTool.class);
        runner.addJob("SomeBigJoin", SomeBigJoinJobTool.class);
        runner.addJob("SomeBigJoinOnly", SomeBigJoinOnlyJoinJobTool.class);
        runner.addJob("AutoJoin", AutoJoinJobTool.class);
        runner.addJob("AutoSomeJoin", AutoSomeJoinJobTool.class);

        if (args.length != 3 && args.length != 4) {
            System.err.println("[jobName] args...");
            System.err.println("jobNames ...");
            for (String s : runner.getJobList()) {
                System.err.println("  " + s);
            }
            System.exit(-1);
        }

        // Remove the leading argument and call run
        String jobName = args[0];
        String[] newArgs = new String[args.length - 1];
        for (int i = 1; i < args.length; ++i) {
            newArgs[i - 1] = args[i];
        }

        int status = runner.run(jobName, newArgs);
        System.exit(status);
    }
}
