package org.connectbot.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.ConnectBotDatabase;
import org.connectbot.data.dao.ColorSchemeDao;

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
public final class DatabaseModule_ProvideColorSchemeDaoFactory implements Factory<ColorSchemeDao> {
  private final Provider<ConnectBotDatabase> databaseProvider;

  private DatabaseModule_ProvideColorSchemeDaoFactory(
      Provider<ConnectBotDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ColorSchemeDao get() {
    return provideColorSchemeDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideColorSchemeDaoFactory create(
      Provider<ConnectBotDatabase> databaseProvider) {
    return new DatabaseModule_ProvideColorSchemeDaoFactory(databaseProvider);
  }

  public static ColorSchemeDao provideColorSchemeDao(ConnectBotDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideColorSchemeDao(database));
  }
}
