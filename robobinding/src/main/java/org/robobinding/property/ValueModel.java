package org.robobinding.property;

/**
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Cheng Wei
 * 
 */
public interface ValueModel<T> {
    T getValue();

    void setValue(T newValue);

    void addPropertyChangeListener(PresentationModelPropertyChangeListener listener);

    void removePropertyChangeListener(PresentationModelPropertyChangeListener listener);
}
