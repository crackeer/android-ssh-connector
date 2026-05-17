package org.connectbot;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.logging.TimberInitializer;

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
public final class ConnectBotApplication_MembersInjector implements MembersInjector<ConnectBotApplication> {
  private final Provider<TimberInitializer> timberInitializerProvider;

  private ConnectBotApplication_MembersInjector(
      Provider<TimberInitializer> timberInitializerProvider) {
    this.timberInitializerProvider = timberInitializerProvider;
  }

  @Override
  public void injectMembers(ConnectBotApplication instance) {
    injectTimberInitializer(instance, timberInitializerProvider.get());
  }

  public static MembersInjector<ConnectBotApplication> create(
      Provider<TimberInitializer> timberInitializerProvider) {
    return new ConnectBotApplication_MembersInjector(timberInitializerProvider);
  }

  @InjectedFieldSignature("org.connectbot.ConnectBotApplication.timberInitializer")
  public static void injectTimberInitializer(ConnectBotApplication instance,
      TimberInitializer timberInitializer) {
    instance.timberInitializer = timberInitializer;
  }
}
