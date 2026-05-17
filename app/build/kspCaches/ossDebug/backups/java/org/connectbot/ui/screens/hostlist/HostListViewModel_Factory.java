package org.connectbot.ui.screens.hostlist;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.HostRepository;
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
public final class HostListViewModel_Factory implements Factory<HostListViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<HostRepository> repositoryProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private final Provider<SharedPreferences> sharedPreferencesProvider;

  private HostListViewModel_Factory(Provider<Context> contextProvider,
      Provider<HostRepository> repositoryProvider,
      Provider<CoroutineDispatchers> dispatchersProvider,
      Provider<SharedPreferences> sharedPreferencesProvider) {
    this.contextProvider = contextProvider;
    this.repositoryProvider = repositoryProvider;
    this.dispatchersProvider = dispatchersProvider;
    this.sharedPreferencesProvider = sharedPreferencesProvider;
  }

  @Override
  public HostListViewModel get() {
    return newInstance(contextProvider.get(), repositoryProvider.get(), dispatchersProvider.get(), sharedPreferencesProvider.get());
  }

  public static HostListViewModel_Factory create(Provider<Context> contextProvider,
      Provider<HostRepository> repositoryProvider,
      Provider<CoroutineDispatchers> dispatchersProvider,
      Provider<SharedPreferences> sharedPreferencesProvider) {
    return new HostListViewModel_Factory(contextProvider, repositoryProvider, dispatchersProvider, sharedPreferencesProvider);
  }

  public static HostListViewModel newInstance(Context context, HostRepository repository,
      CoroutineDispatchers dispatchers, SharedPreferences sharedPreferences) {
    return new HostListViewModel(context, repository, dispatchers, sharedPreferences);
  }
}
