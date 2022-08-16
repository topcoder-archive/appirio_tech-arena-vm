/**
 * TotalChangeSupport.java
 *
 * Description:		Event set support class for TotalChangeListener.
 Manages listener registration and contains fire functions.
 * @author			Tim "Pops" Roberts
 * @version			1.0
 */

package com.topcoder.client.spectatorApp.scoreboard.model;


import java.util.ArrayList;

/**
 * TotalChangeSupport bottlenecks support for classes that fire events to
 * TotalChangeListener listeners.
 */

public class TotalChangeSupport implements java.io.Serializable {

    /** Holder for all listeners */
    private ArrayList totalChangeListeners = new ArrayList();

    /**
     * Adds a listener
     *
     * @param listener the listener to be added
     */
    public synchronized void addTotalChangeListener(TotalChangeListener listener) {
        // add a listener if it is not already registered
        if (!totalChangeListeners.contains(listener)) {
            totalChangeListeners.add(listener);
        }
    }

    /**
     * Removes a listener
     *
     * @param listener the listener to be removed
     */
    public synchronized void removeTotalChangeListener(TotalChangeListener listener) {
        // remove it if it is registered
        int pos = totalChangeListeners.indexOf(listener);
        if (pos >= 0) {
            totalChangeListeners.remove(pos);
        }
    }


    /**
     * Fires notifications off to all listeners (in reverse order)
     *
     * @param evt the event
     */
    public synchronized void fireUpdateTotal(TotalChangeEvent evt) {
        // Fire the event to all listeners (done in reverse order from how they were added).
        for (int i = totalChangeListeners.size() - 1; i >= 0; i--) {
            TotalChangeListener listener = (TotalChangeListener) totalChangeListeners.get(i);
            listener.updateTotal(evt);
        }
    }

}

/* @(#)TotalChangeSupport.java */
