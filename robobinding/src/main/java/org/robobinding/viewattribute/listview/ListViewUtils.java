package org.robobinding.viewattribute.listview;

import android.util.SparseBooleanArray;
import android.widget.ListView;

/**
 * 
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Cheng Wei
 */
public class ListViewUtils {
    private ListViewUtils() {
    }

    /**
     * TODO:Will be replaced by listView.clearChoices(). Requires a change to
     * ShadowListView in Robolectric.
     */
    public static void clearSelections(ListView listView) {
	SparseBooleanArray array = listView.getCheckedItemPositions();

	if (array == null) {
	    return;
	}

	for (int i = 0; i < array.size(); i++) {
	    int position = array.keyAt(i);
	    boolean checked = array.valueAt(i);
	    if (checked) {
		listView.setItemChecked(position, false);
	    }
	}
    }
}
