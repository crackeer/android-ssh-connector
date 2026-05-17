package org.connectbot;

import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ServiceComponent;
import dagger.hilt.android.components.ViewComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.components.ViewWithFragmentComponent;
import dagger.hilt.android.flags.FragmentGetContextFix;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_DefaultViewModelFactories_ActivityModule;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ViewModelModule;
import dagger.hilt.android.internal.managers.ActivityComponentManager;
import dagger.hilt.android.internal.managers.FragmentComponentManager;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_LifecycleModule;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivitySavedStateHandleModule;
import dagger.hilt.android.internal.managers.ServiceComponentManager;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.HiltWrapper_ActivityModule;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.android.scopes.FragmentScoped;
import dagger.hilt.android.scopes.ServiceScoped;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.android.scopes.ViewScoped;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedComponent;
import dagger.hilt.migration.DisableInstallInCheck;
import javax.annotation.processing.Generated;
import javax.inject.Singleton;
import org.connectbot.di.AppModule;
import org.connectbot.di.BiometricModule;
import org.connectbot.di.DatabaseModule;
import org.connectbot.di.DispatcherModule;
import org.connectbot.di.LanguagePackModule;
import org.connectbot.di.LoggingModule;
import org.connectbot.di.MigrationModule;
import org.connectbot.di.SplitInstallManagerModule;
import org.connectbot.service.TerminalManager_GeneratedInjector;
import org.connectbot.ui.AppViewModel_HiltModules;
import org.connectbot.ui.MainActivity_GeneratedInjector;
import org.connectbot.ui.screens.colors.ColorSchemeManagerViewModel_HiltModules;
import org.connectbot.ui.screens.colors.PaletteEditorViewModel_HiltModules;
import org.connectbot.ui.screens.console.ConsoleViewModel_HiltModules;
import org.connectbot.ui.screens.filebrowser.FileBrowserViewModel_HiltModules;
import org.connectbot.ui.screens.generatepubkey.GeneratePubkeyViewModel_HiltModules;
import org.connectbot.ui.screens.help.LogViewerViewModel_HiltModules;
import org.connectbot.ui.screens.hosteditor.HostEditorViewModel_HiltModules;
import org.connectbot.ui.screens.hostlist.HostListViewModel_HiltModules;
import org.connectbot.ui.screens.portforwardlist.PortForwardListViewModel_HiltModules;
import org.connectbot.ui.screens.profiles.ProfileEditorViewModel_HiltModules;
import org.connectbot.ui.screens.profiles.ProfileListViewModel_HiltModules;
import org.connectbot.ui.screens.pubkeyeditor.PubkeyEditorViewModel_HiltModules;
import org.connectbot.ui.screens.pubkeylist.PubkeyListViewModel_HiltModules;
import org.connectbot.ui.screens.quickcommandlist.QuickCommandListViewModel_HiltModules;
import org.connectbot.ui.screens.settings.SettingsViewModel_HiltModules;

@Generated("dagger.hilt.processor.internal.root.RootProcessor")
public final class ConnectBotApplication_HiltComponents {
  private ConnectBotApplication_HiltComponents() {
  }

  @Module(
      subcomponents = ServiceC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ServiceCBuilderModule {
    @Binds
    ServiceComponentBuilder bind(ServiceC.Builder builder);
  }

  @Module(
      subcomponents = ActivityRetainedC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityRetainedCBuilderModule {
    @Binds
    ActivityRetainedComponentBuilder bind(ActivityRetainedC.Builder builder);
  }

  @Module(
      subcomponents = ActivityC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityCBuilderModule {
    @Binds
    ActivityComponentBuilder bind(ActivityC.Builder builder);
  }

  @Module(
      subcomponents = ViewModelC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewModelCBuilderModule {
    @Binds
    ViewModelComponentBuilder bind(ViewModelC.Builder builder);
  }

  @Module(
      subcomponents = ViewC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewCBuilderModule {
    @Binds
    ViewComponentBuilder bind(ViewC.Builder builder);
  }

  @Module(
      subcomponents = FragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface FragmentCBuilderModule {
    @Binds
    FragmentComponentBuilder bind(FragmentC.Builder builder);
  }

  @Module(
      subcomponents = ViewWithFragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewWithFragmentCBuilderModule {
    @Binds
    ViewWithFragmentComponentBuilder bind(ViewWithFragmentC.Builder builder);
  }

  @Component(
      modules = {
          AppModule.class,
          ApplicationContextModule.class,
          BiometricModule.class,
          ActivityRetainedCBuilderModule.class,
          ServiceCBuilderModule.class,
          DatabaseModule.class,
          DispatcherModule.class,
          HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule.class,
          LanguagePackModule.class,
          LoggingModule.class,
          MigrationModule.class,
          SplitInstallManagerModule.class
      }
  )
  @Singleton
  @jakarta.inject.Singleton
  public abstract static class SingletonC implements FragmentGetContextFix.FragmentGetContextFixEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint,
      ServiceComponentManager.ServiceComponentBuilderEntryPoint,
      SingletonComponent,
      GeneratedComponent,
      ConnectBotApplication_GeneratedInjector {
  }

  @Subcomponent
  @ServiceScoped
  public abstract static class ServiceC implements ServiceComponent,
      GeneratedComponent,
      TerminalManager_GeneratedInjector {
    @Subcomponent.Builder
    abstract interface Builder extends ServiceComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          AppViewModel_HiltModules.KeyModule.class,
          ColorSchemeManagerViewModel_HiltModules.KeyModule.class,
          ActivityCBuilderModule.class,
          ViewModelCBuilderModule.class,
          ConsoleViewModel_HiltModules.KeyModule.class,
          FileBrowserViewModel_HiltModules.KeyModule.class,
          GeneratePubkeyViewModel_HiltModules.KeyModule.class,
          HiltWrapper_ActivityRetainedComponentManager_LifecycleModule.class,
          HiltWrapper_ActivitySavedStateHandleModule.class,
          HostEditorViewModel_HiltModules.KeyModule.class,
          HostListViewModel_HiltModules.KeyModule.class,
          LogViewerViewModel_HiltModules.KeyModule.class,
          PaletteEditorViewModel_HiltModules.KeyModule.class,
          PortForwardListViewModel_HiltModules.KeyModule.class,
          ProfileEditorViewModel_HiltModules.KeyModule.class,
          ProfileListViewModel_HiltModules.KeyModule.class,
          PubkeyEditorViewModel_HiltModules.KeyModule.class,
          PubkeyListViewModel_HiltModules.KeyModule.class,
          QuickCommandListViewModel_HiltModules.KeyModule.class,
          SettingsViewModel_HiltModules.KeyModule.class
      }
  )
  @ActivityRetainedScoped
  public abstract static class ActivityRetainedC implements ActivityRetainedComponent,
      ActivityComponentManager.ActivityComponentBuilderEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityRetainedComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          FragmentCBuilderModule.class,
          ViewCBuilderModule.class,
          HiltWrapper_ActivityModule.class,
          HiltWrapper_DefaultViewModelFactories_ActivityModule.class
      }
  )
  @ActivityScoped
  public abstract static class ActivityC implements ActivityComponent,
      DefaultViewModelFactories.ActivityEntryPoint,
      HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint,
      FragmentComponentManager.FragmentComponentBuilderEntryPoint,
      ViewComponentManager.ViewComponentBuilderEntryPoint,
      GeneratedComponent,
      HiltComponentActivity_GeneratedInjector,
      MainActivity_GeneratedInjector {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          AppViewModel_HiltModules.BindsModule.class,
          ColorSchemeManagerViewModel_HiltModules.BindsModule.class,
          ConsoleViewModel_HiltModules.BindsModule.class,
          FileBrowserViewModel_HiltModules.BindsModule.class,
          GeneratePubkeyViewModel_HiltModules.BindsModule.class,
          HiltWrapper_HiltViewModelFactory_ViewModelModule.class,
          HostEditorViewModel_HiltModules.BindsModule.class,
          HostListViewModel_HiltModules.BindsModule.class,
          LogViewerViewModel_HiltModules.BindsModule.class,
          PaletteEditorViewModel_HiltModules.BindsModule.class,
          PortForwardListViewModel_HiltModules.BindsModule.class,
          ProfileEditorViewModel_HiltModules.BindsModule.class,
          ProfileListViewModel_HiltModules.BindsModule.class,
          PubkeyEditorViewModel_HiltModules.BindsModule.class,
          PubkeyListViewModel_HiltModules.BindsModule.class,
          QuickCommandListViewModel_HiltModules.BindsModule.class,
          SettingsViewModel_HiltModules.BindsModule.class
      }
  )
  @ViewModelScoped
  public abstract static class ViewModelC implements ViewModelComponent,
      HiltViewModelFactory.ViewModelFactoriesEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewModelComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewC implements ViewComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewComponentBuilder {
    }
  }

  @Subcomponent(
      modules = ViewWithFragmentCBuilderModule.class
  )
  @FragmentScoped
  public abstract static class FragmentC implements FragmentComponent,
      DefaultViewModelFactories.FragmentEntryPoint,
      ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends FragmentComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewWithFragmentC implements ViewWithFragmentComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewWithFragmentComponentBuilder {
    }
  }
}
