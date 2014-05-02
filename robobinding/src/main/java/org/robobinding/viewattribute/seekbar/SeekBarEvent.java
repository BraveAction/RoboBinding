package org.robobinding.viewattribute.seekbar;

import org.robobinding.viewattribute.view.AbstractViewEvent;

import android.widget.SeekBar;

/**
 * 
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Robert Taylor
 */
public class SeekBarEvent extends AbstractViewEvent {
    private final int progress;
    private final boolean fromUser;

    SeekBarEvent(SeekBar seekBar, int progress, boolean fromUser) {
	super(seekBar);
	this.progress = progress;
	this.fromUser = fromUser;
    }

    public SeekBar getSeekBar() {
	return (SeekBar) getView();
    }

    public int getProgress() {
	return progress;
    }

    public boolean isFromUser() {
	return fromUser;
    }
}
