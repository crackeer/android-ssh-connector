package org.connectbot.ui.screens.colors;

import androidx.lifecycle.SavedStateHandle;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.ColorSchemeRepository;
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
public final class PaletteEditorViewModel_Factory implements Factory<PaletteEditorViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<ColorSchemeRepository> repositoryProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private PaletteEditorViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ColorSchemeRepository> repositoryProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.repositoryProvider = repositoryProvider;
    this.dispatchersProvider = dispatchersProvider;
  }

  @Override
  public PaletteEditorViewModel get() {
    return newInstance(savedStateHandleProvider.get(), repositoryProvider.get(), dispatchersProvider.get());
  }

  public static PaletteEditorViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ColorSchemeRepository> repositoryProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    return new PaletteEditorViewModel_Factory(savedStateHandleProvider, repositoryProvider, dispatchersProvider);
  }

  public static PaletteEditorViewModel newInstance(SavedStateHandle savedStateHandle,
      ColorSchemeRepository repository, CoroutineDispatchers dispatchers) {
    return new PaletteEditorViewModel(savedStateHandle, repository, dispatchers);
  }
}
