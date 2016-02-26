package com.tinygrip.android.test.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.view.user.profile.UserProfileActivity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserProfileActivityTest extends ActivityInstrumentationTestCase2<UserProfileActivity> {

    private static final String FAKE_USER_NAME = "fake";
    private static final String FAKE_PASSWORD = "fake";

    private UserProfileActivity userProfileActivity;

    public UserProfileActivityTest() {
        super(UserProfileActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.setActivityIntent(createTargetIntent());
        this.userProfileActivity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testContainsUserLoginFragment() {
        Fragment userLoginFragment = userProfileActivity.getSupportFragmentManager().findFragmentById(R.id.fragment_user_login);
        assertThat(userLoginFragment, is(notNullValue()));
    }

    public void testContainsProperTitle() {
        String actualTitle = this.userProfileActivity.getTitle().toString().trim();

        assertThat(actualTitle, is("User Login"));
    }

    //
    //public void testLoadUserHappyCaseViews() {
    //    onView(withId(R.id.rl_progress)).check(matches(not(isDisplayed())));
    //
    //    onView(withId(R.id.et_username)).check(matches(isDisplayed()));
    //    onView(withId(R.id.et_password)).check(matches(isDisplayed()));
    //    onView(withId(R.id.tv_register_link)).check(matches(isDisplayed()));
    //}
    //
    //public void testLoadUserHappyCaseData() {
    //    onView(withId(R.id.et_username)).perform(clearText(), typeText(FAKE_USER_NAME));
    //    onView(withId(R.id.et_password)).perform(clearText(), typeText(FAKE_PASSWORD));
    //
    //    onView(withId(R.id.et_username)).check(matches(withText(FAKE_PASSWORD)));
    //    onView(withId(R.id.et_password)).check(matches(withText(FAKE_PASSWORD)));
    //}

    private Intent createTargetIntent() {
        return UserProfileActivity.getCallingIntent(getInstrumentation().getTargetContext());
    }
}
