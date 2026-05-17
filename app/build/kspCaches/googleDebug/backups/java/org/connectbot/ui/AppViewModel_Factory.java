package org.connectbot.ui;

import android.content.SharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.migration.DatabaseMigrator;
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
public final class AppViewModel_Factory implements Factory<AppViewModel> {
  private final Provider<DatabaseMigrator> migratorProvider;

  private final Provider<SharedPreferences> prefsProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private final Provider<NotificationPermissionHelper> notificationPermissionHelperProvider;

  private AppViewModel_Factory(Provider<DatabaseMigrator> migratorProvider,
      Provider<SharedPreferences> prefsProvider, Provider<CoroutineDispatchers> dispatchersProvider,
      Provider<NotificationPermissionHelper> notificationPermissionHelperProvider) {
    this.migratorProvider = migratorProvider;
    this.prefsProvider = prefsProvider;
    this.dispatchersProvider = dispatchersProvider;
    this.notificationPermissionHelperProvider = notificationPermissionHelperProvider;
  }

  @Override
  public AppViewModel get() {
    return newInstance(migratorProvider.get(), prefsProvider.get(), dispatchersProvider.get(), notificationPermissionHelperProvider.get());
  }

  public static AppViewModel_Factory create(Provider<DatabaseMigrator> migratorProvider,
      Provider<SharedPreferences> prefsProvider, Provider<CoroutineDispatchers> dispatchersProvider,
      Provider<NotificationPermissionHelper> notificationPermissionHelperProvider) {
    return new AppViewModel_Factory(migratorProvider, prefsProvider, dispatchersProvider, notificationPermissionHelperProvider);
  }

  public static AppViewModel newInstance(DatabaseMigrator migrator, SharedPreferences prefs,
      CoroutineDispatchers dispatchers, NotificationPermissionHelper notificationPermissionHelper) {
    return new AppViewModel(migrator, prefs, dispatchers, notificationPermissionHelper);
  }
}
