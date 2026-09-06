# musmeta-plugin-template

Starter skeleton for a [MusMeta](https://github.com/mas6y6/MusMeta) plugin. It includes a
`Plugin` entry point, a settings-tab registration, and a working mixin that targets
`Utils.isRunningAsRoot()`.

## Requirements

- JDK 25
- MusMeta's `plugins:framework` published to `~/.m2/repository` (see below)

## Getting started

1. **Publish the framework to mavenLocal** (from a MusMeta checkout):

   ```bash
   ./gradlew :plugins:framework:publishToMavenLocal
   ```

2. **Rename the template** to your plugin:

   - `plugin.json` → change `id`, `name`, `version`, `authors`, `main`.
   - Move `com/example/musmeta/` to your own package and update `main` in `plugin.json`.
   - Update `hello.mixins.json`'s `package` (and rename the file to match the new `mixins` entry).
   - `build.gradle` → `group`, `jar.archiveBaseName`, and any version bumps.

3. **Build**:

   ```bash
   ./gradlew build
   ```

4. **Install into MusMeta**:

   ```bash
   ./gradlew publishPlugin
   ```

   This copies the JAR into `~/.musmeta/plugins` (or the directory set via the
   `musmeta.plugins` Gradle property). Restart MusMeta and the plugin will be discovered.

## Project layout

```
build.gradle            Build script (framework via mavenLocal)
gradle.properties       Dependency versions
src/main/java           Plugin entry point + mixin classes
src/main/resources      plugin.json + mixin config
```

## Extension points

- `onBoot()` — plugin is loaded, mixins are being applied; safe time to register
  into the `Registries` (e.g. `SETTING_TABS`).
- `onEnable()` / `onDisable()` — lifecycle hooks when MusMeta enables or disables you.
- Mixins — drop classes under the `package` listed in your `<id>.mixins.json` and add
  them to its `mixins` array.