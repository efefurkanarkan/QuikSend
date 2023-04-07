//GenelUye ve ElitUye siniflarina miras veren ust sinif
public class Uye {
    private String ad, soyad, eposta; //Uye sinifinin ozellikleri
    
    public Uye(String ad, String soyad, String eposta) { //Uye nesnesi icin dolu constructor
        this.ad = ad;
        this.soyad = soyad;
        this.eposta = eposta;
    }
    //getter metodlari
    public String getAd() {
        return ad;
    }
    
    public String getSoyad() {
        return soyad;
    }
    
    public String getEposta() {
        return eposta;
    }
}