package org.connectbot.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.ConnectBotDatabase;
import org.connectbot.data.dao.QuickCommandDao;

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
public final class DatabaseModule_ProvideQuickCommandDaoFactory implements Factory<QuickCommandDao> {
  private final Provider<ConnectBotDatabase> databaseProvider;

  private DatabaseModule_ProvideQuickCommandDaoFactory(
      Provider<ConnectBotDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public QuickCommandDao get() {
    return provideQuickCommandDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideQuickCommandDaoFactory create(
      Provider<ConnectBotDatabase> databaseProvider) {
    return new DatabaseModule_ProvideQuickCommandDaoFactory(databaseProvider);
  }

  public static QuickCommandDao provideQuickCommandDao(ConnectBotDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideQuickCommandDao(database));
  }
}
