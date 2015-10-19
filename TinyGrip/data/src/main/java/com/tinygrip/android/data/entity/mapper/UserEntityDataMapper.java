
package com.tinygrip.android.data.entity.mapper;

import com.tinygrip.android.data.entity.UserEntity;

import com.tinygrip.android.domain.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link UserEntity} (in the data layer) to {@link com.tinygrip.android.domain.User} in the
 * domain layer.
 */
@Singleton
public class UserEntityDataMapper {

  @Inject
  public UserEntityDataMapper() {}

  /**
   * Transform a {@link UserEntity} into an {@link com.tinygrip.android.domain.User}.
   *
   * @param userEntity Object to be transformed.
   * @return {@link com.tinygrip.android.domain.User} if valid {@link UserEntity} otherwise null.
   */
  public User transform(UserEntity userEntity) {
    User user = null;
    if (userEntity != null) {
      user = new User(userEntity.getId());
      user.setAvatarUrl(userEntity.getAvatarUrl());
      user.setCoverUrl(userEntity.getCoverUrl());
      user.setFirstName(userEntity.getFirstName());
      user.setLastName(userEntity.getLastName());
      user.setEmail(userEntity.getEmail());
    }

    return user;
  }

  /**
   * Transform a List of {@link UserEntity} into a Collection of {@link com.tinygrip.android.domain.User}.
   *
   * @param userEntityCollection Object Collection to be transformed.
   * @return {@link com.tinygrip.android.domain.User} if valid {@link UserEntity} otherwise null.
   */
  public List<User> transform(Collection<UserEntity> userEntityCollection) {
    List<User> userList = new ArrayList<>(20);
    User user;
    for (UserEntity userEntity : userEntityCollection) {
      user = transform(userEntity);
      if (user != null) {
        userList.add(user);
      }
    }

    return userList;
  }
}
