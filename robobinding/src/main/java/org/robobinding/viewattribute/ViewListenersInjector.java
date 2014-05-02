package org.robobinding.viewattribute;

import android.view.View;

/**
 * 
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Cheng Wei
 */
public interface ViewListenersInjector {
    void injectIfRequired(ViewAttribute viewAttribute, View view);
}
