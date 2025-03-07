/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.agent.core;

import org.apache.shenyu.agent.api.config.ShenyuAgentConfig;
import org.apache.shenyu.agent.api.entity.PointCutConfig;
import org.apache.shenyu.agent.core.yaml.ShenyuYamlEngine;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

import static org.junit.Assert.assertNotNull;

/**
 * The type Shenyu yaml engine test.
 */
public final class ShenyuYamlEngineTest {
    
    @Test
    public void testAgentConfig() throws URISyntaxException, IOException {
        File file = new File(Objects.requireNonNull(this.getClass().getResource("/shenyu-agent-test.yaml")).toURI());
        ShenyuAgentConfig shenyuAgentConfig = ShenyuYamlEngine.agentConfig(file);
        assertNotNull(shenyuAgentConfig);
        File tracing = new File(Objects.requireNonNull(this.getClass().getResource("/tracing-point-test.yaml")).toURI());
        PointCutConfig cutConfig = ShenyuYamlEngine.unmarshal(tracing, PointCutConfig.class);
        assertNotNull(cutConfig);
    }
}
