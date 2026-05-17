package org.connectbot.data;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.connectbot.data.dao.HostDao;
import org.connectbot.data.dao.KnownHostDao;
import org.connectbot.data.dao.PortForwardDao;
import org.connectbot.util.SecurePasswordStorage;

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
public final class HostRepository_Factory implements Factory<HostRepository> {
  private final Provider<Context> contextProvider;

  private final Provider<ConnectBotDatabase> databaseProvider;

  private final Provider<HostDao> hostDaoProvider;

  private final Provider<PortForwardDao> portForwardDaoProvider;

  private final Provider<KnownHostDao> knownHostDaoProvider;

  private final Provider<SecurePasswordStorage> securePasswordStorageProvider;

  private HostRepository_Factory(Provider<Context> contextProvider,
      Provider<ConnectBotDatabase> databaseProvider, Provider<HostDao> hostDaoProvider,
      Provider<PortForwardDao> portForwardDaoProvider, Provider<KnownHostDao> knownHostDaoProvider,
      Provider<SecurePasswordStorage> securePasswordStorageProvider) {
    this.contextProvider = contextProvider;
    this.databaseProvider = databaseProvider;
    this.hostDaoProvider = hostDaoProvider;
    this.portForwardDaoProvider = portForwardDaoProvider;
    this.knownHostDaoProvider = knownHostDaoProvider;
    this.securePasswordStorageProvider = securePasswordStorageProvider;
  }

  @Override
  public HostRepository get() {
    return newInstance(contextProvider.get(), databaseProvider.get(), hostDaoProvider.get(), portForwardDaoProvider.get(), knownHostDaoProvider.get(), securePasswordStorageProvider.get());
  }

  public static HostRepository_Factory create(Provider<Context> contextProvider,
      Provider<ConnectBotDatabase> databaseProvider, Provider<HostDao> hostDaoProvider,
      Provider<PortForwardDao> portForwardDaoProvider, Provider<KnownHostDao> knownHostDaoProvider,
      Provider<SecurePasswordStorage> securePasswordStorageProvider) {
    return new HostRepository_Factory(contextProvider, databaseProvider, hostDaoProvider, portForwardDaoProvider, knownHostDaoProvider, securePasswordStorageProvider);
  }

  public static HostRepository newInstance(Context context, ConnectBotDatabase database,
      HostDao hostDao, PortForwardDao portForwardDao, KnownHostDao knownHostDao,
      SecurePasswordStorage securePasswordStorage) {
    return new HostRepository(context, database, hostDao, portForwardDao, knownHostDao, securePasswordStorage);
  }
}
