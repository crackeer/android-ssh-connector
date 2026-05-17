package org.connectbot.util;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class LanguagePackManagerImpl_Factory implements Factory<LanguagePackManagerImpl> {
  private final Provider<Context> contextProvider;

  private LanguagePackManagerImpl_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LanguagePackManagerImpl get() {
    return newInstance(contextProvider.get());
  }

  public static LanguagePackManagerImpl_Factory create(Provider<Context> contextProvider) {
    return new LanguagePackManagerImpl_Factory(contextProvider);
  }

  public static LanguagePackManagerImpl newInstance(Context context) {
    return new LanguagePackManagerImpl(context);
  }
}
