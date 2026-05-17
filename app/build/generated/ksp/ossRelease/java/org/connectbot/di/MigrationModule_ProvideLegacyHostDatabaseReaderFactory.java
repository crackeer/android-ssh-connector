package org.connectbot.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.migration.LegacyHostDatabaseReader;

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
public final class MigrationModule_ProvideLegacyHostDatabaseReaderFactory implements Factory<LegacyHostDatabaseReader> {
  private final Provider<Context> contextProvider;

  private MigrationModule_ProvideLegacyHostDatabaseReaderFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LegacyHostDatabaseReader get() {
    return provideLegacyHostDatabaseReader(contextProvider.get());
  }

  public static MigrationModule_ProvideLegacyHostDatabaseReaderFactory create(
      Provider<Context> contextProvider) {
    return new MigrationModule_ProvideLegacyHostDatabaseReaderFactory(contextProvider);
  }

  public static LegacyHostDatabaseReader provideLegacyHostDatabaseReader(Context context) {
    return Preconditions.checkNotNullFromProvides(MigrationModule.INSTANCE.provideLegacyHostDatabaseReader(context));
  }
}
