package org.connectbot.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.ConnectBotDatabase;
import org.connectbot.data.dao.PubkeyDao;

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
public final class DatabaseModule_ProvidePubkeyDaoFactory implements Factory<PubkeyDao> {
  private final Provider<ConnectBotDatabase> databaseProvider;

  private DatabaseModule_ProvidePubkeyDaoFactory(Provider<ConnectBotDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PubkeyDao get() {
    return providePubkeyDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvidePubkeyDaoFactory create(
      Provider<ConnectBotDatabase> databaseProvider) {
    return new DatabaseModule_ProvidePubkeyDaoFactory(databaseProvider);
  }

  public static PubkeyDao providePubkeyDao(ConnectBotDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePubkeyDao(database));
  }
}
