//Gerekli kutuphaneler ve siniflar eklendi
import javax.mail.*; //Dosya islemleri
import javax.mail.internet.InternetAddress; //E-posta adresi islemleri
import javax.mail.internet.MimeMessage; //MIME formatli e-posta islemleri
import java.io.BufferedReader; //Dosya okuma islemleri
import java.io.FileReader; //Dosya okuma islemleri
import java.util.ArrayList; //Dinamik liste islemleri
import java.util.List; //Liste islemleri
import java.util.Properties; //Yapilandirma islemleri

//E-posta gonderilecek adresi secen ve e-posta gonderen sinif
public class PostaYonetimi {
    public String postaSec(int secim) { //E-posta gonderilecek adresleri belirleyen metot
        List<String> elitUyeler = new ArrayList<>(); //Elit uyelerin listesi
        List<String> genelUyeler = new ArrayList<>(); //Genel uyelerin listesi
        String alici = ""; //Sonradan atanacak degerler icin null olarak tanimlandi
        
        try {
            //E-posta atilacak adresler dosyadan okunarak listelere kaydedilir
            BufferedReader br = new BufferedReader(new FileReader("kullanicilar.txt")); //Okunan veriyi bellekte karakter dizisi olarak tutar
            String satir = br.readLine(); //Dosyanin ilk satiriyla okuma baslatildi
            while (satir != null) { //Satirlar dosya sonuna kadar sirayla gezildi
                if (satir.equals("ELIT UYELER")) { //ELIT UYELER basligina ulasildi
                    satir = br.readLine(); //Sonraki satira gecildi
                    while (!satir.equals("GENEL UYELER")) { //GENEL UYELER basligina kadar okuma yapildi
                        String[] uyeBilgi = satir.split("\t"); //Bilgiler TAB karakterine gore bolundu
                        String eposta = uyeBilgi[2]; //Dosya bilgilerinden eposta bilgisi okundu
                        elitUyeler.add(eposta); //Elit uyelerin eposta bilgileri listeye eklendi
                        satir = br.readLine(); //Sonraki satira gecildi
                    }
                }
                if (satir.equals("GENEL UYELER")) { //GENEL UYELER basligina ulasildi
                    satir = br.readLine(); //Basliktan sonraki ilk satirla okuma baslatildi
                    while (satir != null) { //Satirlar dosya sonuna kadar sirayla gezildi
                        String[] uyeBilgi = satir.split("\t"); //Bilgiler TAB karakterine gore bolundu
                        String eposta = uyeBilgi[2]; //Dosya bilgilerinden eposta bilgisi okundu
                        genelUyeler.add(eposta); //Genel uyelerin eposta bilgileri listeye eklendi
                        satir = br.readLine(); //Sonraki satira gecildi
                    }
                }
                satir = br.readLine(); //Sonraki satira gecildi
                br.close(); //Dosya okuma islemi bitirildi
            }
            
            //Kaydedilen listeler kullanilarak alici adresleri postaGonder() metoduna parametre olarak gonderilir
            if (secim == 1) { //Elit Uyelere Gonder secilirse calisir
                alici = String.join(",", elitUyeler); //Alici adresleri virgul karakterleriyle ayrilir
            } else if (secim == 2) { //Genel Uyelere Gonder secilirse calisir
                alici = String.join(",", genelUyeler); //Alici adresleri virgul karakterleriyle ayrilir
            } else if (secim == 3) { //Tum Uyelere Gonder secilirse calisir
                List<String> tumUyeler = new ArrayList<>(); //Tum uyelerin listesi
                tumUyeler.addAll(elitUyeler); //Elit uyeler tum uyelere dahil edildi
                tumUyeler.addAll(genelUyeler); //Genel uyeler tum uyelere dahil edildi
                alici = String.join(",", tumUyeler); //Alici adresleri virgul karakterleriyle ayrilir
            }
        } catch (Exception e) { //Calistirma sirasinda hata yasanirsa mesaj yazdirilir
            System.out.println("Dosya isleme hatasi: " + e.getMessage());
        }
        return alici; //Alici adresleri gonderilir
    }
    public void postaGonder(String konu, String icerik, String alici){ //Parametreleri alarak e-posta gonderen metot
        final String gonderen = "*****@gmail.com"; //BURAYA GONDERENIN E-POSTA ADRESI GIRILECEK
        final String sifre = "*****"; //BURAYA GONDERENIN UYGULAMA SIFRESI GIRILECEK
        
        Properties prop = new Properties(); //E-posta ayarlari icin gerekli Properties nesnesi
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(prop, //E-posta gonderimi icin olusturulan Session nesnesi
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(gonderen, sifre);
                    }
                });
        
        try {
            Message mesaj = new MimeMessage(session); //E-postanin olusturuldugu Message nesnesi
            mesaj.setFrom(new InternetAddress(gonderen));
            mesaj.setRecipients(Message.RecipientType.TO, InternetAddress.parse(alici));
            mesaj.setSubject(konu);
            mesaj.setText(icerik);
            Transport.send(mesaj); //E-posta gonderildi
            System.out.println("E-posta basariyla gonderildi."); //Bilgi mesaji gosterildi
        } catch (MessagingException e) { //Calistirma sirasinda hata yasanirsa mesaj yazdirilir
            System.out.println("E-posta gonderme hatasi: " + e.getMessage());
        }
    }
}