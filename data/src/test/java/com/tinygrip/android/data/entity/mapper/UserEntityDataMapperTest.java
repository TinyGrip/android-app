
package com.tinygrip.android.data.entity.mapper;

import com.tinygrip.android.data.ApplicationTestCase;
import org.junit.Before;

public class UserEntityDataMapperTest extends ApplicationTestCase {

  private static final int FAKE_USER_ID = 123;
  private static final String FAKE_FULLNAME = "Tony Stark";

  private UserEntityDataMapper userEntityDataMapper;

  @Before
  public void setUp() throws Exception {
    userEntityDataMapper = new UserEntityDataMapper();
  }

  //@Test
  //public void testTransformUserEntity() {
  //  UserEntity userEntity = createFakeUserEntity();
  //  User user = userEntityDataMapper.transform(userEntity);
  //
  //  assertThat(user, is(instanceOf(User.class)));
  //  assertThat(user.getId(), is(FAKE_USER_ID));
  //  assertThat(user.getFullName(), is(FAKE_FULLNAME));
  //}
  //
  //@Test
  //public void testTransformUserEntityCollection() {
  //  UserEntity mockUserEntityOne = mock(UserEntity.class);
  //  UserEntity mockUserEntityTwo = mock(UserEntity.class);
  //
  //  List<UserEntity> userEntityList = new ArrayList<UserEntity>(5);
  //  userEntityList.add(mockUserEntityOne);
  //  userEntityList.add(mockUserEntityTwo);
  //
  //  Collection<User> userCollection = userEntityDataMapper.transform(userEntityList);
  //
  //  assertThat(userCollection.toArray()[0], is(instanceOf(User.class)));
  //  assertThat(userCollection.toArray()[1], is(instanceOf(User.class)));
  //  assertThat(userCollection.size(), is(2));
  //}
  //
  //private UserEntity createFakeUserEntity() {
  //  UserEntity userEntity = new UserEntity();
  //  userEntity.setId(FAKE_USER_ID);
  //  userEntity.setFullname(FAKE_FULLNAME);
  //
  //  return userEntity;
  //}
}
