package org.huahinframework.examples.wordcount.huahin;

import org.huahinframework.core.SimpleJob;
import org.huahinframework.core.SimpleJobTool;

public class HuahinWordCountTool extends SimpleJobTool {
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
        SimpleJob job1 = addJob(" ");
        job1.setFilter(WordFilter.class);
        job1.setSummaizer(WordSummarizer.class, true);
    }
}
