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
package org.huahinframework.examples.joinpath;

import java.util.ArrayList;
import java.util.List;

import org.huahinframework.core.util.StringUtil;

/**
 *
 */
public class JoinPathUtils {
    public static List<String> createMaster() {
        List<String> l = new ArrayList<String>();
        l.add("^https?://localdomain.local/$|^https?://localdomain.local/\\?.*" + StringUtil.TAB + "TOP PAGE");
        l.add("^https?://localdomain.local/search.*" + StringUtil.TAB + "SEARCH");
        l.add("^https?://localdomain.local/watch.*" + StringUtil.TAB + "WATCH");
        l.add("^https?://localdomain.local/bbs.html.*" + StringUtil.TAB + "BBS");
        l.add("^https?://localdomain.local/campaign.*" + StringUtil.TAB + "CAMPAIGN");
        return l;
    }
}
