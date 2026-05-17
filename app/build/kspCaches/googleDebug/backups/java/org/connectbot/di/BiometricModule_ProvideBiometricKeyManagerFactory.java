package org.connectbot.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.util.BiometricKeyManager;

@ScopeMetadata("javax.inject.Singleton")
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
public final class BiometricModule_ProvideBiometricKeyManagerFactory implements Factory<BiometricKeyManager> {
  private final Provider<Context> contextProvider;

  private BiometricModule_ProvideBiometricKeyManagerFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public BiometricKeyManager get() {
    return provideBiometricKeyManager(contextProvider.get());
  }

  public static BiometricModule_ProvideBiometricKeyManagerFactory create(
      Provider<Context> contextProvider) {
    return new BiometricModule_ProvideBiometricKeyManagerFactory(contextProvider);
  }

  public static BiometricKeyManager provideBiometricKeyManager(Context context) {
    return Preconditions.checkNotNullFromProvides(BiometricModule.INSTANCE.provideBiometricKeyManager(context));
  }
}
