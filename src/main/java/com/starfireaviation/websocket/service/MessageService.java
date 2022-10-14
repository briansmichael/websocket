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

package com.starfireaviation.websocket.service;

import com.starfireaviation.model.Event;
import com.starfireaviation.model.Message;
import com.starfireaviation.model.NotificationEventType;
import com.starfireaviation.model.Question;
import com.starfireaviation.model.Quiz;
import com.starfireaviation.model.User;
import com.starfireaviation.websocket.model.WebsocketMessage;
import lombok.extern.slf4j.Slf4j;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * MessageService.
 */
@Slf4j
public class MessageService {

    /**
     * EVENT_SERVICE.
     */
    public static final String EVENT_SERVICE = "EVENT";

    /**
     * QUESTION_SERVICE.
     */
    public static final String QUESTION_SERVICE = "QUESTION";

    /**
     * QUIZ_SERVICE.
     */
    public static final String QUIZ_SERVICE = "QUIZ";

    /**
     * REFERENCE_MATERIAL_SERVICE.
     */
    public static final String REFERENCE_MATERIAL_SERVICE = "REFERENCE_MATERIAL";

    /**
     * USER_SERVICE.
     */
    public static final String USER_SERVICE = "USER";

    /**
     * GET_OPERATION.
     */
    public static final String GET_OPERATION = "GET";

    /**
     * Map of Messages.
     */
    private Map<Long, List<WebsocketMessage>> messagesMap;

    /**
     * Sends a message for an upcoming event.
     *
     * @param message Message
     */
    public void sendEventUpcomingMsg(final Message message) {
        final User user = getUser(message);
        final Event event = getEvent(message);
        final WebsocketMessage websocketMessage = new WebsocketMessage();
        websocketMessage.setUserId(user.getId());
        websocketMessage.setService(EVENT_SERVICE);
        websocketMessage.setOperation(GET_OPERATION);
        websocketMessage.setEventType(NotificationEventType.EVENT_UPCOMING);
        websocketMessage.setParameters(List.of(event.getId().toString()));
        websocketMessage.setTimestamp(Instant.now());

        sendMessage(websocketMessage);
    }

    /**
     * Sends a message to a user that an event has started.
     *
     * @param message Message
     */
    public void sendEventStartMsg(final Message message) {
        final User user = getUser(message);
        final Event event = getEvent(message);
        final WebsocketMessage websocketMessage = new WebsocketMessage();
        websocketMessage.setUserId(user.getId());
        websocketMessage.setService(EVENT_SERVICE);
        websocketMessage.setOperation(GET_OPERATION);
        websocketMessage.setEventType(NotificationEventType.EVENT_START);
        websocketMessage.setParameters(List.of(event.getId().toString()));
        websocketMessage.setTimestamp(Instant.now());

        sendMessage(websocketMessage);
    }

    /**
     * Sends a message to RSVP for an upcoming event.
     *
     * @param message Message
     */
    public void sendEventRSVPMsg(final Message message) {
        final User user = getUser(message);
        final Event event = getEvent(message);
        final WebsocketMessage websocketMessage = new WebsocketMessage();
        websocketMessage.setUserId(user.getId());
        websocketMessage.setService(EVENT_SERVICE);
        websocketMessage.setOperation(GET_OPERATION);
        websocketMessage.setEventType(NotificationEventType.EVENT_RSVP);
        websocketMessage.setParameters(List.of(event.getId().toString()));
        websocketMessage.setTimestamp(Instant.now());

        sendMessage(websocketMessage);
    }

    /**
     * Sends a message that a question has been asked.
     *
     * @param message Message
     */
    public void sendQuestionAskedMsg(final Message message) {
        final User user = getUser(message);
        final Question question = getQuestion(message);
        final Quiz quiz = getQuiz(message);
        final WebsocketMessage websocketMessage = new WebsocketMessage();
        websocketMessage.setUserId(user.getId());
        websocketMessage.setService(QUESTION_SERVICE);
        websocketMessage.setOperation(GET_OPERATION);
        websocketMessage.setEventType(NotificationEventType.QUESTION_ASKED);
        final List<String> parameters = new ArrayList<>();
        parameters.add(question.getId().toString());
        if (quiz.getId() != null) {
            parameters.add(quiz.getId().toString());
        }
        websocketMessage.setParameters(parameters);
        websocketMessage.setTimestamp(Instant.now());

        sendMessage(websocketMessage);
    }

    /**
     * Sends a message for registering for an upcoming event.
     *
     * @param message Message
     */
    public void sendEventRegisterMsg(final Message message) {
        final User user = getUser(message);
        final Event event = getEvent(message);
        final WebsocketMessage websocketMessage = new WebsocketMessage();
        websocketMessage.setUserId(user.getId());
        websocketMessage.setService(EVENT_SERVICE);
        websocketMessage.setOperation(GET_OPERATION);
        websocketMessage.setEventType(NotificationEventType.EVENT_REGISTER);
        websocketMessage.setParameters(List.of(event.getId().toString()));
        websocketMessage.setTimestamp(Instant.now());

        sendMessage(websocketMessage);
    }

    /**
     * Sends a message for unregistering from an upcoming event.
     *
     * @param message Message
     */
    public void sendEventUnregisterMsg(final Message message) {
        final User user = getUser(message);
        final Event event = getEvent(message);
        final WebsocketMessage websocketMessage = new WebsocketMessage();
        websocketMessage.setUserId(user.getId());
        websocketMessage.setService(EVENT_SERVICE);
        websocketMessage.setOperation(GET_OPERATION);
        websocketMessage.setEventType(NotificationEventType.EVENT_UNREGISTER);
        websocketMessage.setParameters(List.of(event.getId().toString()));
        websocketMessage.setTimestamp(Instant.now());

        sendMessage(websocketMessage);
    }

    /**
     * Sends a message for user deletion.
     *
     * @param message Message
     */
    public void sendUserDeleteMsg(final Message message) {
        final User user = getUser(message);
        final WebsocketMessage websocketMessage = new WebsocketMessage();
        websocketMessage.setUserId(user.getId());
        websocketMessage.setService(USER_SERVICE);
        websocketMessage.setOperation(GET_OPERATION);
        websocketMessage.setEventType(NotificationEventType.USER_DELETE);
        websocketMessage.setParameters(List.of(user.getId().toString()));
        websocketMessage.setTimestamp(Instant.now());

        sendMessage(websocketMessage);
    }

    /**
     * Sends a message for quiz completion.
     *
     * @param message Message
     */
    public void sendQuizCompleteMsg(final Message message) {
        final User user = getUser(message);
        final Quiz quiz = getQuiz(message);
        final WebsocketMessage websocketMessage = new WebsocketMessage();
        websocketMessage.setUserId(user.getId());
        websocketMessage.setService(QUIZ_SERVICE);
        websocketMessage.setOperation(GET_OPERATION);
        websocketMessage.setEventType(NotificationEventType.QUIZ_COMPLETE);
        final List<String> parameters = new ArrayList<>();
        parameters.add(user.getId().toString());
        if (quiz.getId() != null) {
            parameters.add(quiz.getId().toString());
        }
        websocketMessage.setParameters(parameters);
        websocketMessage.setTimestamp(Instant.now());

        sendMessage(websocketMessage);
    }

    /**
     * Sends a message for user settings verified.
     *
     * @param message Message
     */
    public void sendUserSettingsVerifiedMsg(final Message message) {
        final User user = getUser(message);
        final WebsocketMessage websocketMessage = new WebsocketMessage();
        websocketMessage.setUserId(user.getId());
        websocketMessage.setService(USER_SERVICE);
        websocketMessage.setOperation(GET_OPERATION);
        websocketMessage.setEventType(NotificationEventType.USER_VERIFIED);
        websocketMessage.setParameters(List.of(user.getId().toString()));
        websocketMessage.setTimestamp(Instant.now());

        sendMessage(websocketMessage);
    }

    /**
     * Sends a message for user settings changed.
     *
     * @param message Message
     */
    public void sendUserSettingsChangeMsg(final Message message) {
        final User user = getUser(message);
        final WebsocketMessage websocketMessage = new WebsocketMessage();
        websocketMessage.setUserId(user.getId());
        websocketMessage.setService(USER_SERVICE);
        websocketMessage.setOperation(GET_OPERATION);
        websocketMessage.setEventType(NotificationEventType.USER_SETTINGS);
        websocketMessage.setParameters(List.of(user.getId().toString()));
        websocketMessage.setTimestamp(Instant.now());

        sendMessage(websocketMessage);
    }

    /**
     * Sends a password reset message.
     *
     * @param message Message
     */
    public void sendPasswordResetMsg(final Message message) {
        final User user = getUser(message);
        final WebsocketMessage websocketMessage = new WebsocketMessage();
        websocketMessage.setUserId(user.getId());
        websocketMessage.setService(USER_SERVICE);
        websocketMessage.setOperation(GET_OPERATION);
        websocketMessage.setEventType(NotificationEventType.PASSWORD_RESET);
        websocketMessage.setParameters(List.of(user.getId().toString()));
        websocketMessage.setTimestamp(Instant.now());

        sendMessage(websocketMessage);
    }

    /**
     * Sends a last minute message to register/RSVP for an upcoming event.
     *
     * @param message Message
     */
    public void sendEventLastMinRegistrationMsg(final Message message) {
        // Not implemented
    }

    /**
     * Sends a message to display information.
     *
     * @param message Message
     * @param referenceId Reference ID
     * @param notificationEventType NotificationEventType
     */
    public void sendDisplayMsg(final Message message, final Long referenceId,
                               final NotificationEventType notificationEventType) {
        final User user = getUser(message);
        final WebsocketMessage websocketMessage = new WebsocketMessage();
        websocketMessage.setUserId(user.getId());
        websocketMessage.setOperation(GET_OPERATION);
        websocketMessage.setEventType(notificationEventType);
        websocketMessage.setTimestamp(Instant.now());
        switch (notificationEventType) {
            case LESSON_DISPLAYED:
                websocketMessage.setParameters(List.of(referenceId.toString()));
                websocketMessage.setService(USER_SERVICE);
                break;
            case QUESTION_DISPLAYED:
                websocketMessage.setParameters(List.of(referenceId.toString()));
                websocketMessage.setService(QUESTION_SERVICE);
                break;
            case REFERENCE_MATERIAL_DISPLAYED:
                websocketMessage.setParameters(List.of(referenceId.toString()));
                websocketMessage.setService(REFERENCE_MATERIAL_SERVICE);
                break;
            default:
                websocketMessage.setService(null);
                websocketMessage.setOperation(null);
                websocketMessage.setEventType(NotificationEventType.HOME_DISPLAYED);
        }

        sendMessage(websocketMessage);
    }

    /**
     * Sends a message to a user that an event has completed.
     *
     * @param message Message
     */
    public void sendEventCompletedMsg(final Message message) {
        // Not implemented
    }

    /**
     * Adds message to message queue.
     *
     * @param websocketMessage WebSocketMessage
     */
    private void sendMessage(final WebsocketMessage websocketMessage) {
        List<WebsocketMessage> websocketMessages;
        if (messagesMap.containsKey(websocketMessage.getUserId())) {
            websocketMessages = messagesMap.get(websocketMessage.getUserId());
        } else {
            websocketMessages = new ArrayList<>();
        }
        websocketMessages.add(websocketMessage);
        messagesMap.put(websocketMessage.getUserId(), websocketMessages);
    }

    private Event getEvent(final Message message) {
        return null;
    }

    private User getUser(final Message message) {
        return null;
    }

    private Question getQuestion(final Message message) {
        return null;
    }

    private Quiz getQuiz(final Message message) {
        return null;
    }
}
