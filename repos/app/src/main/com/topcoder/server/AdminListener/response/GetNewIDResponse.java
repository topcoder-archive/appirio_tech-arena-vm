package com.topcoder.server.AdminListener.response;
import com.topcoder.shared.netCommon.CSReader;
import com.topcoder.shared.netCommon.CSWriter;
import java.io.IOException;

/**
 * A response to request for ID for newly created round. Instances of this 
 * class are created by 
 * <code>com.topcoder.server.AdminListener.ContestManagementProcessor</code>
 * in response to <code>GetNewIDRequest</code> and contain the 
 * <code>int</code> value representing the ID that may be assigned to newly 
 * created round.
 *
 * Copyright (c) 2003 TopCoder, Inc.  All rights reserved.
 * 
 * @author  TCSDESIGNER
 * @version 1.0 11/03/2003
 * @since   Admin Tool 2.0
 */
public class GetNewIDResponse extends ContestManagementAck {

    /**
     * An int representing the ID that may be assigned to newly created 
     * round.
     */
    private int newID = 0;

    /**
     * An ID of sequence that was used to generate newID.
     */
    private String sequence;
    
    public GetNewIDResponse() {
        
    }
    
    public void customWriteObject(CSWriter writer) throws IOException {
        super.customWriteObject(writer);
        writer.writeInt(newID);
        writer.writeString(sequence);
    }
    
    public void customReadObject(CSReader reader) throws IOException {
        super.customReadObject(reader);
        newID = reader.readInt();
        sequence = reader.readString();
    }

    /**
     * Constructs new GetNewIDResponse with specified newly generated ID. 
     *
     * @param sequence an ID of sequence that generated new ID
     * @param newID an int representing newly generated ID
     */
    public GetNewIDResponse(String sequence, int newID) {
    	super();
    	this.sequence = sequence;
    	this.newID = newID;
    }

    /**
     * Constructs new instance of GetNewIDResponse specifying the
     * Throwable representing the exception or error occured preventing
     * the successful fulfilment of request.
     *
     * @param errorDetails a Throwable occured during the fulfilment of 
     *        request
     */
    public GetNewIDResponse(Throwable errorDetails) {
    	super( errorDetails);
    }

    /**
     * Gets the ID that was generated by sequence specified in request.
     *
     * @return an int representing newly generated ID.
     */
    public int getNewId() {
        return newID;
    }

    /**
     * Gets the ID of sequence that generated new ID.
     *
     * @param an int reprsenting ID of sequence that was used to generate new
     *        ID.
     */
    public String getSequence() {
        return sequence;
    }
}
