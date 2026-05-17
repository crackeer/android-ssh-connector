package org.connectbot;

import android.app.Activity;
import android.app.Service;
import android.content.SharedPreferences;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.connectbot.data.ColorSchemeRepository;
import org.connectbot.data.ConnectBotDatabase;
import org.connectbot.data.HostRepository;
import org.connectbot.data.LogRepository;
import org.connectbot.data.ProfileRepository;
import org.connectbot.data.PubkeyRepository;
import org.connectbot.data.QuickCommandRepository;
import org.connectbot.data.dao.ColorSchemeDao;
import org.connectbot.data.dao.HostDao;
import org.connectbot.data.dao.KnownHostDao;
import org.connectbot.data.dao.PortForwardDao;
import org.connectbot.data.dao.ProfileDao;
import org.connectbot.data.dao.PubkeyDao;
import org.connectbot.data.dao.QuickCommandDao;
import org.connectbot.data.migration.DatabaseMigrator;
import org.connectbot.data.migration.LegacyHostDatabaseReader;
import org.connectbot.data.migration.LegacyPubkeyDatabaseReader;
import org.connectbot.di.AppModule_ProvideSharedPreferencesFactory;
import org.connectbot.di.BiometricModule_ProvideBiometricKeyManagerFactory;
import org.connectbot.di.CoroutineDispatchers;
import org.connectbot.di.DatabaseModule_ProvideColorSchemeDaoFactory;
import org.connectbot.di.DatabaseModule_ProvideConnectBotDatabaseFactory;
import org.connectbot.di.DatabaseModule_ProvideHostDaoFactory;
import org.connectbot.di.DatabaseModule_ProvideKnownHostDaoFactory;
import org.connectbot.di.DatabaseModule_ProvidePortForwardDaoFactory;
import org.connectbot.di.DatabaseModule_ProvideProfileDaoFactory;
import org.connectbot.di.DatabaseModule_ProvidePubkeyDaoFactory;
import org.connectbot.di.DatabaseModule_ProvideQuickCommandDaoFactory;
import org.connectbot.di.DispatcherModule_ProvideCoroutineDispatchersFactory;
import org.connectbot.di.LoggingModule_ProvideTimberTreesFactory;
import org.connectbot.di.MigrationModule_ProvideLegacyHostDatabaseReaderFactory;
import org.connectbot.di.MigrationModule_ProvideLegacyPubkeyDatabaseReaderFactory;
import org.connectbot.di.SplitInstallManagerModule_ProvideSplitInstallManagerFactory;
import org.connectbot.logging.TimberInitializer;
import org.connectbot.service.ConnectionNotifier;
import org.connectbot.service.TerminalManager;
import org.connectbot.service.TerminalManager_MembersInjector;
import org.connectbot.ui.AppViewModel;
import org.connectbot.ui.AppViewModel_HiltModules;
import org.connectbot.ui.AppViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.AppViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.ui.MainActivity;
import org.connectbot.ui.screens.colors.ColorSchemeManagerViewModel;
import org.connectbot.ui.screens.colors.ColorSchemeManagerViewModel_HiltModules;
import org.connectbot.ui.screens.colors.ColorSchemeManagerViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.screens.colors.ColorSchemeManagerViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.ui.screens.colors.PaletteEditorViewModel;
import org.connectbot.ui.screens.colors.PaletteEditorViewModel_HiltModules;
import org.connectbot.ui.screens.colors.PaletteEditorViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.screens.colors.PaletteEditorViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.ui.screens.console.ConsoleViewModel;
import org.connectbot.ui.screens.console.ConsoleViewModel_HiltModules;
import org.connectbot.ui.screens.console.ConsoleViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.screens.console.ConsoleViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.ui.screens.filebrowser.FileBrowserViewModel;
import org.connectbot.ui.screens.filebrowser.FileBrowserViewModel_HiltModules;
import org.connectbot.ui.screens.filebrowser.FileBrowserViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.screens.filebrowser.FileBrowserViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.ui.screens.generatepubkey.GeneratePubkeyViewModel;
import org.connectbot.ui.screens.generatepubkey.GeneratePubkeyViewModel_HiltModules;
import org.connectbot.ui.screens.generatepubkey.GeneratePubkeyViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.screens.generatepubkey.GeneratePubkeyViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.ui.screens.help.LogViewerViewModel;
import org.connectbot.ui.screens.help.LogViewerViewModel_HiltModules;
import org.connectbot.ui.screens.help.LogViewerViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.screens.help.LogViewerViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.ui.screens.hosteditor.HostEditorViewModel;
import org.connectbot.ui.screens.hosteditor.HostEditorViewModel_HiltModules;
import org.connectbot.ui.screens.hosteditor.HostEditorViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.screens.hosteditor.HostEditorViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.ui.screens.hostlist.HostListViewModel;
import org.connectbot.ui.screens.hostlist.HostListViewModel_HiltModules;
import org.connectbot.ui.screens.hostlist.HostListViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.screens.hostlist.HostListViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.ui.screens.portforwardlist.PortForwardListViewModel;
import org.connectbot.ui.screens.portforwardlist.PortForwardListViewModel_HiltModules;
import org.connectbot.ui.screens.portforwardlist.PortForwardListViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.screens.portforwardlist.PortForwardListViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.ui.screens.profiles.ProfileEditorViewModel;
import org.connectbot.ui.screens.profiles.ProfileEditorViewModel_HiltModules;
import org.connectbot.ui.screens.profiles.ProfileEditorViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.screens.profiles.ProfileEditorViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.ui.screens.profiles.ProfileListViewModel;
import org.connectbot.ui.screens.profiles.ProfileListViewModel_HiltModules;
import org.connectbot.ui.screens.profiles.ProfileListViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.screens.profiles.ProfileListViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.ui.screens.pubkeyeditor.PubkeyEditorViewModel;
import org.connectbot.ui.screens.pubkeyeditor.PubkeyEditorViewModel_HiltModules;
import org.connectbot.ui.screens.pubkeyeditor.PubkeyEditorViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.screens.pubkeyeditor.PubkeyEditorViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.ui.screens.pubkeylist.PubkeyListViewModel;
import org.connectbot.ui.screens.pubkeylist.PubkeyListViewModel_HiltModules;
import org.connectbot.ui.screens.pubkeylist.PubkeyListViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.screens.pubkeylist.PubkeyListViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.ui.screens.quickcommandlist.QuickCommandListViewModel;
import org.connectbot.ui.screens.quickcommandlist.QuickCommandListViewModel_HiltModules;
import org.connectbot.ui.screens.quickcommandlist.QuickCommandListViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.screens.quickcommandlist.QuickCommandListViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.ui.screens.settings.SettingsViewModel;
import org.connectbot.ui.screens.settings.SettingsViewModel_HiltModules;
import org.connectbot.ui.screens.settings.SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.connectbot.ui.screens.settings.SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.connectbot.util.BiometricKeyManager;
import org.connectbot.util.LanguagePackManager;
import org.connectbot.util.LanguagePackManagerImpl;
import org.connectbot.util.NotificationPermissionHelper;
import org.connectbot.util.SecurePasswordStorage;
import timber.log.Timber;

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
public final class DaggerConnectBotApplication_HiltComponents_SingletonC {
  private DaggerConnectBotApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public ConnectBotApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements ConnectBotApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public ConnectBotApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements ConnectBotApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public ConnectBotApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements ConnectBotApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public ConnectBotApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements ConnectBotApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ConnectBotApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements ConnectBotApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ConnectBotApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements ConnectBotApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public ConnectBotApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements ConnectBotApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public ConnectBotApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends ConnectBotApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends ConnectBotApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    FragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends ConnectBotApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends ConnectBotApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    ActivityCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    ImmutableMap keySetMapOfClassOfAndBooleanBuilder() {
      ImmutableMap.Builder mapBuilder = ImmutableMap.<String, Boolean>builderWithExpectedSize(16);
      mapBuilder.put(AppViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, AppViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(ColorSchemeManagerViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ColorSchemeManagerViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(ConsoleViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ConsoleViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(FileBrowserViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, FileBrowserViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(GeneratePubkeyViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, GeneratePubkeyViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(HostEditorViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, HostEditorViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(HostListViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, HostListViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(LogViewerViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, LogViewerViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(PaletteEditorViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, PaletteEditorViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(PortForwardListViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, PortForwardListViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(ProfileEditorViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ProfileEditorViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(ProfileListViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ProfileListViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(PubkeyEditorViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, PubkeyEditorViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(PubkeyListViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, PubkeyListViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(QuickCommandListViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, QuickCommandListViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SettingsViewModel_HiltModules.KeyModule.provide());
      return mapBuilder.build();
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(keySetMapOfClassOfAndBooleanBuilder());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public void injectHiltComponentActivity(HiltComponentActivity hiltComponentActivity) {
    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }
  }

  private static final class ViewModelCImpl extends ConnectBotApplication_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    Provider<AppViewModel> appViewModelProvider;

    Provider<ColorSchemeManagerViewModel> colorSchemeManagerViewModelProvider;

    Provider<ConsoleViewModel> consoleViewModelProvider;

    Provider<FileBrowserViewModel> fileBrowserViewModelProvider;

    Provider<GeneratePubkeyViewModel> generatePubkeyViewModelProvider;

    Provider<HostEditorViewModel> hostEditorViewModelProvider;

    Provider<HostListViewModel> hostListViewModelProvider;

    Provider<LogViewerViewModel> logViewerViewModelProvider;

    Provider<PaletteEditorViewModel> paletteEditorViewModelProvider;

    Provider<PortForwardListViewModel> portForwardListViewModelProvider;

    Provider<ProfileEditorViewModel> profileEditorViewModelProvider;

    Provider<ProfileListViewModel> profileListViewModelProvider;

    Provider<PubkeyEditorViewModel> pubkeyEditorViewModelProvider;

    Provider<PubkeyListViewModel> pubkeyListViewModelProvider;

    Provider<QuickCommandListViewModel> quickCommandListViewModelProvider;

    Provider<SettingsViewModel> settingsViewModelProvider;

    ViewModelCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        SavedStateHandle savedStateHandleParam, ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    ImmutableMap hiltViewModelMapMapOfClassOfAndProviderOfViewModelBuilder() {
      ImmutableMap.Builder mapBuilder = ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(16);
      mapBuilder.put(AppViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (appViewModelProvider)));
      mapBuilder.put(ColorSchemeManagerViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (colorSchemeManagerViewModelProvider)));
      mapBuilder.put(ConsoleViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (consoleViewModelProvider)));
      mapBuilder.put(FileBrowserViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (fileBrowserViewModelProvider)));
      mapBuilder.put(GeneratePubkeyViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (generatePubkeyViewModelProvider)));
      mapBuilder.put(HostEditorViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (hostEditorViewModelProvider)));
      mapBuilder.put(HostListViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (hostListViewModelProvider)));
      mapBuilder.put(LogViewerViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (logViewerViewModelProvider)));
      mapBuilder.put(PaletteEditorViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (paletteEditorViewModelProvider)));
      mapBuilder.put(PortForwardListViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (portForwardListViewModelProvider)));
      mapBuilder.put(ProfileEditorViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (profileEditorViewModelProvider)));
      mapBuilder.put(ProfileListViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (profileListViewModelProvider)));
      mapBuilder.put(PubkeyEditorViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (pubkeyEditorViewModelProvider)));
      mapBuilder.put(PubkeyListViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (pubkeyListViewModelProvider)));
      mapBuilder.put(QuickCommandListViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (quickCommandListViewModelProvider)));
      mapBuilder.put(SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (settingsViewModelProvider)));
      return mapBuilder.build();
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.appViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.colorSchemeManagerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.consoleViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.fileBrowserViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.generatePubkeyViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.hostEditorViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.hostListViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.logViewerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.paletteEditorViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.portForwardListViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.profileEditorViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
      this.profileListViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 11);
      this.pubkeyEditorViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 12);
      this.pubkeyListViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 13);
      this.quickCommandListViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 14);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 15);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(hiltViewModelMapMapOfClassOfAndProviderOfViewModelBuilder());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // org.connectbot.ui.AppViewModel
          return (T) new AppViewModel(singletonCImpl.databaseMigratorProvider.get(), singletonCImpl.provideSharedPreferencesProvider.get(), singletonCImpl.provideCoroutineDispatchersProvider.get(), singletonCImpl.notificationPermissionHelperProvider.get());

          case 1: // org.connectbot.ui.screens.colors.ColorSchemeManagerViewModel
          return (T) new ColorSchemeManagerViewModel(singletonCImpl.colorSchemeRepositoryProvider.get());

          case 2: // org.connectbot.ui.screens.console.ConsoleViewModel
          return (T) new ConsoleViewModel(viewModelCImpl.savedStateHandle, singletonCImpl.provideCoroutineDispatchersProvider.get(), singletonCImpl.provideSharedPreferencesProvider.get(), singletonCImpl.notificationPermissionHelperProvider.get(), singletonCImpl.quickCommandRepositoryProvider.get());

          case 3: // org.connectbot.ui.screens.filebrowser.FileBrowserViewModel
          return (T) new FileBrowserViewModel(viewModelCImpl.savedStateHandle, ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideCoroutineDispatchersProvider.get());

          case 4: // org.connectbot.ui.screens.generatepubkey.GeneratePubkeyViewModel
          return (T) new GeneratePubkeyViewModel(singletonCImpl.pubkeyRepositoryProvider.get(), singletonCImpl.provideBiometricKeyManagerProvider.get(), singletonCImpl.provideCoroutineDispatchersProvider.get());

          case 5: // org.connectbot.ui.screens.hosteditor.HostEditorViewModel
          return (T) new HostEditorViewModel(viewModelCImpl.savedStateHandle, singletonCImpl.hostRepositoryProvider.get(), singletonCImpl.pubkeyRepositoryProvider.get(), singletonCImpl.profileRepositoryProvider.get(), singletonCImpl.provideSharedPreferencesProvider.get(), singletonCImpl.securePasswordStorageProvider.get());

          case 6: // org.connectbot.ui.screens.hostlist.HostListViewModel
          return (T) new HostListViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.hostRepositoryProvider.get(), singletonCImpl.provideCoroutineDispatchersProvider.get(), singletonCImpl.provideSharedPreferencesProvider.get());

          case 7: // org.connectbot.ui.screens.help.LogViewerViewModel
          return (T) new LogViewerViewModel(singletonCImpl.logRepositoryProvider.get());

          case 8: // org.connectbot.ui.screens.colors.PaletteEditorViewModel
          return (T) new PaletteEditorViewModel(viewModelCImpl.savedStateHandle, singletonCImpl.colorSchemeRepositoryProvider.get(), singletonCImpl.provideCoroutineDispatchersProvider.get());

          case 9: // org.connectbot.ui.screens.portforwardlist.PortForwardListViewModel
          return (T) new PortForwardListViewModel(viewModelCImpl.savedStateHandle, singletonCImpl.hostRepositoryProvider.get(), singletonCImpl.provideCoroutineDispatchersProvider.get());

          case 10: // org.connectbot.ui.screens.profiles.ProfileEditorViewModel
          return (T) new ProfileEditorViewModel(viewModelCImpl.savedStateHandle, singletonCImpl.profileRepositoryProvider.get(), singletonCImpl.colorSchemeRepositoryProvider.get(), singletonCImpl.provideSharedPreferencesProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideCoroutineDispatchersProvider.get());

          case 11: // org.connectbot.ui.screens.profiles.ProfileListViewModel
          return (T) new ProfileListViewModel(singletonCImpl.profileRepositoryProvider.get());

          case 12: // org.connectbot.ui.screens.pubkeyeditor.PubkeyEditorViewModel
          return (T) new PubkeyEditorViewModel(viewModelCImpl.savedStateHandle, singletonCImpl.pubkeyRepositoryProvider.get(), singletonCImpl.provideCoroutineDispatchersProvider.get());

          case 13: // org.connectbot.ui.screens.pubkeylist.PubkeyListViewModel
          return (T) new PubkeyListViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.pubkeyRepositoryProvider.get(), singletonCImpl.provideCoroutineDispatchersProvider.get(), singletonCImpl.provideBiometricKeyManagerProvider.get());

          case 14: // org.connectbot.ui.screens.quickcommandlist.QuickCommandListViewModel
          return (T) new QuickCommandListViewModel(singletonCImpl.quickCommandRepositoryProvider.get(), singletonCImpl.provideCoroutineDispatchersProvider.get());

          case 15: // org.connectbot.ui.screens.settings.SettingsViewModel
          return (T) new SettingsViewModel(singletonCImpl.provideSharedPreferencesProvider.get(), singletonCImpl.profileRepositoryProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideCoroutineDispatchersProvider.get(), singletonCImpl.bindLanguagePackManagerProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends ConnectBotApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends ConnectBotApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }

    @Override
    public void injectTerminalManager(TerminalManager terminalManager) {
      injectTerminalManager2(terminalManager);
    }

    @CanIgnoreReturnValue
    private TerminalManager injectTerminalManager2(TerminalManager instance) {
      TerminalManager_MembersInjector.injectHostRepository(instance, singletonCImpl.hostRepositoryProvider.get());
      TerminalManager_MembersInjector.injectColorRepository(instance, singletonCImpl.colorSchemeRepositoryProvider.get());
      TerminalManager_MembersInjector.injectProfileRepository(instance, singletonCImpl.profileRepositoryProvider.get());
      TerminalManager_MembersInjector.injectPubkeyRepository(instance, singletonCImpl.pubkeyRepositoryProvider.get());
      TerminalManager_MembersInjector.injectPrefs(instance, singletonCImpl.provideSharedPreferencesProvider.get());
      TerminalManager_MembersInjector.injectConnectionNotifier(instance, singletonCImpl.connectionNotifierProvider.get());
      TerminalManager_MembersInjector.injectDispatchers(instance, singletonCImpl.provideCoroutineDispatchersProvider.get());
      TerminalManager_MembersInjector.injectSecurePasswordStorage(instance, singletonCImpl.securePasswordStorageProvider.get());
      return instance;
    }
  }

  private static final class SingletonCImpl extends ConnectBotApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    Provider<Set<Timber.Tree>> provideTimberTreesProvider;

    Provider<TimberInitializer> timberInitializerProvider;

    Provider<ConnectBotDatabase> provideConnectBotDatabaseProvider;

    Provider<LegacyHostDatabaseReader> provideLegacyHostDatabaseReaderProvider;

    Provider<LegacyPubkeyDatabaseReader> provideLegacyPubkeyDatabaseReaderProvider;

    Provider<CoroutineDispatchers> provideCoroutineDispatchersProvider;

    Provider<DatabaseMigrator> databaseMigratorProvider;

    Provider<SharedPreferences> provideSharedPreferencesProvider;

    Provider<NotificationPermissionHelper> notificationPermissionHelperProvider;

    Provider<ColorSchemeRepository> colorSchemeRepositoryProvider;

    Provider<QuickCommandRepository> quickCommandRepositoryProvider;

    Provider<PubkeyRepository> pubkeyRepositoryProvider;

    Provider<BiometricKeyManager> provideBiometricKeyManagerProvider;

    Provider<SecurePasswordStorage> securePasswordStorageProvider;

    Provider<HostRepository> hostRepositoryProvider;

    Provider<ProfileRepository> profileRepositoryProvider;

    Provider<LogRepository> logRepositoryProvider;

    Provider<SplitInstallManager> provideSplitInstallManagerProvider;

    Provider<LanguagePackManagerImpl> languagePackManagerImplProvider;

    Provider<LanguagePackManager> bindLanguagePackManagerProvider;

    Provider<ConnectionNotifier> connectionNotifierProvider;

    SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    ColorSchemeDao colorSchemeDao() {
      return DatabaseModule_ProvideColorSchemeDaoFactory.provideColorSchemeDao(provideConnectBotDatabaseProvider.get());
    }

    QuickCommandDao quickCommandDao() {
      return DatabaseModule_ProvideQuickCommandDaoFactory.provideQuickCommandDao(provideConnectBotDatabaseProvider.get());
    }

    PubkeyDao pubkeyDao() {
      return DatabaseModule_ProvidePubkeyDaoFactory.providePubkeyDao(provideConnectBotDatabaseProvider.get());
    }

    HostDao hostDao() {
      return DatabaseModule_ProvideHostDaoFactory.provideHostDao(provideConnectBotDatabaseProvider.get());
    }

    PortForwardDao portForwardDao() {
      return DatabaseModule_ProvidePortForwardDaoFactory.providePortForwardDao(provideConnectBotDatabaseProvider.get());
    }

    KnownHostDao knownHostDao() {
      return DatabaseModule_ProvideKnownHostDaoFactory.provideKnownHostDao(provideConnectBotDatabaseProvider.get());
    }

    ProfileDao profileDao() {
      return DatabaseModule_ProvideProfileDaoFactory.provideProfileDao(provideConnectBotDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideTimberTreesProvider = DoubleCheck.provider(new SwitchingProvider<Set<Timber.Tree>>(singletonCImpl, 1));
      this.timberInitializerProvider = DoubleCheck.provider(new SwitchingProvider<TimberInitializer>(singletonCImpl, 0));
      this.provideConnectBotDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<ConnectBotDatabase>(singletonCImpl, 3));
      this.provideLegacyHostDatabaseReaderProvider = DoubleCheck.provider(new SwitchingProvider<LegacyHostDatabaseReader>(singletonCImpl, 4));
      this.provideLegacyPubkeyDatabaseReaderProvider = DoubleCheck.provider(new SwitchingProvider<LegacyPubkeyDatabaseReader>(singletonCImpl, 5));
      this.provideCoroutineDispatchersProvider = DoubleCheck.provider(new SwitchingProvider<CoroutineDispatchers>(singletonCImpl, 6));
      this.databaseMigratorProvider = DoubleCheck.provider(new SwitchingProvider<DatabaseMigrator>(singletonCImpl, 2));
      this.provideSharedPreferencesProvider = DoubleCheck.provider(new SwitchingProvider<SharedPreferences>(singletonCImpl, 7));
      this.notificationPermissionHelperProvider = DoubleCheck.provider(new SwitchingProvider<NotificationPermissionHelper>(singletonCImpl, 8));
      this.colorSchemeRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ColorSchemeRepository>(singletonCImpl, 9));
      this.quickCommandRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<QuickCommandRepository>(singletonCImpl, 10));
      this.pubkeyRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<PubkeyRepository>(singletonCImpl, 11));
      this.provideBiometricKeyManagerProvider = DoubleCheck.provider(new SwitchingProvider<BiometricKeyManager>(singletonCImpl, 12));
      this.securePasswordStorageProvider = DoubleCheck.provider(new SwitchingProvider<SecurePasswordStorage>(singletonCImpl, 14));
      this.hostRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<HostRepository>(singletonCImpl, 13));
      this.profileRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ProfileRepository>(singletonCImpl, 15));
      this.logRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<LogRepository>(singletonCImpl, 16));
      this.provideSplitInstallManagerProvider = DoubleCheck.provider(new SwitchingProvider<SplitInstallManager>(singletonCImpl, 18));
      this.languagePackManagerImplProvider = new SwitchingProvider<>(singletonCImpl, 17);
      this.bindLanguagePackManagerProvider = DoubleCheck.provider((Provider) (languagePackManagerImplProvider));
      this.connectionNotifierProvider = DoubleCheck.provider(new SwitchingProvider<ConnectionNotifier>(singletonCImpl, 19));
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    @Override
    public void injectConnectBotApplication(ConnectBotApplication connectBotApplication) {
      injectConnectBotApplication2(connectBotApplication);
    }

    @CanIgnoreReturnValue
    private ConnectBotApplication injectConnectBotApplication2(ConnectBotApplication instance) {
      ConnectBotApplication_MembersInjector.injectTimberInitializer(instance, timberInitializerProvider.get());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // org.connectbot.logging.TimberInitializer
          return (T) new TimberInitializer(singletonCImpl.provideTimberTreesProvider.get());

          case 1: // java.util.Set<timber.log.Timber.Tree>
          return (T) LoggingModule_ProvideTimberTreesFactory.provideTimberTrees();

          case 2: // org.connectbot.data.migration.DatabaseMigrator
          return (T) new DatabaseMigrator(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideConnectBotDatabaseProvider.get(), singletonCImpl.provideLegacyHostDatabaseReaderProvider.get(), singletonCImpl.provideLegacyPubkeyDatabaseReaderProvider.get(), singletonCImpl.provideCoroutineDispatchersProvider.get());

          case 3: // org.connectbot.data.ConnectBotDatabase
          return (T) DatabaseModule_ProvideConnectBotDatabaseFactory.provideConnectBotDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // org.connectbot.data.migration.LegacyHostDatabaseReader
          return (T) MigrationModule_ProvideLegacyHostDatabaseReaderFactory.provideLegacyHostDatabaseReader(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 5: // org.connectbot.data.migration.LegacyPubkeyDatabaseReader
          return (T) MigrationModule_ProvideLegacyPubkeyDatabaseReaderFactory.provideLegacyPubkeyDatabaseReader(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 6: // org.connectbot.di.CoroutineDispatchers
          return (T) DispatcherModule_ProvideCoroutineDispatchersFactory.provideCoroutineDispatchers();

          case 7: // android.content.SharedPreferences
          return (T) AppModule_ProvideSharedPreferencesFactory.provideSharedPreferences(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 8: // org.connectbot.util.NotificationPermissionHelper
          return (T) new NotificationPermissionHelper(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 9: // org.connectbot.data.ColorSchemeRepository
          return (T) new ColorSchemeRepository(singletonCImpl.colorSchemeDao(), singletonCImpl.provideCoroutineDispatchersProvider.get());

          case 10: // org.connectbot.data.QuickCommandRepository
          return (T) new QuickCommandRepository(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.quickCommandDao());

          case 11: // org.connectbot.data.PubkeyRepository
          return (T) new PubkeyRepository(singletonCImpl.pubkeyDao());

          case 12: // org.connectbot.util.BiometricKeyManager
          return (T) BiometricModule_ProvideBiometricKeyManagerFactory.provideBiometricKeyManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 13: // org.connectbot.data.HostRepository
          return (T) new HostRepository(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideConnectBotDatabaseProvider.get(), singletonCImpl.hostDao(), singletonCImpl.portForwardDao(), singletonCImpl.knownHostDao(), singletonCImpl.securePasswordStorageProvider.get());

          case 14: // org.connectbot.util.SecurePasswordStorage
          return (T) new SecurePasswordStorage(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 15: // org.connectbot.data.ProfileRepository
          return (T) new ProfileRepository(singletonCImpl.profileDao(), singletonCImpl.provideCoroutineDispatchersProvider.get());

          case 16: // org.connectbot.data.LogRepository
          return (T) new LogRepository();

          case 17: // org.connectbot.util.LanguagePackManagerImpl
          return (T) new LanguagePackManagerImpl(singletonCImpl.provideSplitInstallManagerProvider.get());

          case 18: // com.google.android.play.core.splitinstall.SplitInstallManager
          return (T) SplitInstallManagerModule_ProvideSplitInstallManagerFactory.provideSplitInstallManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 19: // org.connectbot.service.ConnectionNotifier
          return (T) new ConnectionNotifier();

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
