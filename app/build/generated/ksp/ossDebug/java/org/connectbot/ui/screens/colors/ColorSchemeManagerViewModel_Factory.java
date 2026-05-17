package org.connectbot.ui.screens.colors;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.ColorSchemeRepository;

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
public final class ColorSchemeManagerViewModel_Factory implements Factory<ColorSchemeManagerViewModel> {
  private final Provider<ColorSchemeRepository> repositoryProvider;

  private ColorSchemeManagerViewModel_Factory(Provider<ColorSchemeRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ColorSchemeManagerViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static ColorSchemeManagerViewModel_Factory create(
      Provider<ColorSchemeRepository> repositoryProvider) {
    return new ColorSchemeManagerViewModel_Factory(repositoryProvider);
  }

  public static ColorSchemeManagerViewModel newInstance(ColorSchemeRepository repository) {
    return new ColorSchemeManagerViewModel(repository);
  }
}
