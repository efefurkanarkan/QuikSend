/*  BASLAMADAN ONCE
1.  Programin calismasi icin javax.mail.jar ve activation-1.1.1.jar dosyalarinin proje dizinine dahil edilmesi ve bagimlilik olarak eklenmesi gerekmektedir.
2.  Program yalnizca Gmail ile calistigindan PostaYonetimi.java sinifinin sifre degiskenini belirlemek icin: https://support.google.com/mail/answer/185833?hl=tr
3.  Programin calismasi icin PostaYonetimi.java sinifinin gonderen degiskenini kendi gmail adresiniz olarak ayarlayiniz.
4.  Programa kullanici girisi yaparaken "ad soyad eposta" formatini kullaniniz. Eger kullanicinin birden cok adi varsa kelimeler arasina bosluk koymayiniz.  */

//Gerekli kutuphane eklendi
import java.util.Scanner; //Girdi alma islemleri

//Programin yurutuldugu ana sinif
public class Main {
    public static void main(String[] args) {
        int secim; //Menu secimlerini tutan degisken
        boolean cikis = false; //Programdan cikis yapilma durumunu kontrol eden degisken
        Scanner sc = new Scanner(System.in); //Kullanici girislerini alan Scanner nesnesi
        PostaYonetimi postaci = new PostaYonetimi(); //Posta islemlerini yuruten nesne
        DosyaYonetimi dosyaci = new DosyaYonetimi(); //Dosya islemlerini yuruten nesne
        
        do {
            //Ana menu
            System.out.println("***** QUIKSEND'E HOSGELDINIZ *****");
            System.out.println("1: Elit Uye Ekle\n2: Genel Uye Ekle");
            System.out.println("3: E-Posta Gonder\n0: Programi Bitir");
            System.out.print("Seciminiz: ");
            secim = sc.nextInt(); //Kullanicidan islem secimi alindi
            sc.nextLine(); //Dummy Scanner kullanildi
            
            switch (secim) {
                case 0 -> { //Cikis secenegi secilirse calisir
                    System.out.println("Cikis yapiliyor...");
                    cikis = true;
                }
                case 1 -> { //Elit Uye EKle secenegi secilirse calisir
                    System.out.println("Lutfen ad, soyad ve e-posta adresi bilgilerini sirayla ve aralarina bosluk koyarak giriniz: ");
                    String[] elitUyeBilgi = sc.nextLine().split(" "); //Tek satirda girdi alinarak diziye atandi
                    //ElitUye sinifindan yeni bir nesne, onceki satirda alinan verilerle birlikte uretildi
                    ElitUye elitUye = new ElitUye(elitUyeBilgi[0], elitUyeBilgi[1], elitUyeBilgi[2]);
                    //dosyaci nesnesiyle DosyaYonetimi sinifindan uyeEkle() metoduna erisildi (bir parametresi uyenin basligidir)
                    dosyaci.uyeEkle(elitUye.baslik, elitUye.getAd(), elitUye.getSoyad(), elitUye.getEposta());
                }
                case 2 -> { //Genel Uye Ekle secenegi secilirse calisir
                    System.out.println("Lutfen ad, soyad ve e-posta adresi bilgilerini sirayla ve aralarina bosluk koyarak giriniz: ");
                    String[] genelUyeBilgi = sc.nextLine().split(" "); //Tek satirda girdi alinarak diziye atandi
                    //GenelUye sinifindan yeni bir nesne, onceki satirda alinan verilerle birlikte uretildi
                    GenelUye genelUye = new GenelUye(genelUyeBilgi[0], genelUyeBilgi[1], genelUyeBilgi[2]);
                    //dosyaci nesnesiyle DosyaYonetimi sinifindan uyeEkle() metoduna erisildi (bir parametresi uyenin basligidir)
                    dosyaci.uyeEkle(genelUye.baslik,genelUye.getAd(), genelUye.getSoyad(), genelUye.getEposta());
                }
                case 3 -> { //E-Posta Gonder secenegi secilirse calisir
                    System.out.println("Gonderilecek e-postanin konusunu giriniz: ");
                    String konu = sc.nextLine(); //Gonderilecek e-postanin konusu alindi
                    System.out.println("Gonderilecek e-postanin icerigini giriniz: ");
                    String icerik = sc.nextLine(); //Gonderilecek e-postanin icerigi alindi
                    //Alt menu
                    System.out.println("1: Elit Uyelere Gonder\n2: Genel Uyelere Gonder");
                    System.out.println("3: Tum Uyelere Gonder\n0: Ana Menuye Don");
                    System.out.print("Seciminiz: ");
                    secim = sc.nextInt(); //Kullanicidan gonderme secenegi alindi
                    sc.nextLine(); //Dummy Scanner kullanildi
                    
                    switch (secim) {
                        case 0 -> System.out.println("Ana menuye donuluyor..."); //Ana Menuye Don secenegi secilirse calisir
                        //postaci nesnesiyle PostaYonetimi sinifindan postaGonder() metoduna (bir parametresi postaSec() metodudur) erisildi
                        case 1, 2, 3 -> postaci.postaGonder(konu, icerik, postaci.postaSec(secim));
                        default -> System.out.println("Hatali giris yapildi."); //Alt menudeki hatali giris durumunda uyari mesaji yazdirildi
                    }
                }
                default -> System.out.println("Hatali giris yapildi."); //Ana menudeki hatali giris durumunda uyari mesaji yazdirildi
            }
        } while (!cikis); //Cikis secenegi secildiyse dongu sonlandirildi
    }
}