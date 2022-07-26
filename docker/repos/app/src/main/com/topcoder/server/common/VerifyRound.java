package com.topcoder.server.common;

import java.io.Serializable;

public class VerifyRound implements Serializable {

//    private String m_cacheKey;

    public final String getCacheKey() {
    	return null;
//        return m_cacheKey;
    }

    private int roundId;
    private String roundName;
    private int invitational;
    private int registrationLimit;
    private String status;
    private int roundTypeId;
    private String roundTypeDesc;
    private boolean modified;


    ////////////////////////////////////////////////////////////////////////////////
    public VerifyRound()
            ////////////////////////////////////////////////////////////////////////////////
    {
        this.roundId = -1;
        this.roundName = "";
        this.invitational = -1;
        this.registrationLimit = -1;
        this.status = "";
        this.roundTypeId = -1;
        this.roundTypeDesc = "";
        this.modified = false;

    }

    public static String getCacheKey(int roundId) {
        return "Round." + roundId;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public final void setRoundId(int in) {
        ////////////////////////////////////////////////////////////////////////////////
        this.roundId = in;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public final void setRoundName(String in) {
        ////////////////////////////////////////////////////////////////////////////////
        this.roundName = in;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public final void setInvitational(int in) {
        ////////////////////////////////////////////////////////////////////////////////
        this.invitational = in;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public final void setRegistrationLimit(int in) {
        ////////////////////////////////////////////////////////////////////////////////
        this.registrationLimit = in;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public final void setStatus(String in) {
        ////////////////////////////////////////////////////////////////////////////////
        this.status = in;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public final void setRoundTypeId(int in) {
        ////////////////////////////////////////////////////////////////////////////////
        this.roundTypeId = in;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public final void setRoundTypeDesc(String in) {
        ////////////////////////////////////////////////////////////////////////////////
        this.roundTypeDesc = in;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public final void setModified(boolean modified) {
        ////////////////////////////////////////////////////////////////////////////////
        this.modified = modified;
    }


    ////////////////////////////////////////////////////////////////////////////////
    public final int getRoundId() {
        ////////////////////////////////////////////////////////////////////////////////
        return this.roundId;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public final String getRoundName() {
        ////////////////////////////////////////////////////////////////////////////////
        return this.roundName;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public final int getInvitational() {
        ////////////////////////////////////////////////////////////////////////////////
        return this.invitational;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public int getRegistrationLimit() {
        ////////////////////////////////////////////////////////////////////////////////
        return registrationLimit;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public final String getStatus() {
        ////////////////////////////////////////////////////////////////////////////////
        return this.status;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public final int getRoundTypeId() {
        ////////////////////////////////////////////////////////////////////////////////
        return this.roundTypeId;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public final String getRoundTypeDesc() {
        ////////////////////////////////////////////////////////////////////////////////
        return this.roundTypeDesc;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public final boolean getModified() {
        ////////////////////////////////////////////////////////////////////////////////
        return modified;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public String toString()
            ////////////////////////////////////////////////////////////////////////////////
    {
        StringBuffer str = new StringBuffer(500);
        str.append((new Integer(roundId)).toString());
        str.append(", ");
        str.append(roundName);
        str.append(",");
        str.append(invitational);
        str.append(", ");
        str.append(registrationLimit);
        str.append(", ");
        str.append(status);
        return str.toString();
    }

    ////////////////////////////////////////////////////////////////////////////////
    public boolean equals(VerifyRound round)
            ////////////////////////////////////////////////////////////////////////////////
    {
        return this.roundId == round.getRoundId();
    }
}
