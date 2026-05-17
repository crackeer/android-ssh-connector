package org.connectbot.di;

import android.content.Context;
import com.google.android.play.core.splitinstall.SplitInstallManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class SplitInstallManagerModule_ProvideSplitInstallManagerFactory implements Factory<SplitInstallManager> {
  private final Provider<Context> contextProvider;

  private SplitInstallManagerModule_ProvideSplitInstallManagerFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SplitInstallManager get() {
    return provideSplitInstallManager(contextProvider.get());
  }

  public static SplitInstallManagerModule_ProvideSplitInstallManagerFactory create(
      Provider<Context> contextProvider) {
    return new SplitInstallManagerModule_ProvideSplitInstallManagerFactory(contextProvider);
  }

  public static SplitInstallManager provideSplitInstallManager(Context context) {
    return Preconditions.checkNotNullFromProvides(SplitInstallManagerModule.INSTANCE.provideSplitInstallManager(context));
  }
}
