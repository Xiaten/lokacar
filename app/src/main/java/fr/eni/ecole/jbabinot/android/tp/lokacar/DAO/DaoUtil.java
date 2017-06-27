package fr.eni.ecole.jbabinot.android.tp.lokacar.DAO;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Agence;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Categorie;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Client;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Location;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Marque;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Modele;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Region;
import fr.eni.ecole.jbabinot.android.tp.lokacar.Model.Voiture;

/**
 * Created by jbabinot2015 on 27/06/2017.
 */

public class DaoUtil {
    public static void insertData(){
        List<Region> region = SQLite.select()
                .from(Region.class)
                .queryList();
        if(region == null || region.size()<1) {
            Region region1 = new Region("Alsace");
            region1.save();
            Region region2 = new Region("Aquitaine");
            region2.save();
            Region region3 = new Region("Auvergne");
            region3.save();
            Region region4 = new Region("Basse-Normandie");
            region4.save();
            Region region5 = new Region("Bourgogne");
            region5.save();
            Region region6 = new Region("Bretagne");
            region6.save();
            Region region7 = new Region("Champagne-Ardenne");
            region7.save();
            Region region8 = new Region("Corse");
            region8.save();
            Region region9 = new Region("Franche-Comté");
            region9.save();
            Region region10 = new Region("Haute-Normandie");
            region10.save();
            Region region11 = new Region("Île-de-France");
            region11.save();
            Region region12 = new Region("Languedoc-Roussillon");
            region12.save();
            Region region13 = new Region("Limousin");
            region13.save();
            Region region14 = new Region("Lorraine");
            region14.save();
            Region region15 = new Region("Midi-Pyrénées");
            region15.save();
            Region region16 = new Region("Nord-Pas-de-Calais");
            region16.save();
            Region region17 = new Region("Pays de la Loire");
            region17.save();
            Region region18 = new Region("Picardie");
            region18.save();
            Region region19 = new Region("Poitou-Charentes");
            region19.save();
            Region region20 = new Region("Provence-Alpes-Côte d\'Azur");
            region20.save();
            Region region21 = new Region("Rhône-Alpes");
            region21.save();
            Region region22 = new Region("Centre");
            region22.save();

            Agence agence1 = new Agence("Bourg-en-Bresse", region21); agence1.save();
            Agence agence2 = new Agence("Laon", region18); agence2.save();
            Agence agence3 = new Agence("Moulins", region3); agence3.save();
            Agence agence4 = new Agence("Digne", region20); agence4.save();
            Agence agence5 = new Agence("Gap", region20); agence5.save();
            Agence agence6 = new Agence("Nice", region20); agence6.save();
            Agence agence7 = new Agence("Privas", region21); agence7.save();
            Agence agence8 = new Agence("Charleville-Mézières", region7); agence8.save();
            Agence agence9 = new Agence("Foix", region15); agence9.save();
            Agence agence10 = new Agence("Troyes", region7); agence10.save();
            Agence agence11 = new Agence("Carcassonne", region12); agence11.save();
            Agence agence12 = new Agence("Rodez", region15); agence12.save();
            Agence agence13 = new Agence("Marseille", region20); agence13.save();
            Agence agence14 = new Agence("Caen", region4); agence14.save();
            Agence agence15 = new Agence("Aurillac", region3); agence15.save();
            Agence agence16 = new Agence("Angoulême", region19); agence16.save();
            Agence agence17 = new Agence("La Rochelle", region19); agence17.save();
            Agence agence18 = new Agence("Bourges", region22); agence18.save();
            Agence agence19 = new Agence("Tulle", region13); agence19.save();
            Agence agence20 = new Agence("Ajaccio", region8); agence20.save();
            Agence agence21 = new Agence("Bastia", region8); agence21.save();
            Agence agence22 = new Agence("Dijon", region5); agence22.save();
            Agence agence23 = new Agence("Saint-Brieuc", region6); agence23.save();
            Agence agence24 = new Agence("Guéret", region13); agence24.save();
            Agence agence25 = new Agence("Périgueux", region2); agence25.save();
            Agence agence26 = new Agence("Besançon", region9); agence26.save();
            Agence agence27 = new Agence("Valence", region21); agence27.save();
            Agence agence28 = new Agence("Évreux", region10); agence28.save();
            Agence agence29 = new Agence("Chartres", region22); agence29.save();

            Categorie categorie1 = new Categorie("Mini citadine"); categorie1.save();
            Categorie categorie2 = new Categorie("Citadine"); categorie2.save();
            Categorie categorie3 = new Categorie("Berline"); categorie3.save();
            Categorie categorie4 = new Categorie("Coupé Cabriolet"); categorie4.save();
            Categorie categorie5 = new Categorie("Monospace"); categorie5.save();
            Categorie categorie6 = new Categorie("SUV"); categorie6.save();
            Categorie categorie7 = new Categorie("4x4"); categorie7.save();

            Marque marque1 = new Marque("Chevrolet"); marque1.save();
            Marque marque2 = new Marque("Citroën"); marque2.save();
            Marque marque3 = new Marque("Dacia"); marque3.save();
            Marque marque4 = new Marque("Fiat"); marque4.save();
            Marque marque5 = new Marque("Chrysler"); marque5.save();
            Marque marque6 = new Marque("Ford"); marque6.save();
            Marque marque7 = new Marque("Honda"); marque7.save();
            Marque marque8 = new Marque("Hyundai"); marque8.save();
            Marque marque9 = new Marque("Kia"); marque9.save();
            Marque marque10 = new Marque("Lancia"); marque10.save();
            Marque marque11 = new Marque("Mazda"); marque11.save();
            Marque marque12 = new Marque("Mitsubishi"); marque12.save();
            Marque marque13 = new Marque("Nissan"); marque13.save();
            Marque marque14 = new Marque("Opel"); marque14.save();
            Marque marque15 = new Marque("Peugeot"); marque15.save();
            Marque marque16 = new Marque("Renault"); marque16.save();
            Marque marque17 = new Marque("Seat"); marque17.save();
            Marque marque18 = new Marque("Škoda"); marque18.save();
            Marque marque19 = new Marque("Subaru"); marque19.save();
            Marque marque20 = new Marque("Suzuki"); marque20.save();
            Marque marque21 = new Marque("Toyota"); marque21.save();
            Marque marque22 = new Marque("Volkswagen"); marque22.save();
            Marque marque23 = new Marque("Audi"); marque23.save();
            Marque marque24 = new Marque("Mercedes"); marque24.save();

            Modele modele1 = new Modele("Touran", 5, categorie5, marque22); modele1.save();
            Modele modele2 = new Modele("Espace", 5, categorie5, marque16); modele2.save();
            Modele modele3 = new Modele("Zafira", 5, categorie5, marque14); modele3.save();
            Modele modele4 = new Modele("Clio", 5, categorie2, marque22); modele4.save();
            Modele modele5 = new Modele("C3", 5, categorie2, marque2); modele5.save();
            Modele modele6 = new Modele("Golf", 5, categorie2, marque22); modele6.save();
            Modele modele7 = new Modele("Twingo", 5, categorie1, marque16); modele7.save();
            Modele modele8 = new Modele("Polo", 5, categorie1, marque22); modele8.save();
            Modele modele9 = new Modele("C1", 5, categorie1, marque2); modele9.save();
            Modele modele10 = new Modele("Laguna", 5, categorie3, marque16); modele10.save();
            Modele modele11 = new Modele("C5", 5, categorie3, marque2); modele11.save();
            Modele modele12 = new Modele("Passat", 5, categorie3, marque22); modele12.save();
            Modele modele13 = new Modele("Tigra", 5, categorie4, marque14); modele13.save();
            Modele modele14 = new Modele("206 cc", 5, categorie4, marque15); modele14.save();
            Modele modele15 = new Modele("Mégane cc", 5, categorie4, marque16); modele15.save();
            Modele modele16 = new Modele("Kuga", 5, categorie6, marque6); modele16.save();
            Modele modele17 = new Modele("Sportage", 5, categorie6, marque9); modele17.save();
            Modele modele18 = new Modele("Juke", 5, categorie6, marque13); modele18.save();
            Modele modele19 = new Modele("Duster", 5, categorie7, marque3); modele19.save();
            Modele modele20 = new Modele("Q5", 5, categorie7, marque23); modele20.save();
            Modele modele21 = new Modele("ML", 5, categorie7, marque24); modele21.save();

            Voiture voiture1 = new Voiture("de-123-ed", 79, 2016, 15000, modele1, agence1); voiture1.save();
            Voiture voiture2 = new Voiture("df-123-fd", 79, 2016, 1600, modele2, agence1); voiture2.save();
            Voiture voiture3 = new Voiture("dg-123-gd", 79, 2016, 17000, modele3, agence1); voiture3.save();
            Voiture voiture4 = new Voiture("dh-123-hd", 49, 2016, 18000, modele4, agence1); voiture4.save();
            Voiture voiture5 = new Voiture("di-123-id", 49, 2016, 19000, modele5, agence1); voiture5.save();
            Voiture voiture6 = new Voiture("dj-123-jd", 49, 2016, 14000, modele6, agence1); voiture6.save();
            Voiture voiture7 = new Voiture("dk-123-kd", 39, 2016, 13000, modele7, agence1); voiture7.save();
            Voiture voiture8 = new Voiture("dl-123-ld", 39, 2016, 13500, modele8, agence1); voiture8.save();
            Voiture voiture9 = new Voiture("dm-123-md", 39, 2016, 14500, modele9, agence1); voiture9.save();
            Voiture voiture10 = new Voiture("dn-123-nd", 59, 2016, 15500, modele10, agence1); voiture10.save();
            Voiture voiture11 = new Voiture("do-123-od", 59, 2016, 16500, modele11, agence1); voiture11.save();
            Voiture voiture12 = new Voiture("dp-123-pd", 59, 2016, 17500, modele12, agence1); voiture12.save();
            Voiture voiture13 = new Voiture("dq-123-qd", 69, 2016, 18500, modele13, agence2); voiture13.save();
            Voiture voiture14 = new Voiture("dr-123-rd", 69, 2016, 19500, modele14, agence2); voiture14.save();
            Voiture voiture15 = new Voiture("ds-123-sd", 69, 2016, 20000, modele15, agence2); voiture15.save();
            Voiture voiture16 = new Voiture("dt-123-td", 89, 2016, 20500, modele16, agence2); voiture16.save();
            Voiture voiture17 = new Voiture("du-123-ud", 89, 2016, 21000, modele17, agence2); voiture17.save();
            Voiture voiture18 = new Voiture("dv-123-vd", 89, 2016, 21500, modele18, agence2); voiture18.save();
            Voiture voiture19 = new Voiture("dw-123-wd", 89, 2016, 22000, modele19, agence2); voiture19.save();
            Voiture voiture20 = new Voiture("dx-123-xd", 89, 2016, 22500, modele20, agence2); voiture20.save();
            Voiture voiture21 = new Voiture("dy-123-yd", 89, 2016, 23000, modele21, agence2); voiture21.save();
            Voiture voiture22 = new Voiture("dz-123-zd", 79, 2016, 23500, modele1, agence2); voiture22.save();
            Voiture voiture23 = new Voiture("da-123-ad", 79, 2016, 24000, modele2, agence2); voiture23.save();
            Voiture voiture24 = new Voiture("db-123-bd", 79, 2016, 24000, modele3, agence2); voiture24.save();

            Client client1 = new Client("Durand", "Emma", "edurand@truc.fr", "0123456789"); client1.save();
            Client client2 = new Client("Dupond", "Gabriel", "gdupond@truc.fr", "9874563210"); client2.save();
            Client client3 = new Client("Tremblay", "Louise", "ltremblay@truc.fr", "1234567890"); client3.save();
            Client client4 = new Client("Michel", "Jules", "jmichel@truc.fr", "2345678901"); client4.save();
            Client client5 = new Client("Roy", "Jade", "jroy@truc.fr", "3456789012"); client5.save();
            Client client6 = new Client("Côté", "Lucas", "lcote@truc.fr", "4567890123"); client6.save();
            Client client7 = new Client("Bouchard", "Louis", "lbouchard@truc.fr", "5678901234"); client7.save();
            Client client8 = new Client("Gauthier", "Adam", "agauthier@truc.fr", "6789012345"); client8.save();
            Client client9 = new Client("Morin", "Chloé", "cmorin@truc.fr", "7890123456"); client9.save();
            Client client10 = new Client("Lavoie", "Manon", "mlavoie@truc.fr", "8901234567"); client10.save();
            Client client11 = new Client("Fortin", "Hugo", "hfortin@truc.fr", "9012345678"); client11.save();
            Client client12 = new Client("Gagné", "Alice", "agagne@truc.fr", "8765432109"); client12.save();
            Client client13 = new Client("Ouellet", "Lina", "louellet@truc.fr", "7654321098"); client13.save();
            Client client14 = new Client("Pelletier", "Léo", "lpelletier@truc.fr", "6543210987"); client14.save();
            Client client15 = new Client("Bélanger", "Raphaël", "rbelanger@truc.fr", "5432109876"); client15.save();
            Client client16 = new Client("Lévesque", "Léa", "llevesque@truc.fr", "4321098765"); client16.save();
            Client client17 = new Client("Bergeron", "Lola", "lbergeron@truc.fr", "3210987654"); client17.save();
            Client client18 = new Client("Leblanc", "Ethan", "eleblanc@truc.fr", "2109876543"); client18.save();
            Client client19 = new Client("Paquette", "Nathan", "npaquette@truc.fr", "1098765432"); client19.save();
            Client client20 = new Client("Girard", "Camille", "cgirard@truc.fr", "0147852369"); client20.save();

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

            try {
                Location location1 = new Location(voiture1, client1, format.parse("26-06-2017"), null); location1.save();
                Location location2 = new Location(voiture2, client2, format.parse("25-06-2017"), null); location2.save();
                Location location3 = new Location(voiture3, client3, format.parse("24-06-2017"), null); location3.save();
                Location location4 = new Location(voiture4, client4, format.parse("23-06-2017"), null); location4.save();
                Location location5 = new Location(voiture5, client5, format.parse("22-06-2017"), null); location5.save();
                Location location6 = new Location(voiture6, client6, format.parse("21-06-2017"), format.parse("26-06-2017")); location6.save();
                Location location7 = new Location(voiture7, client7, format.parse("22-06-2017"), format.parse("26-06-2017")); location7.save();
                Location location8 = new Location(voiture8, client8, format.parse("23-06-2017"), format.parse("26-06-2017")); location8.save();
                Location location9 = new Location(voiture9, client9, format.parse("24-06-2017"), format.parse("26-06-2017")); location9.save();
                Location location10 = new Location(voiture10, client10, format.parse("25-06-2017"), format.parse("26-06-2017")); location10.save();
                Location location11 = new Location(voiture11, client11, format.parse("25-06-2017"), null); location11.save();
                Location location12 = new Location(voiture12, client12, format.parse("24-06-2017"), null); location12.save();
                Location location13 = new Location(voiture13, client13, format.parse("23-06-2017"), null); location13.save();
                Location location14 = new Location(voiture14, client14, format.parse("22-06-2017"), null); location14.save();
                Location location15 = new Location(voiture15, client15, format.parse("21-06-2017"), null); location15.save();
                Location location16 = new Location(voiture16, client16, format.parse("22-06-2017"), format.parse("26-06-2017")); location16.save();
                Location location17 = new Location(voiture17, client17, format.parse("23-06-2017"), format.parse("26-06-2017")); location17.save();
                Location location18 = new Location(voiture18, client18, format.parse("24-06-2017"), format.parse("26-06-2017")); location18.save();
                Location location19 = new Location(voiture19, client19, format.parse("25-06-2017"), format.parse("26-06-2017")); location19.save();
                Location location20 = new Location(voiture20, client20, format.parse("26-06-2017"), format.parse("26-06-2017")); location20.save();

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }
}
