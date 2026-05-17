package org.connectbot.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.migration.LegacyPubkeyDatabaseReader;

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
public final class MigrationModule_ProvideLegacyPubkeyDatabaseReaderFactory implements Factory<LegacyPubkeyDatabaseReader> {
  private final Provider<Context> contextProvider;

  private MigrationModule_ProvideLegacyPubkeyDatabaseReaderFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LegacyPubkeyDatabaseReader get() {
    return provideLegacyPubkeyDatabaseReader(contextProvider.get());
  }

  public static MigrationModule_ProvideLegacyPubkeyDatabaseReaderFactory create(
      Provider<Context> contextProvider) {
    return new MigrationModule_ProvideLegacyPubkeyDatabaseReaderFactory(contextProvider);
  }

  public static LegacyPubkeyDatabaseReader provideLegacyPubkeyDatabaseReader(Context context) {
    return Preconditions.checkNotNullFromProvides(MigrationModule.INSTANCE.provideLegacyPubkeyDatabaseReader(context));
  }
}
