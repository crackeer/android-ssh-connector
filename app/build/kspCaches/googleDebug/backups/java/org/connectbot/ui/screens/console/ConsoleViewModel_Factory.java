package org.connectbot.ui.screens.console;

import android.content.SharedPreferences;
import androidx.lifecycle.SavedStateHandle;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.QuickCommandRepository;
import org.connectbot.di.CoroutineDispatchers;
import org.connectbot.util.NotificationPermissionHelper;

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
public final class ConsoleViewModel_Factory implements Factory<ConsoleViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private final Provider<SharedPreferences> prefsProvider;

  private final Provider<NotificationPermissionHelper> notificationPermissionHelperProvider;

  private final Provider<QuickCommandRepository> quickCommandRepositoryProvider;

  private ConsoleViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<CoroutineDispatchers> dispatchersProvider, Provider<SharedPreferences> prefsProvider,
      Provider<NotificationPermissionHelper> notificationPermissionHelperProvider,
      Provider<QuickCommandRepository> quickCommandRepositoryProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.dispatchersProvider = dispatchersProvider;
    this.prefsProvider = prefsProvider;
    this.notificationPermissionHelperProvider = notificationPermissionHelperProvider;
    this.quickCommandRepositoryProvider = quickCommandRepositoryProvider;
  }

  @Override
  public ConsoleViewModel get() {
    return newInstance(savedStateHandleProvider.get(), dispatchersProvider.get(), prefsProvider.get(), notificationPermissionHelperProvider.get(), quickCommandRepositoryProvider.get());
  }

  public static ConsoleViewModel_Factory create(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<CoroutineDispatchers> dispatchersProvider, Provider<SharedPreferences> prefsProvider,
      Provider<NotificationPermissionHelper> notificationPermissionHelperProvider,
      Provider<QuickCommandRepository> quickCommandRepositoryProvider) {
    return new ConsoleViewModel_Factory(savedStateHandleProvider, dispatchersProvider, prefsProvider, notificationPermissionHelperProvider, quickCommandRepositoryProvider);
  }

  public static ConsoleViewModel newInstance(SavedStateHandle savedStateHandle,
      CoroutineDispatchers dispatchers, SharedPreferences prefs,
      NotificationPermissionHelper notificationPermissionHelper,
      QuickCommandRepository quickCommandRepository) {
    return new ConsoleViewModel(savedStateHandle, dispatchers, prefs, notificationPermissionHelper, quickCommandRepository);
  }
}
