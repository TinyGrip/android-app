
package com.tinygrip.android.presentation.view.user;

import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.domain.interactor.UserDetails;
import com.tinygrip.android.domain.interactor.UserLogin;
import com.tinygrip.android.domain.repository.UserRepository;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class UserModule {

    public UserModule() {}

    @Provides
    @ActivityScope
    @Named("userDetails")
    UseCase provideGetUser(UserRepository userRepository,
                           ThreadExecutor threadExecutor,
                           PostExecutionThread postExecutionThread) {
        return new UserDetails(userRepository, threadExecutor, postExecutionThread);
    }

    @Provides
    @ActivityScope
    @Named("userLogin")
    UserLogin provideGetUserByLogin(UserRepository userRepository,
                                    ThreadExecutor threadExecutor,
                                    PostExecutionThread postExecutionThread) {
        return new UserLogin(userRepository, threadExecutor, postExecutionThread);
    }
}