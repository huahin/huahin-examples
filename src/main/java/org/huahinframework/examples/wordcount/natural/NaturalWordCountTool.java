package org.huahinframework.examples.wordcount.natural;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.huahinframework.core.SimpleJob;
import org.huahinframework.core.SimpleJobTool;

public class NaturalWordCountTool extends SimpleJobTool {
    @Override
    protected String setInputPath(String[] args) {
        return args[0];
    }

    @Override
    protected String setOutputPath(String[] args) {
        return args[1];
    }

    @Override
    protected void setup() throws Exception {
        SimpleJob job = addJob(true);
        job.setMapperClass(WordFilter.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setCombinerClass(WordSummarizer.class);

        job.setReducerClass(WordSummarizer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
    }
}
