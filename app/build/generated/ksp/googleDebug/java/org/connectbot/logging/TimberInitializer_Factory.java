package org.connectbot.logging;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Set;
import javax.annotation.processing.Generated;
import timber.log.Timber;

@ScopeMetadata("javax.inject.Singleton")
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
public final class TimberInitializer_Factory implements Factory<TimberInitializer> {
  private final Provider<Set<Timber.Tree>> treesProvider;

  private TimberInitializer_Factory(Provider<Set<Timber.Tree>> treesProvider) {
    this.treesProvider = treesProvider;
  }

  @Override
  public TimberInitializer get() {
    return newInstance(treesProvider.get());
  }

  public static TimberInitializer_Factory create(Provider<Set<Timber.Tree>> treesProvider) {
    return new TimberInitializer_Factory(treesProvider);
  }

  public static TimberInitializer newInstance(Set<Timber.Tree> trees) {
    return new TimberInitializer(trees);
  }
}
