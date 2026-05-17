package org.connectbot.ui.screens.filebrowser;

import android.content.Context;
import androidx.lifecycle.SavedStateHandle;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
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
public final class FileBrowserViewModel_Factory implements Factory<FileBrowserViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<Context> contextProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private FileBrowserViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<Context> contextProvider, Provider<CoroutineDispatchers> dispatchersProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.contextProvider = contextProvider;
    this.dispatchersProvider = dispatchersProvider;
  }

  @Override
  public FileBrowserViewModel get() {
    return newInstance(savedStateHandleProvider.get(), contextProvider.get(), dispatchersProvider.get());
  }

  public static FileBrowserViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider, Provider<Context> contextProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    return new FileBrowserViewModel_Factory(savedStateHandleProvider, contextProvider, dispatchersProvider);
  }

  public static FileBrowserViewModel newInstance(SavedStateHandle savedStateHandle, Context context,
      CoroutineDispatchers dispatchers) {
    return new FileBrowserViewModel(savedStateHandle, context, dispatchers);
  }
}
