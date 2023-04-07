//Uye sinifindan miras alan ve elit uye basligini belirleyen alt sinif
public class GenelUye extends Uye {
    public String baslik = "GENEL UYELER"; //Olusan nesne kendi basligini alir
    public GenelUye(String ad, String soyad, String eposta) { //GenelUye sinifinin constructor
        super(ad, soyad, eposta); //Uye adli ust siniftan miras alinir
    }
}