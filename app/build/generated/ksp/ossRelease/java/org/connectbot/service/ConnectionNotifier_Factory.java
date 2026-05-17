package org.connectbot.service;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class ConnectionNotifier_Factory implements Factory<ConnectionNotifier> {
  @Override
  public ConnectionNotifier get() {
    return newInstance();
  }

  public static ConnectionNotifier_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ConnectionNotifier newInstance() {
    return new ConnectionNotifier();
  }

  private static final class InstanceHolder {
    static final ConnectionNotifier_Factory INSTANCE = new ConnectionNotifier_Factory();
  }
}
