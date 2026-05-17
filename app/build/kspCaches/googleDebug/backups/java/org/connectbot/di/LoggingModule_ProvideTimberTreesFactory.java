package org.connectbot.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class LoggingModule_ProvideTimberTreesFactory implements Factory<Set<Timber.Tree>> {
  @Override
  public Set<Timber.Tree> get() {
    return provideTimberTrees();
  }

  public static LoggingModule_ProvideTimberTreesFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Set<Timber.Tree> provideTimberTrees() {
    return Preconditions.checkNotNullFromProvides(LoggingModule.INSTANCE.provideTimberTrees());
  }

  private static final class InstanceHolder {
    static final LoggingModule_ProvideTimberTreesFactory INSTANCE = new LoggingModule_ProvideTimberTreesFactory();
  }
}
