package org.connectbot.ui.screens.pubkeylist;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.PubkeyRepository;
import org.connectbot.di.CoroutineDispatchers;
import org.connectbot.util.BiometricKeyManager;

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
public final class PubkeyListViewModel_Factory implements Factory<PubkeyListViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<PubkeyRepository> repositoryProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private final Provider<BiometricKeyManager> biometricKeyManagerProvider;

  private PubkeyListViewModel_Factory(Provider<Context> contextProvider,
      Provider<PubkeyRepository> repositoryProvider,
      Provider<CoroutineDispatchers> dispatchersProvider,
      Provider<BiometricKeyManager> biometricKeyManagerProvider) {
    this.contextProvider = contextProvider;
    this.repositoryProvider = repositoryProvider;
    this.dispatchersProvider = dispatchersProvider;
    this.biometricKeyManagerProvider = biometricKeyManagerProvider;
  }

  @Override
  public PubkeyListViewModel get() {
    return newInstance(contextProvider.get(), repositoryProvider.get(), dispatchersProvider.get(), biometricKeyManagerProvider.get());
  }

  public static PubkeyListViewModel_Factory create(Provider<Context> contextProvider,
      Provider<PubkeyRepository> repositoryProvider,
      Provider<CoroutineDispatchers> dispatchersProvider,
      Provider<BiometricKeyManager> biometricKeyManagerProvider) {
    return new PubkeyListViewModel_Factory(contextProvider, repositoryProvider, dispatchersProvider, biometricKeyManagerProvider);
  }

  public static PubkeyListViewModel newInstance(Context context, PubkeyRepository repository,
      CoroutineDispatchers dispatchers, BiometricKeyManager biometricKeyManager) {
    return new PubkeyListViewModel(context, repository, dispatchers, biometricKeyManager);
  }
}
