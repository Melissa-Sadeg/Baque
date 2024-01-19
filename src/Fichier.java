import org.json.JSONObject;
import org.json.JSONArray;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static java.util.Collections.replaceAll;

public class Fichier {
    public static final String userFilePath = "src/user.json";
    public static final String compteFilePath = "src/compteBancaire.json";

    public static JSONArray readJsonArray(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return new JSONArray(content);
    }

    public static JSONObject readJsonObject(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return new JSONObject(content);
    }

    public static void writeJsonToFile(JSONObject jsonObject, String filePath) {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonObject.toString(4)); // Indentation for readability
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static CompteBancaire readCompteBancaire(String numeroCompte) {
        try {
            JSONArray jsonArray = readJsonArray(compteFilePath);
            CompteBancaire compteBancaire = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.getString("numeroCompte").equals(numeroCompte)) {
                    //CompteBancaire compteBancaire = new CompteBancaire(jsonObject.getString("numeroCompte"), jsonObject.getString("nom"), jsonObject.getString("type"), jsonObject.getDouble("solde"), LocalDate.parse(jsonObject.getString("dateCreation")), new ArrayList<>());
                    switch (jsonObject.getString("type")) {
                        case "Courant":
                            compteBancaire = new Courant(jsonObject.getString("numeroCompte"), jsonObject.getString("nom"), jsonObject.getString("type"), jsonObject.getDouble("solde"), LocalDate.parse(jsonObject.getString("dateCreation")), new ArrayList<>());
                            break;
                        case "LDD":
                            compteBancaire = new LDD(jsonObject.getString("numeroCompte"), jsonObject.getString("nom"), jsonObject.getString("type"), jsonObject.getDouble("solde"), LocalDate.parse(jsonObject.getString("dateCreation")), new ArrayList<>());
                            break;
                        case "PEL":
                            compteBancaire = new PEL(jsonObject.getString("numeroCompte"), jsonObject.getString("nom"), jsonObject.getString("type"), jsonObject.getDouble("solde"), LocalDate.parse(jsonObject.getString("dateCreation")), new ArrayList<>());
                            break;
                        case "Livret A":
                            compteBancaire = new LivretA(jsonObject.getString("numeroCompte"), jsonObject.getString("nom"), jsonObject.getString("type"), jsonObject.getDouble("solde"), LocalDate.parse(jsonObject.getString("dateCreation")), new ArrayList<>());
                            break;
                    }
                    JSONArray historiques = jsonObject.getJSONArray("historiques");
                    for (int j = 0; j < historiques.length(); j++) {
                        JSONObject historique = historiques.getJSONObject(j);
                        compteBancaire.addHistorique(new Transaction(historique.getString("type"), historique.getDouble("montant"), LocalDate.parse(historique.getString("date"))));
                    }
                    return compteBancaire;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean updateCompteBancaire(CompteBancaire compte, Transaction transaction) {
        try {
            JSONArray jsonArray = readJsonArray(compteFilePath);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString("numeroCompte").equals(compte.getNumeroCompte())) {
                    obj.put("solde", compte.getSolde());
                    JSONObject newTransaction = new JSONObject();
                    newTransaction.put("type", transaction.getType());
                    newTransaction.put("montant", transaction.getMontant());
                    newTransaction.put("date", transaction.getDate());
                    JSONArray historiques = obj.getJSONArray("historiques");
                    historiques.put(newTransaction);
                    obj.put("historiques", historiques);
                    Files.write(Paths.get(compteFilePath), jsonArray.toString(4).getBytes());
                    System.out.println("Fichier json maj réussi !");
                    return true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Fichier json maj échoué !");
        return false;
    }

    public static Boolean ajouterCompteBancaire(User user, CompteBancaire compte) {
        try {
            JSONArray jsonArray = readJsonArray(compteFilePath);
            JSONObject obj = new JSONObject();
            obj.put("numeroCompte", compte.getNumeroCompte());
            obj.put("type", compte.getType());
            obj.put("nom", compte.getNom());
            obj.put("dateCreation", compte.getDateCreation());
            obj.put("solde", compte.getSolde());
            JSONArray historiques = new JSONArray();
            for(Transaction t : compte.getHistoriques()) {
                JSONObject h = new JSONObject();
                h.put("type", t.getType());
                h.put("montant", t.getMontant());
                h.put("date", t.getDate());
                historiques.put(h);
            }
            obj.put("historiques", historiques);
            jsonArray.put(obj);
            Files.write(Paths.get(compteFilePath), jsonArray.toString(4).getBytes());
            jsonArray = readJsonArray(userFilePath);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject o = jsonArray.getJSONObject(i);
                if(o.getString("email").equals(user.getEmail())) {
                    JSONArray comptes = o.getJSONArray("comptes");
                    comptes.put(compte.getNumeroCompte());
                    o.put("comptes", comptes);
                    Files.write(Paths.get(userFilePath), jsonArray.toString(4).getBytes());
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static User readUser(String email) {
        try {
            JSONArray jsonArray = readJsonArray(userFilePath);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.getString("email").equals(email)) {
                    User user = new User(jsonObject.getString("nom"), jsonObject.getString("prenom"), jsonObject.getString("email"), jsonObject.getString("mdp"));
                    JSONArray comptes = jsonObject.getJSONArray("comptes");
                    for(int j = 0; j < comptes.length(); j++) {
                        user.getComptes().add(readCompteBancaire(comptes.getString(j)));
                    }
                    return user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeJsonToFile(JSONArray jsonArray, String filePath) {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonArray.toString(4));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Boolean exporterHistoriques(CompteBancaire compte, ArrayList<Transaction> historiques) {
        if(historiques.isEmpty()) {
            System.out.println("Aucune transaction historique à exporter");
            return false;
        }
        JSONObject object = new JSONObject();

        object.put("date d'exportion : ", LocalDateTime.now());

        JSONObject compteObj = new JSONObject();
        compteObj.put("numeroCompte", compte.getNumeroCompte());
        compteObj.put("Nom", compte.getNom());
        compteObj.put("dateCreation", compte.getDateCreation());
        compteObj.put("solde", compte.getSolde());

        object.put("compte",compteObj);

        JSONArray jsonArray = new JSONArray();
        for(Transaction t : historiques) {
            JSONObject obj = new JSONObject();
            obj.put("type", t.getType());
            obj.put("montant", t.getMontant());
            obj.put("date", t.getDate());
            jsonArray.put(obj);
        }

        object.put("historiques", jsonArray);
        String filePath = "exports\\" + compte.getNumeroCompte().replaceAll("\\s","") + "-" + LocalDate.now() + ".json";
        writeJsonToFile(object, filePath);
        return true;
    }

}
