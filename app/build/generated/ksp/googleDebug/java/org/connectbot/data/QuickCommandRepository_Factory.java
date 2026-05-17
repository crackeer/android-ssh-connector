package org.connectbot.data;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.dao.QuickCommandDao;

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
public final class QuickCommandRepository_Factory implements Factory<QuickCommandRepository> {
  private final Provider<Context> contextProvider;

  private final Provider<QuickCommandDao> quickCommandDaoProvider;

  private QuickCommandRepository_Factory(Provider<Context> contextProvider,
      Provider<QuickCommandDao> quickCommandDaoProvider) {
    this.contextProvider = contextProvider;
    this.quickCommandDaoProvider = quickCommandDaoProvider;
  }

  @Override
  public QuickCommandRepository get() {
    return newInstance(contextProvider.get(), quickCommandDaoProvider.get());
  }

  public static QuickCommandRepository_Factory create(Provider<Context> contextProvider,
      Provider<QuickCommandDao> quickCommandDaoProvider) {
    return new QuickCommandRepository_Factory(contextProvider, quickCommandDaoProvider);
  }

  public static QuickCommandRepository newInstance(Context context,
      QuickCommandDao quickCommandDao) {
    return new QuickCommandRepository(context, quickCommandDao);
  }
}
