/*
// $Id$
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
// Copyright (C) 2011-2012 Julian Hyde
// All Rights Reserved.
*/
package mondrian.server.monitor;

import mondrian.server.Locus;

/**
 * Event concerning the execution of an MDX statement.
 *
 * @version $Id$
 */
public abstract class ExecutionEvent extends Event {
    /**
     * Identifier of the server.
     */
    public final int serverId;

    /**
     * Identifier of the connection.
     */
    public final int connectionId;

    /**
     * Identifier of the statement. Unique for the lifetime of the JVM.
     */
    public final long statementId;

    /**
     * Identifier of the execution. Unique for the lifetime of the JVM.
     */
    public final long executionId;

    /**
     * Creates an ExecutionEvent.
     *
     * @param timestamp Timestamp
     * @param serverId Server id
     * @param connectionId Connection id
     * @param statementId Statement id
     * @param executionId Execution id
     */
    public ExecutionEvent(
        long timestamp,
        int serverId,
        int connectionId,
        long statementId,
        long executionId)
    {
        super(timestamp);
        this.serverId = serverId;
        this.connectionId = connectionId;
        this.statementId = statementId;
        this.executionId = executionId;
    }

    /**
     * Creates an ExecutionEvent, specifying context via a Locus object.
     * The Locus object is not stored.
     *
     * @param timestamp Timestamp
     * @param locus Locus
     */
    public ExecutionEvent(long timestamp, Locus locus) {
        this(
            timestamp,
            locus.getServer().getId(),
            locus.execution.getMondrianStatement().getMondrianConnection()
                .getId(),
            locus.execution.getMondrianStatement().getId(),
            locus.execution.getId());
    }
}

// End ExecutionEvent.java