
package com.tinygrip.android.data.entity.mapper;

import com.tinygrip.android.data.ApplicationTestCase;

public class UserEntityJsonMapperTest extends ApplicationTestCase {

  private static final String JSON_RESPONSE_USER_DETAILS = "{\n"
      + "    \"id\": 1,\n"
      + "    \"cover_url\": \"http://www.android.org/myapi/cover_1.jpg\",\n"
      + "    \"full_name\": \"Simon Hill\",\n"
      + "    \"description\": \"Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\\n\\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.\\n\\nPraesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.\",\n"
      + "    \"followers\": 7484,\n"
      + "    \"email\": \"jcooper@babbleset.edu\"\n"
      + "}";

  private static final String JSON_RESPONSE_USER_COLLECTION = "[{\n"
      + "    \"id\": 1,\n"
      + "    \"full_name\": \"Simon Hill\",\n"
      + "    \"followers\": 7484\n"
      + "}, {\n"
      + "    \"id\": 12,\n"
      + "    \"full_name\": \"Pedro Garcia\",\n"
      + "    \"followers\": 1381\n"
      + "}]";

  //private UserEntityJsonMapper userEntityJsonMapper;
  //
  //@Rule
  //public ExpectedException expectedException = ExpectedException.none();
  //
  //@Before
  //public void setUp() {
  //  userEntityJsonMapper = new UserEntityJsonMapper();
  //}
  //
  //@Test
  //public void testTransformUserEntityHappyCase() {
  //  UserEntity userEntity = userEntityJsonMapper.transformUserEntity(JSON_RESPONSE_USER_DETAILS);
  //
  //  assertThat(userEntity.getId(), is(1));
  //  assertThat(userEntity.getFullname(), is(equalTo("Simon Hill")));
  //  assertThat(userEntity.getEmail(), is(equalTo("jcooper@babbleset.edu")));
  //}
  //
  //@Test
  //public void testTransformUserEntityCollectionHappyCase() {
  //  Collection<UserEntity> userEntityCollection =
  //      userEntityJsonMapper.transformUserEntityCollection(
  //          JSON_RESPONSE_USER_COLLECTION);
  //
  //  assertThat(((UserEntity) userEntityCollection.toArray()[0]).getId(), is(1));
  //  assertThat(((UserEntity) userEntityCollection.toArray()[1]).getId(), is(12));
  //  assertThat(userEntityCollection.size(), is(2));
  //}
  //
  //@Test
  //public void testTransformUserEntityNotValidResponse() {
  //  expectedException.expect(JsonSyntaxException.class);
  //  userEntityJsonMapper.transformUserEntity("ironman");
  //}
  //
  //@Test
  //public void testTransformUserEntityCollectionNotValidResponse() {
  //  expectedException.expect(JsonSyntaxException.class);
  //  userEntityJsonMapper.transformUserEntityCollection("Tony Stark");
  //}
}
