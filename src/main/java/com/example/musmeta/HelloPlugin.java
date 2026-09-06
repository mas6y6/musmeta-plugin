package com.example.musmeta;

import com.mas6y6.musmeta.plugin.api.Plugin;
import com.mas6y6.musmeta.registry.Registries;
import com.mas6y6.musmeta.registry.base.SettingTab;

import javax.swing.*;

public class HelloPlugin extends Plugin {

    @Override
    public void onBoot() {
        getContext().logger().info("{} booting (id={}, version={})",
                getContext().descriptor().name(),
                getContext().descriptor().id(),
                getContext().descriptor().version());

        Registries.SETTING_TABS.register("helloplugin", new SettingTab("Hello Plugin", null,
                () -> new JLabel("Hello from the MusMeta plugin template!")));
    }

    @Override
    public void onEnable() {
        getContext().logger().info("HelloPlugin enabled");
    }

    @Override
    public void onDisable() {
        getContext().logger().info("HelloPlugin disabled");
    }
}