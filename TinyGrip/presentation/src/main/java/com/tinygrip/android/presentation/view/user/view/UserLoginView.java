
package com.tinygrip.android.presentation.view.user.view;

import android.content.Context;
import com.tinygrip.android.presentation.model.UserModel;
import com.tinygrip.android.presentation.view.LoadDataView;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user login.
 */
public interface UserLoginView extends LoadDataView {

    /**
     * Simply go up in the stack
     */
    void goUp();

    void showInvalidEmail(String errorMessage);

    void showInvalidPassword(String errorMessage);

    /**
     * Render the login
     */
    void loginSuccessful(UserModel userModel);

    /**
     * Get a {@link android.content.Context}.
     */
    Context getContext();


}
