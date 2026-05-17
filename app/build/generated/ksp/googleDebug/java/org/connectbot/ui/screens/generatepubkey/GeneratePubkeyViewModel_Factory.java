package org.connectbot.ui.screens.generatepubkey;

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
public final class GeneratePubkeyViewModel_Factory implements Factory<GeneratePubkeyViewModel> {
  private final Provider<PubkeyRepository> repositoryProvider;

  private final Provider<BiometricKeyManager> biometricKeyManagerProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private GeneratePubkeyViewModel_Factory(Provider<PubkeyRepository> repositoryProvider,
      Provider<BiometricKeyManager> biometricKeyManagerProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    this.repositoryProvider = repositoryProvider;
    this.biometricKeyManagerProvider = biometricKeyManagerProvider;
    this.dispatchersProvider = dispatchersProvider;
  }

  @Override
  public GeneratePubkeyViewModel get() {
    return newInstance(repositoryProvider.get(), biometricKeyManagerProvider.get(), dispatchersProvider.get());
  }

  public static GeneratePubkeyViewModel_Factory create(
      Provider<PubkeyRepository> repositoryProvider,
      Provider<BiometricKeyManager> biometricKeyManagerProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    return new GeneratePubkeyViewModel_Factory(repositoryProvider, biometricKeyManagerProvider, dispatchersProvider);
  }

  public static GeneratePubkeyViewModel newInstance(PubkeyRepository repository,
      BiometricKeyManager biometricKeyManager, CoroutineDispatchers dispatchers) {
    return new GeneratePubkeyViewModel(repository, biometricKeyManager, dispatchers);
  }
}
