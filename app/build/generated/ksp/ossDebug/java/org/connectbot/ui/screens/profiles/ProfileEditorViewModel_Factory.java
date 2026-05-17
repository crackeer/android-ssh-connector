package org.connectbot.ui.screens.profiles;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.SavedStateHandle;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.ColorSchemeRepository;
import org.connectbot.data.ProfileRepository;
import org.connectbot.di.CoroutineDispatchers;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ProfileEditorViewModel_Factory implements Factory<ProfileEditorViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<ProfileRepository> profileRepositoryProvider;

  private final Provider<ColorSchemeRepository> colorSchemeRepositoryProvider;

  private final Provider<SharedPreferences> prefsProvider;

  private final Provider<Context> contextProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private ProfileEditorViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ProfileRepository> profileRepositoryProvider,
      Provider<ColorSchemeRepository> colorSchemeRepositoryProvider,
      Provider<SharedPreferences> prefsProvider, Provider<Context> contextProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.profileRepositoryProvider = profileRepositoryProvider;
    this.colorSchemeRepositoryProvider = colorSchemeRepositoryProvider;
    this.prefsProvider = prefsProvider;
    this.contextProvider = contextProvider;
    this.dispatchersProvider = dispatchersProvider;
  }

  @Override
  public ProfileEditorViewModel get() {
    return newInstance(savedStateHandleProvider.get(), profileRepositoryProvider.get(), colorSchemeRepositoryProvider.get(), prefsProvider.get(), contextProvider.get(), dispatchersProvider.get());
  }

  public static ProfileEditorViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ProfileRepository> profileRepositoryProvider,
      Provider<ColorSchemeRepository> colorSchemeRepositoryProvider,
      Provider<SharedPreferences> prefsProvider, Provider<Context> contextProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    return new ProfileEditorViewModel_Factory(savedStateHandleProvider, profileRepositoryProvider, colorSchemeRepositoryProvider, prefsProvider, contextProvider, dispatchersProvider);
  }

  public static ProfileEditorViewModel newInstance(SavedStateHandle savedStateHandle,
      ProfileRepository profileRepository, ColorSchemeRepository colorSchemeRepository,
      SharedPreferences prefs, Context context, CoroutineDispatchers dispatchers) {
    return new ProfileEditorViewModel(savedStateHandle, profileRepository, colorSchemeRepository, prefs, context, dispatchers);
  }
}
