/**
 * CodingPhaseRenderer renders for more than 4 people Description: Coding Phase
 * renderer
 * 
 * @author Tim "Pops" Roberts
 * @version 1.0
 */
package com.topcoder.client.spectatorApp.scoreboard.view;

import com.topcoder.client.spectatorApp.widgets.SNoHeaderBackground;
import com.topcoder.client.spectatorApp.views.BlankPanel;

public class NoHeaderScreenRenderer extends AbstractScreenContestRenderer {
	/** Constructs the panel */
	public NoHeaderScreenRenderer(String[] computerNames, String path, String[] handles, String title) {
		super(computerNames, path, handles, new BlankPanel(1360, 130), new SNoHeaderBackground());
	}
}
