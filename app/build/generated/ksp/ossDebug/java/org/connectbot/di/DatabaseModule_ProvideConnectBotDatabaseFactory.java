package org.connectbot.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.ConnectBotDatabase;

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
public final class DatabaseModule_ProvideConnectBotDatabaseFactory implements Factory<ConnectBotDatabase> {
  private final Provider<Context> contextProvider;

  private DatabaseModule_ProvideConnectBotDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ConnectBotDatabase get() {
    return provideConnectBotDatabase(contextProvider.get());
  }

  public static DatabaseModule_ProvideConnectBotDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideConnectBotDatabaseFactory(contextProvider);
  }

  public static ConnectBotDatabase provideConnectBotDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideConnectBotDatabase(context));
  }
}
