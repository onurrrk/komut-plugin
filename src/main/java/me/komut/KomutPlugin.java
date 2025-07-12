package me.komut;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class KomutPlugin extends JavaPlugin {

    private FileConfiguration config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        registerAllCommands();
    }

    private void registerAllCommands() {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());

            Set<String> komutlar = config.getConfigurationSection("komutlar").getKeys(false);

            for (String komutAdi : komutlar) {
                Command command = new Command(komutAdi) {

                    @Override
                    public boolean execute(CommandSender sender, String label, String[] args) {
                        return KomutPlugin.this.executeCommand(sender, label, args, komutAdi);
                    }

                    // Tab tamamlama kısmı:
                    @Override
                    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
                        // Yalnızca Player ve izni olan gösterir:
                        if (!(sender instanceof Player player)) return List.of();
                        String perm = config.getString("komutlar." + komutAdi + ".perm");
                        if (perm == null || !player.hasPermission(perm)) return List.of();

                        List<String> tamamla = new ArrayList<>();
                        if (args.length == 1) {
                            String arg = args[0].toLowerCase();
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.getName().toLowerCase().startsWith(arg))
                                    tamamla.add(p.getName());
                            }
                        }
                        return tamamla;
                    }
                };

                commandMap.register(getDescription().getName(), command);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean executeCommand(CommandSender sender, String label, String[] args, String komutAdi) {
        if (!config.contains("komutlar." + komutAdi)) {
            sender.sendMessage("§cKomut yapılandırması bulunamadı!");
            return true;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cBu komutu sadece oyuncular kullanabilir!");
            return true;
        }

        String izin = config.getString("komutlar." + komutAdi + ".perm");
        if (izin == null || !player.hasPermission(izin)) {
            // İzin yoksa sessizce komutu çalıştırma ve mesaj verme
            return true;
        }

        if (args.length < 1) {
            player.sendMessage("§eBir oyuncu ismi girin!");
            return true;
        }

        String hedef = args[0];
        String komut = config.getString("komutlar." + komutAdi + ".calistirilacak");
        if (komut == null || komut.isEmpty()) {
            player.sendMessage("§cÇalıştırılacak komut yapılandırılmamış!");
            return true;
        }

        String calisacak = komut.replace("kullanıcı", hedef);

        player.getServer().getGlobalRegionScheduler().execute(this, () -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), calisacak);
        });

        player.sendMessage("§aİşlem Başarılı!");
        return true;
    }
}
