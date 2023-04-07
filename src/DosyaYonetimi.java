//Gerekli kutuphaneler ve siniflar eklendi
import java.io.BufferedReader; //Dosya Okuma islemleri
import java.io.File; //Dosya olusturma islemleri
import java.io.FileReader; //Dosya okuma islemleri
import java.io.FileWriter; //Dosya yazma islemleri

//Dosyayi hazirlayan ve baslik ekleyen sinif
public class DosyaYonetimi {
    public void uyeEkle(String baslik, String ad, String soyad, String eposta) { //Dosyaya uye ekleyen metot
        try {//Dosya bulunmuyorsa olusturuldu
            File dosya = new File("kullanicilar.txt");
            if (!dosya.exists()) dosya.createNewFile();
            
            //Basliklar dosyada bulunmuyorsa basliklar olusturuldu
            BufferedReader br1 = new BufferedReader(new FileReader(dosya)); //Okunan veriyi bellekte karakter dizisi olarak tutar
            if(br1.readLine() == null) {
                FileWriter fw1 = new FileWriter(dosya); //Dosyaya karakter girdisi yapar
                fw1.write("ELIT UYELER\nGENEL UYELER");
                fw1.close(); //Dosya yazma islemi bitirildi
            }
            br1.close(); //Dosya okuma islemi bitirildi
            
            //Alinan uye bilgileri gerekli satira yerlestirilir
            String satir; //Satirlari sirayla gezer
            StringBuilder sb = new StringBuilder(); //Degistirilebilir karakter dizisi olusturur
            BufferedReader br2 = new BufferedReader(new FileReader(dosya)); //Okunan veriyi bellekte karakter dizisi olarak tutar
            while ((satir = br2.readLine()) != null) { //Satirlar dosya sonuna kadar sirayla gezildi
                if (satir.equals(baslik)) { //İlgili basliga denk gelinirse alt satira uye girisi yapilir
                    sb.append(satir).append("\n");
                    sb.append(String.format("%s\t%s\t%s\n", ad, soyad, eposta));
                } else sb.append(satir).append("\n"); //İlgili baslik bulunamazsa sonraki satira gecilir
            }
            br2.close(); //Dosya okuma islemi bitirildi
            
            FileWriter fw2 = new FileWriter(dosya); //Dosyaya karakter girdisi yapar
            fw2.write(sb.toString()); //StringBuilder sinifindan sb nesnesinin icerigi dosyaya yazildi
            fw2.close(); //Dosya yazma islemi bitirildi
            System.out.println("Uye basariyla eklendi."); //Bilgi mesaji gosterildi
        } catch (Exception e) { //Calistirma sirasinda hata yasanirsa mesaj yazdirilir
            System.out.println("Dosya isleme hatasi: " + e.getMessage());
        }
    }
}