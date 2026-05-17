package org.connectbot.data;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.dao.ColorSchemeDao;
import org.connectbot.di.CoroutineDispatchers;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ColorSchemeRepository_Factory implements Factory<ColorSchemeRepository> {
  private final Provider<ColorSchemeDao> colorSchemeDaoProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private ColorSchemeRepository_Factory(Provider<ColorSchemeDao> colorSchemeDaoProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    this.colorSchemeDaoProvider = colorSchemeDaoProvider;
    this.dispatchersProvider = dispatchersProvider;
  }

  @Override
  public ColorSchemeRepository get() {
    return newInstance(colorSchemeDaoProvider.get(), dispatchersProvider.get());
  }

  public static ColorSchemeRepository_Factory create(
      Provider<ColorSchemeDao> colorSchemeDaoProvider,
      Provider<CoroutineDispatchers> dispatchersProvider) {
    return new ColorSchemeRepository_Factory(colorSchemeDaoProvider, dispatchersProvider);
  }

  public static ColorSchemeRepository newInstance(ColorSchemeDao colorSchemeDao,
      CoroutineDispatchers dispatchers) {
    return new ColorSchemeRepository(colorSchemeDao, dispatchers);
  }
}
