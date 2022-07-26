/*
 * Author: Michael Cervantes (emcee)
 * Date: Jun 10, 2002
 * Time: 2:07:44 AM
 */
package com.topcoder.server.AdminListener.request;

import com.topcoder.shared.netCommon.CSReader;
import com.topcoder.shared.netCommon.CSWriter;
import java.io.IOException;

public class DeleteContestRequest extends ContestManagementRequest {

    private int id;
    
    public DeleteContestRequest() {
        
    }
    
    public void customWriteObject(CSWriter writer) throws IOException {
        writer.writeInt(id);
    }
    
    public void customReadObject(CSReader reader) throws IOException {
        id = reader.readInt();
    }

    public DeleteContestRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
