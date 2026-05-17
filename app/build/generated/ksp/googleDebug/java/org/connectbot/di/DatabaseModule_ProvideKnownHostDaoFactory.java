package org.connectbot.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.ConnectBotDatabase;
import org.connectbot.data.dao.KnownHostDao;

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
public final class DatabaseModule_ProvideKnownHostDaoFactory implements Factory<KnownHostDao> {
  private final Provider<ConnectBotDatabase> databaseProvider;

  private DatabaseModule_ProvideKnownHostDaoFactory(Provider<ConnectBotDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public KnownHostDao get() {
    return provideKnownHostDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideKnownHostDaoFactory create(
      Provider<ConnectBotDatabase> databaseProvider) {
    return new DatabaseModule_ProvideKnownHostDaoFactory(databaseProvider);
  }

  public static KnownHostDao provideKnownHostDao(ConnectBotDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideKnownHostDao(database));
  }
}
