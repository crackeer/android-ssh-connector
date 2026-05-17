package org.connectbot.ui.screens.help;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.LogRepository;

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
public final class LogViewerViewModel_Factory implements Factory<LogViewerViewModel> {
  private final Provider<LogRepository> logRepositoryProvider;

  private LogViewerViewModel_Factory(Provider<LogRepository> logRepositoryProvider) {
    this.logRepositoryProvider = logRepositoryProvider;
  }

  @Override
  public LogViewerViewModel get() {
    return newInstance(logRepositoryProvider.get());
  }

  public static LogViewerViewModel_Factory create(Provider<LogRepository> logRepositoryProvider) {
    return new LogViewerViewModel_Factory(logRepositoryProvider);
  }

  public static LogViewerViewModel newInstance(LogRepository logRepository) {
    return new LogViewerViewModel(logRepository);
  }
}
