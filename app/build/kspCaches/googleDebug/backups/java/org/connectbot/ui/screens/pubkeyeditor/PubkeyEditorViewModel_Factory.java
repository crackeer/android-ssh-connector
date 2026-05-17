package org.connectbot.ui.screens.pubkeyeditor;

import androidx.lifecycle.SavedStateHandle;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.PubkeyRepository;
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
public final class PubkeyEditorViewModel_Factory implements Factory<PubkeyEditorViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<PubkeyRepository> repositoryProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private PubkeyEditorViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<PubkeyRepository> repositoryProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.repositoryProvider = repositoryProvider;
    this.dispatchersProvider = dispatchersProvider;
  }

  @Override
  public PubkeyEditorViewModel get() {
    return newInstance(savedStateHandleProvider.get(), repositoryProvider.get(), dispatchersProvider.get());
  }

  public static PubkeyEditorViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<PubkeyRepository> repositoryProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    return new PubkeyEditorViewModel_Factory(savedStateHandleProvider, repositoryProvider, dispatchersProvider);
  }

  public static PubkeyEditorViewModel newInstance(SavedStateHandle savedStateHandle,
      PubkeyRepository repository, CoroutineDispatchers dispatchers) {
    return new PubkeyEditorViewModel(savedStateHandle, repository, dispatchers);
  }
}
