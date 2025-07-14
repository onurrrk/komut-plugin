# 🧩 Komut

A lightweight and flexible plugin that allows you to define configurable custom commands on your Minecraft servers. Used to trigger console commands via players.

## 🔍 Features

- Unlimited command definitions via `config.yml`
- Permission control per command
- Automatically executes commands as console
- Dynamic command registration
- Fully compatible with Folia

## 🧪 Supported Forks

| Fork / Type  | Support Status |
|--------------|----------------|
| ✅ Paper      | Fully supported |
| ✅ Purpur     | Fully supported |
| ✅ Folia      | Fully supported |
| ✅ Spigot     | Supported       |
| ⚠️ Bukkit     | Partial support |

## ⚙️ Installation

1. Place the `Komut.jar` file into the `plugins/` directory.
2. Start your server.
3. Define your commands by editing the `plugins/Komut/config.yml` file.
4. Restart your server.

## 📁 Config Example

```yaml
komutlar:
  aacver:
    calistirilacak: lp user kullanıcı parent set aac
    perm: aac.verkullan

  mutele:
    calistirilacak: litebans:mute kullanıcı 30m Spam
    perm: spam.kullan
