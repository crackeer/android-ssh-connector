package org.connectbot.ui.screens.hosteditor;

import android.content.SharedPreferences;
import androidx.lifecycle.SavedStateHandle;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.HostRepository;
import org.connectbot.data.ProfileRepository;
import org.connectbot.data.PubkeyRepository;
import org.connectbot.util.SecurePasswordStorage;

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
public final class HostEditorViewModel_Factory implements Factory<HostEditorViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<HostRepository> repositoryProvider;

  private final Provider<PubkeyRepository> pubkeyRepositoryProvider;

  private final Provider<ProfileRepository> profileRepositoryProvider;

  private final Provider<SharedPreferences> prefsProvider;

  private final Provider<SecurePasswordStorage> securePasswordStorageProvider;

  private HostEditorViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<HostRepository> repositoryProvider,
      Provider<PubkeyRepository> pubkeyRepositoryProvider,
      Provider<ProfileRepository> profileRepositoryProvider,
      Provider<SharedPreferences> prefsProvider,
      Provider<SecurePasswordStorage> securePasswordStorageProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.repositoryProvider = repositoryProvider;
    this.pubkeyRepositoryProvider = pubkeyRepositoryProvider;
    this.profileRepositoryProvider = profileRepositoryProvider;
    this.prefsProvider = prefsProvider;
    this.securePasswordStorageProvider = securePasswordStorageProvider;
  }

  @Override
  public HostEditorViewModel get() {
    return newInstance(savedStateHandleProvider.get(), repositoryProvider.get(), pubkeyRepositoryProvider.get(), profileRepositoryProvider.get(), prefsProvider.get(), securePasswordStorageProvider.get());
  }

  public static HostEditorViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<HostRepository> repositoryProvider,
      Provider<PubkeyRepository> pubkeyRepositoryProvider,
      Provider<ProfileRepository> profileRepositoryProvider,
      Provider<SharedPreferences> prefsProvider,
      Provider<SecurePasswordStorage> securePasswordStorageProvider) {
    return new HostEditorViewModel_Factory(savedStateHandleProvider, repositoryProvider, pubkeyRepositoryProvider, profileRepositoryProvider, prefsProvider, securePasswordStorageProvider);
  }

  public static HostEditorViewModel newInstance(SavedStateHandle savedStateHandle,
      HostRepository repository, PubkeyRepository pubkeyRepository,
      ProfileRepository profileRepository, SharedPreferences prefs,
      SecurePasswordStorage securePasswordStorage) {
    return new HostEditorViewModel(savedStateHandle, repository, pubkeyRepository, profileRepository, prefs, securePasswordStorage);
  }
}
