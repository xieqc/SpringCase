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
package com.xie.javacase.net.mina.sumup.codec;

import com.xie.javacase.net.mina.sumup.message.ResultMessage;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

/**
 * A {@link org.apache.mina.filter.codec.demux.MessageEncoder} that encodes {@link ResultMessage}.
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class ResultMessageEncoder<T extends ResultMessage> extends AbstractMessageEncoder<T> {
    public ResultMessageEncoder() {
        super(Constants.RESULT);
    }

    @Override
    protected void encodeBody(IoSession session, T message, IoBuffer out) {
        if (message.isOk()) {
            out.putShort((short) Constants.RESULT_OK);
            out.putInt(message.getValue());
        } else {
            out.putShort((short) Constants.RESULT_ERROR);
        }
    }

    public void dispose() throws Exception {
    }
}
