package org.connectbot.ui.screens.portforwardlist;

import androidx.lifecycle.SavedStateHandle;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.HostRepository;
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
public final class PortForwardListViewModel_Factory implements Factory<PortForwardListViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<HostRepository> repositoryProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private PortForwardListViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<HostRepository> repositoryProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.repositoryProvider = repositoryProvider;
    this.dispatchersProvider = dispatchersProvider;
  }

  @Override
  public PortForwardListViewModel get() {
    return newInstance(savedStateHandleProvider.get(), repositoryProvider.get(), dispatchersProvider.get());
  }

  public static PortForwardListViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<HostRepository> repositoryProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    return new PortForwardListViewModel_Factory(savedStateHandleProvider, repositoryProvider, dispatchersProvider);
  }

  public static PortForwardListViewModel newInstance(SavedStateHandle savedStateHandle,
      HostRepository repository, CoroutineDispatchers dispatchers) {
    return new PortForwardListViewModel(savedStateHandle, repository, dispatchers);
  }
}
