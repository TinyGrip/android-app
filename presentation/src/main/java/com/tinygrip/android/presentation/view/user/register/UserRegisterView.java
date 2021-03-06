
package com.tinygrip.android.presentation.view.user.register;

import android.content.Context;
import com.tinygrip.android.presentation.view.LoadDataView;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user registration.
 */
public interface UserRegisterView extends LoadDataView {

    /**
     * Simply go up in the stack
     */
    void goUp();

    /**
     * Render the invalid email error message
     *
     * @param errorMessage The error message to display
     */
    void showInvalidEmail(String errorMessage);

    /**
     * Render the invalid password error message
     *
     * @param errorMessage The error message to display
     */
    void showInvalidPassword(String errorMessage);

    /**
     * Render the confirm password error message
     *
     * @param errorMessage The error message to display
     */
    void showInvalidConfirmPassword(String errorMessage);

    /**
     * Render the user registration
     */
    void showRegisterSuccessful();

    /**
     * Get a {@link Context}.
     */
    Context getContext();


}
