package org.connectbot.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.ConnectBotDatabase;
import org.connectbot.data.dao.ProfileDao;

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
public final class DatabaseModule_ProvideProfileDaoFactory implements Factory<ProfileDao> {
  private final Provider<ConnectBotDatabase> databaseProvider;

  private DatabaseModule_ProvideProfileDaoFactory(Provider<ConnectBotDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ProfileDao get() {
    return provideProfileDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideProfileDaoFactory create(
      Provider<ConnectBotDatabase> databaseProvider) {
    return new DatabaseModule_ProvideProfileDaoFactory(databaseProvider);
  }

  public static ProfileDao provideProfileDao(ConnectBotDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideProfileDao(database));
  }
}
