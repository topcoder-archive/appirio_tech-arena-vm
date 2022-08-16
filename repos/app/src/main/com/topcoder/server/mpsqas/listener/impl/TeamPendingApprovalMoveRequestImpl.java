package com.topcoder.server.mpsqas.listener.impl;

import com.topcoder.server.mpsqas.room.TeamPendingApprovalRoom;
import com.topcoder.netCommon.mpsqas.communication.message.*;
import com.topcoder.netCommon.mpsqas.communication.Peer;
import com.topcoder.netCommon.mpsqas.StatusConstants;
import com.topcoder.netCommon.mpsqas.MessageConstants;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 *
 * @author mitalub
 */
public class TeamPendingApprovalMoveRequestImpl
        extends TeamPendingApprovalMoveRequest
        implements MessageProcessor, TeamPendingApprovalRoom {

    public void process(Peer peer) {
        MPSQASProcessorPeer mpeer = (MPSQASProcessorPeer) peer;

        if (!mpeer.isLoggedIn()) {
            Logger logger = Logger.getLogger(getClass());

            logger.error("Unauthorized user attempted pending approval "
                    + "room move");
            mpeer.sendErrorMessage("Not logged in");
            return;
        }
        if (!mpeer.isAdmin()) {
            Logger logger = Logger.getLogger(getClass());

            logger.error("Non-admin " + mpeer.getUsername() + " (" +
                    mpeer.getUserId()
                    + ") attempted pending approval room move");
            mpeer.sendErrorMessage("Not an admin");
            return;
        }
        mpeer.moveToNewRoom(this);
    }

    public void enter(Peer peer) {
        MPSQASProcessorPeer mpeer = (MPSQASProcessorPeer) peer;

        try {
            ArrayList problemsPendingApproval = mpeer.getProblemServices()
                    .getTeamProblems(MessageConstants.PROBLEMS_WITH_STATUS,
                            StatusConstants.PROPOSAL_PENDING_APPROVAL);
            ArrayList problemsPendingSubmission = mpeer.getProblemServices()
                    .getTeamProblems(MessageConstants.PROBLEMS_WITH_STATUS,
                            StatusConstants.SUBMISSION_PENDING_APPROVAL);

            MainTeamProblemMoveResponse response =
                    new MainTeamProblemMoveResponse();

            response.addProblems(MessageConstants.PENDING_APPROVAL_PROBLEMS,
                    problemsPendingApproval);
            response.addProblems(MessageConstants.PENDING_SUBMISSION_PROBLEMS,
                    problemsPendingSubmission);
            mpeer.sendMessage(response);
        } catch (Exception e) {
            mpeer.sendErrorMessage("Error retrieving problems", e);
        }
    }
}
