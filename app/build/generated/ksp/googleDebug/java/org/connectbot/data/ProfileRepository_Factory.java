package org.connectbot.data;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.dao.ProfileDao;
import org.connectbot.di.CoroutineDispatchers;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class ProfileRepository_Factory implements Factory<ProfileRepository> {
  private final Provider<ProfileDao> profileDaoProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private ProfileRepository_Factory(Provider<ProfileDao> profileDaoProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    this.profileDaoProvider = profileDaoProvider;
    this.dispatchersProvider = dispatchersProvider;
  }

  @Override
  public ProfileRepository get() {
    return newInstance(profileDaoProvider.get(), dispatchersProvider.get());
  }

  public static ProfileRepository_Factory create(Provider<ProfileDao> profileDaoProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    return new ProfileRepository_Factory(profileDaoProvider, dispatchersProvider);
  }

  public static ProfileRepository newInstance(ProfileDao profileDao,
      CoroutineDispatchers dispatchers) {
    return new ProfileRepository(profileDao, dispatchers);
  }
}
