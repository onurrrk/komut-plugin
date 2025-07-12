# 🧩 Komut

Minecraft sunucularınızda, yapılandırılabilir özel komutlar tanımlamanıza olanak sağlayan hafif ve esnek bir eklentidir. Konsol komutlarını oyuncular üzerinden tetiklemek için kullanılır.

## 🔍 Özellikler

- `config.yml` dosyasından sınırsız komut tanımı
- Her komut için özel yetki belirleyebilme
- Konsol üzerinden otomatik komut çalıştırma
- Dinamik komut kaydı 
- Folia uyumlu çalışır

## 🧪 Desteklenen Forklar

| Fork / Yapı | Destek Durumu |
|-------------|----------------|
| ✅ Paper     | Tam uyumlu     |
| ✅ Purpur    | Tam uyumlu     |
| ✅ Folia     | Tam uyumlu     |
| ✅ Spigot    | Uyumlu         |
| ⚠️ Bukkit    | Kısmi destek   |

## ⚙️ Kurulum

1. `Komut.jar` dosyasını `plugins/` klasörüne atın.
2. Sunucunuzu başlatın.
3. `plugins/Komut/config.yml` dosyasını düzenleyerek komutlarınızı tanımlayın.
4. Sunucunuzu yeniden başlatın.

## 📁 Config Örneği

```yaml
komutlar:
  aacver:
    calistirilacak: lp user kullanıcı parent set aac
    perm: aac.verkullan

  mutele:
    calistirilacak: litebans:mute kullanıcı 30m Spam
    perm: spam.kullan
