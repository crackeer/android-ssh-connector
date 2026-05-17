package org.connectbot.data;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.dao.PubkeyDao;

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
public final class PubkeyRepository_Factory implements Factory<PubkeyRepository> {
  private final Provider<PubkeyDao> pubkeyDaoProvider;

  private PubkeyRepository_Factory(Provider<PubkeyDao> pubkeyDaoProvider) {
    this.pubkeyDaoProvider = pubkeyDaoProvider;
  }

  @Override
  public PubkeyRepository get() {
    return newInstance(pubkeyDaoProvider.get());
  }

  public static PubkeyRepository_Factory create(Provider<PubkeyDao> pubkeyDaoProvider) {
    return new PubkeyRepository_Factory(pubkeyDaoProvider);
  }

  public static PubkeyRepository newInstance(PubkeyDao pubkeyDao) {
    return new PubkeyRepository(pubkeyDao);
  }
}
