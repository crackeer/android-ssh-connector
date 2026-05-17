package org.connectbot.ui.screens.quickcommandlist;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.QuickCommandRepository;
import org.connectbot.di.CoroutineDispatchers;

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
public final class QuickCommandListViewModel_Factory implements Factory<QuickCommandListViewModel> {
  private final Provider<QuickCommandRepository> repositoryProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private QuickCommandListViewModel_Factory(Provider<QuickCommandRepository> repositoryProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    this.repositoryProvider = repositoryProvider;
    this.dispatchersProvider = dispatchersProvider;
  }

  @Override
  public QuickCommandListViewModel get() {
    return newInstance(repositoryProvider.get(), dispatchersProvider.get());
  }

  public static QuickCommandListViewModel_Factory create(
      Provider<QuickCommandRepository> repositoryProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    return new QuickCommandListViewModel_Factory(repositoryProvider, dispatchersProvider);
  }

  public static QuickCommandListViewModel newInstance(QuickCommandRepository repository,
      CoroutineDispatchers dispatchers) {
    return new QuickCommandListViewModel(repository, dispatchers);
  }
}
