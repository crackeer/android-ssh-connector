package org.connectbot.data.migration;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.ConnectBotDatabase;
import org.connectbot.di.CoroutineDispatchers;

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
public final class DatabaseMigrator_Factory implements Factory<DatabaseMigrator> {
  private final Provider<Context> contextProvider;

  private final Provider<ConnectBotDatabase> roomDatabaseProvider;

  private final Provider<LegacyHostDatabaseReader> legacyHostReaderProvider;

  private final Provider<LegacyPubkeyDatabaseReader> legacyPubkeyReaderProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private DatabaseMigrator_Factory(Provider<Context> contextProvider,
      Provider<ConnectBotDatabase> roomDatabaseProvider,
      Provider<LegacyHostDatabaseReader> legacyHostReaderProvider,
      Provider<LegacyPubkeyDatabaseReader> legacyPubkeyReaderProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    this.contextProvider = contextProvider;
    this.roomDatabaseProvider = roomDatabaseProvider;
    this.legacyHostReaderProvider = legacyHostReaderProvider;
    this.legacyPubkeyReaderProvider = legacyPubkeyReaderProvider;
    this.dispatchersProvider = dispatchersProvider;
  }

  @Override
  public DatabaseMigrator get() {
    return newInstance(contextProvider.get(), roomDatabaseProvider.get(), legacyHostReaderProvider.get(), legacyPubkeyReaderProvider.get(), dispatchersProvider.get());
  }

  public static DatabaseMigrator_Factory create(Provider<Context> contextProvider,
      Provider<ConnectBotDatabase> roomDatabaseProvider,
      Provider<LegacyHostDatabaseReader> legacyHostReaderProvider,
      Provider<LegacyPubkeyDatabaseReader> legacyPubkeyReaderProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    return new DatabaseMigrator_Factory(contextProvider, roomDatabaseProvider, legacyHostReaderProvider, legacyPubkeyReaderProvider, dispatchersProvider);
  }

  public static DatabaseMigrator newInstance(Context context, ConnectBotDatabase roomDatabase,
      LegacyHostDatabaseReader legacyHostReader, LegacyPubkeyDatabaseReader legacyPubkeyReader,
      CoroutineDispatchers dispatchers) {
    return new DatabaseMigrator(context, roomDatabase, legacyHostReader, legacyPubkeyReader, dispatchers);
  }
}
