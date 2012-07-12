package org.huahinframework.examples.wordcount.natural;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

public class WordFilter extends Mapper<LongWritable, Text, Text, IntWritable> {
    private IntWritable ONE = new IntWritable(1);

    public void map(LongWritable key, Text value, Context context)
            throws IOException,InterruptedException {
        for (String s : StringUtils.split(value.toString())) {
            context.write(new Text(s), ONE);
        }
    }
}