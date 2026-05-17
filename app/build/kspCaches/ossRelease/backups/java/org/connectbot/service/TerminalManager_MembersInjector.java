package org.connectbot.service;

import android.content.SharedPreferences;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.ColorSchemeRepository;
import org.connectbot.data.HostRepository;
import org.connectbot.data.ProfileRepository;
import org.connectbot.data.PubkeyRepository;
import org.connectbot.di.CoroutineDispatchers;
import org.connectbot.util.SecurePasswordStorage;

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
public final class TerminalManager_MembersInjector implements MembersInjector<TerminalManager> {
  private final Provider<HostRepository> hostRepositoryProvider;

  private final Provider<ColorSchemeRepository> colorRepositoryProvider;

  private final Provider<ProfileRepository> profileRepositoryProvider;

  private final Provider<PubkeyRepository> pubkeyRepositoryProvider;

  private final Provider<SharedPreferences> prefsProvider;

  private final Provider<ConnectionNotifier> connectionNotifierProvider;

  private final Provider<CoroutineDispatchers> dispatchersProvider;

  private final Provider<SecurePasswordStorage> securePasswordStorageProvider;

  private TerminalManager_MembersInjector(Provider<HostRepository> hostRepositoryProvider,
      Provider<ColorSchemeRepository> colorRepositoryProvider,
      Provider<ProfileRepository> profileRepositoryProvider,
      Provider<PubkeyRepository> pubkeyRepositoryProvider,
      Provider<SharedPreferences> prefsProvider,
      Provider<ConnectionNotifier> connectionNotifierProvider,
      Provider<CoroutineDispatchers> dispatchersProvider,
      Provider<SecurePasswordStorage> securePasswordStorageProvider) {
    this.hostRepositoryProvider = hostRepositoryProvider;
    this.colorRepositoryProvider = colorRepositoryProvider;
    this.profileRepositoryProvider = profileRepositoryProvider;
    this.pubkeyRepositoryProvider = pubkeyRepositoryProvider;
    this.prefsProvider = prefsProvider;
    this.connectionNotifierProvider = connectionNotifierProvider;
    this.dispatchersProvider = dispatchersProvider;
    this.securePasswordStorageProvider = securePasswordStorageProvider;
  }

  @Override
  public void injectMembers(TerminalManager instance) {
    injectHostRepository(instance, hostRepositoryProvider.get());
    injectColorRepository(instance, colorRepositoryProvider.get());
    injectProfileRepository(instance, profileRepositoryProvider.get());
    injectPubkeyRepository(instance, pubkeyRepositoryProvider.get());
    injectPrefs(instance, prefsProvider.get());
    injectConnectionNotifier(instance, connectionNotifierProvider.get());
    injectDispatchers(instance, dispatchersProvider.get());
    injectSecurePasswordStorage(instance, securePasswordStorageProvider.get());
  }

  public static MembersInjector<TerminalManager> create(
      Provider<HostRepository> hostRepositoryProvider,
      Provider<ColorSchemeRepository> colorRepositoryProvider,
      Provider<ProfileRepository> profileRepositoryProvider,
      Provider<PubkeyRepository> pubkeyRepositoryProvider,
      Provider<SharedPreferences> prefsProvider,
      Provider<ConnectionNotifier> connectionNotifierProvider,
      Provider<CoroutineDispatchers> dispatchersProvider,
      Provider<SecurePasswordStorage> securePasswordStorageProvider) {
    return new TerminalManager_MembersInjector(hostRepositoryProvider, colorRepositoryProvider, profileRepositoryProvider, pubkeyRepositoryProvider, prefsProvider, connectionNotifierProvider, dispatchersProvider, securePasswordStorageProvider);
  }

  @InjectedFieldSignature("org.connectbot.service.TerminalManager.hostRepository")
  public static void injectHostRepository(TerminalManager instance, HostRepository hostRepository) {
    instance.hostRepository = hostRepository;
  }

  @InjectedFieldSignature("org.connectbot.service.TerminalManager.colorRepository")
  public static void injectColorRepository(TerminalManager instance,
      ColorSchemeRepository colorRepository) {
    instance.colorRepository = colorRepository;
  }

  @InjectedFieldSignature("org.connectbot.service.TerminalManager.profileRepository")
  public static void injectProfileRepository(TerminalManager instance,
      ProfileRepository profileRepository) {
    instance.profileRepository = profileRepository;
  }

  @InjectedFieldSignature("org.connectbot.service.TerminalManager.pubkeyRepository")
  public static void injectPubkeyRepository(TerminalManager instance,
      PubkeyRepository pubkeyRepository) {
    instance.pubkeyRepository = pubkeyRepository;
  }

  @InjectedFieldSignature("org.connectbot.service.TerminalManager.prefs")
  public static void injectPrefs(TerminalManager instance, SharedPreferences prefs) {
    instance.prefs = prefs;
  }

  @InjectedFieldSignature("org.connectbot.service.TerminalManager.connectionNotifier")
  public static void injectConnectionNotifier(TerminalManager instance,
      ConnectionNotifier connectionNotifier) {
    instance.connectionNotifier = connectionNotifier;
  }

  @InjectedFieldSignature("org.connectbot.service.TerminalManager.dispatchers")
  public static void injectDispatchers(TerminalManager instance, CoroutineDispatchers dispatchers) {
    instance.dispatchers = dispatchers;
  }

  @InjectedFieldSignature("org.connectbot.service.TerminalManager.securePasswordStorage")
  public static void injectSecurePasswordStorage(TerminalManager instance,
      SecurePasswordStorage securePasswordStorage) {
    instance.securePasswordStorage = securePasswordStorage;
  }
}
