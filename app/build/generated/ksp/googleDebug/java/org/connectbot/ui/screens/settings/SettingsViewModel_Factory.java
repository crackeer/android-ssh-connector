package org.connectbot.ui.screens.settings;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.ProfileRepository;
import org.connectbot.di.CoroutineDispatchers;
import org.connectbot.util.LanguagePackManager;

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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<SharedPreferences> prefsProvider;

  private final Provider<ProfileRepository> profileRepositoryProvider;

  private final Provider<Context> contextProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private final Provider<LanguagePackManager> languagePackManagerProvider;

  private SettingsViewModel_Factory(Provider<SharedPreferences> prefsProvider,
      Provider<ProfileRepository> profileRepositoryProvider, Provider<Context> contextProvider,
      Provider<CoroutineDispatchers> dispatchersProvider,
      Provider<LanguagePackManager> languagePackManagerProvider) {
    this.prefsProvider = prefsProvider;
    this.profileRepositoryProvider = profileRepositoryProvider;
    this.contextProvider = contextProvider;
    this.dispatchersProvider = dispatchersProvider;
    this.languagePackManagerProvider = languagePackManagerProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(prefsProvider.get(), profileRepositoryProvider.get(), contextProvider.get(), dispatchersProvider.get(), languagePackManagerProvider.get());
  }

  public static SettingsViewModel_Factory create(Provider<SharedPreferences> prefsProvider,
      Provider<ProfileRepository> profileRepositoryProvider, Provider<Context> contextProvider,
      Provider<CoroutineDispatchers> dispatchersProvider,
      Provider<LanguagePackManager> languagePackManagerProvider) {
    return new SettingsViewModel_Factory(prefsProvider, profileRepositoryProvider, contextProvider, dispatchersProvider, languagePackManagerProvider);
  }

  public static SettingsViewModel newInstance(SharedPreferences prefs,
      ProfileRepository profileRepository, Context context, CoroutineDispatchers dispatchers,
      LanguagePackManager languagePackManager) {
    return new SettingsViewModel(prefs, profileRepository, context, dispatchers, languagePackManager);
  }
}
