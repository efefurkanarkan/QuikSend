//Uye sinifindan miras alan ve elit uye basligini belirleyen alt sinif
public class ElitUye extends Uye {
    public String baslik = "ELIT UYELER"; //Olusan nesne kendi basligini alir
    public ElitUye(String ad, String soyad, String eposta) { //ElitUye sinifinin constructor
        super(ad, soyad, eposta); //Uye adli ust siniftan miras alinir
    }
}