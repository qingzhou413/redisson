/**
 * Copyright 2018 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.api;

import java.util.List;

import org.reactivestreams.Publisher;
import org.redisson.api.listener.MessageListener;
import org.redisson.api.listener.StatusListener;

/**
 * Reactive interface for Publish Subscribe object. Messages are delivered to all message listeners across Redis cluster.
 *
 * @author Nikita Koksharov
 *
 * @param <M> the type of message object
 */
public interface RTopicReactive<M> {

    /**
     * Get topic channel names
     *
     * @return channel names
     */
    List<String> getChannelNames();

    /**
     * Publish the message to all subscribers of this topic asynchronously
     *
     * @param message to send
     * @return the <code>Future</code> object with number of clients that received the message
     */
    Publisher<Long> publish(M message);

    /**
     * Subscribes to status changes of this topic
     *
     * @param listener for messages
     * @return listener id
     * @see org.redisson.api.listener.StatusListener
     */
    Publisher<Integer> addListener(StatusListener listener);

    /**
     * Subscribes to this topic.
     * <code>MessageListener.onMessage</code> is called when any message
     * is published on this topic.
     *
     * @param listener for messages
     * @return locally unique listener id
     * @see org.redisson.api.listener.MessageListener
     */
    Publisher<Integer> addListener(MessageListener<M> listener);

    /**
     * Removes the listener by <code>id</code> for listening this topic
     *
     * @param listenerId - listener id
     */
    void removeListener(int listenerId);
}
