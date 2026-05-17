package org.connectbot.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class DispatcherModule_ProvideCoroutineDispatchersFactory implements Factory<CoroutineDispatchers> {
  @Override
  public CoroutineDispatchers get() {
    return provideCoroutineDispatchers();
  }

  public static DispatcherModule_ProvideCoroutineDispatchersFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CoroutineDispatchers provideCoroutineDispatchers() {
    return Preconditions.checkNotNullFromProvides(DispatcherModule.INSTANCE.provideCoroutineDispatchers());
  }

  private static final class InstanceHolder {
    static final DispatcherModule_ProvideCoroutineDispatchersFactory INSTANCE = new DispatcherModule_ProvideCoroutineDispatchersFactory();
  }
}
