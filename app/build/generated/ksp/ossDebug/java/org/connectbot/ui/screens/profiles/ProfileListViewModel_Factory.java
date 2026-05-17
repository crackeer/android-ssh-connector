package org.connectbot.ui.screens.profiles;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.ProfileRepository;

@ScopeMetadata
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
public final class ProfileListViewModel_Factory implements Factory<ProfileListViewModel> {
  private final Provider<ProfileRepository> profileRepositoryProvider;

  private ProfileListViewModel_Factory(Provider<ProfileRepository> profileRepositoryProvider) {
    this.profileRepositoryProvider = profileRepositoryProvider;
  }

  @Override
  public ProfileListViewModel get() {
    return newInstance(profileRepositoryProvider.get());
  }

  public static ProfileListViewModel_Factory create(
      Provider<ProfileRepository> profileRepositoryProvider) {
    return new ProfileListViewModel_Factory(profileRepositoryProvider);
  }

  public static ProfileListViewModel newInstance(ProfileRepository profileRepository) {
    return new ProfileListViewModel(profileRepository);
  }
}
