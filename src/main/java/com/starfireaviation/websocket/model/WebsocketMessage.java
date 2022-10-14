/*
 *  Copyright (C) 2022 Starfire Aviation, LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.starfireaviation.websocket.model;

import com.starfireaviation.model.NotificationEventType;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * WebSocketMessage.
 */
@Data
public class WebsocketMessage implements Serializable {

    /**
     * Default SerialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * User message is intended for.
     */
    private Long userId;

    /**
     * NotificationEventType.
     */
    private NotificationEventType eventType;

    /**
     * Service.
     */
    private String service;

    /**
     * Operation (a.k.a. method).
     */
    private String operation;

    /**
     * List of parameters.
     */
    private List<String> parameters;

    /**
     * Instant message was created.
     */
    private Instant timestamp;

}
