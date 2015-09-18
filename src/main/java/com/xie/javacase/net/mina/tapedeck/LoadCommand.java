/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package com.xie.javacase.net.mina.tapedeck;

/**
 * Represents the <code>load &lt;tape&gt;</code> command.
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class LoadCommand extends Command {
    public static final String NAME = "load";

    private final int tapeNumber;
    
    public LoadCommand(int tapeNumber) {
        this.tapeNumber = tapeNumber;
    }

    public int getTapeNumber() {
        return tapeNumber;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
}
