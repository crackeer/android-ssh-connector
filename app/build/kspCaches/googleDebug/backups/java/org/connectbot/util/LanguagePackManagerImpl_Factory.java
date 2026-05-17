package org.connectbot.util;

import com.google.android.play.core.splitinstall.SplitInstallManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class LanguagePackManagerImpl_Factory implements Factory<LanguagePackManagerImpl> {
  private final Provider<SplitInstallManager> splitInstallManagerProvider;

  private LanguagePackManagerImpl_Factory(
      Provider<SplitInstallManager> splitInstallManagerProvider) {
    this.splitInstallManagerProvider = splitInstallManagerProvider;
  }

  @Override
  public LanguagePackManagerImpl get() {
    return newInstance(splitInstallManagerProvider.get());
  }

  public static LanguagePackManagerImpl_Factory create(
      Provider<SplitInstallManager> splitInstallManagerProvider) {
    return new LanguagePackManagerImpl_Factory(splitInstallManagerProvider);
  }

  public static LanguagePackManagerImpl newInstance(SplitInstallManager splitInstallManager) {
    return new LanguagePackManagerImpl(splitInstallManager);
  }
}
