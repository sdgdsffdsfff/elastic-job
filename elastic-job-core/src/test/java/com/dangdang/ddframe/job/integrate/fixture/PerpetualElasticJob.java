/**
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.job.integrate.fixture;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.dangdang.ddframe.job.api.AbstractPerpetualElasticJob;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;

public final class PerpetualElasticJob extends AbstractPerpetualElasticJob<String> {
    
    private static Set<String> processedData = new CopyOnWriteArraySet<>();
    
    private static List<String> result = Arrays.asList("data0", "data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9");
    
    @Override
    protected List<String> fetchData(final JobExecutionMultipleShardingContext context) {
        if (processedData.isEmpty()) {
            return result;
        } else {
            return null;
        }
    }
    
    @Override
    protected boolean processData(final JobExecutionMultipleShardingContext context, final String data) {
        processedData.add(data);
        return true;
    }
    
    public static boolean isCompleted() {
        return result.size() == processedData.size();
    }
    
    public static void reset() {
        processedData.clear();
    }
}
