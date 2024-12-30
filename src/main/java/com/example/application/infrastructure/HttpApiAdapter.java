package com.example.application.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HttpApiAdapter {
    private final ObjectMapper mapper;
    // Centralisation de la configuration de la classe
    // Plus besoin de reconfigurer ObjectMapper
    public HttpApiAdapter() {
        this.mapper = new ObjectMapper();
    }
    // T et R sont des tyes génériques, ils permettent de définir les types personnalisés lors de l'appel de la methode
    // Cette methode peut utiliser differents types d'objets
    // T = type d'objet à envoyer dans le corps de la requete
    // R = type de reponse qu'on veut recevoir
    public <T, R> R sendDataRequest(String apiURL, String requestMethod, T requestbody, Class<R> responseClass) {
        try {
            // Chemin API
            URL url = new URL(apiURL);
            // Ouverture de la connexion
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);
            // Parametrage de l'envoie de la requete
            connection.setRequestProperty("Content-Type", "application/json");
            // Autoriser la sortie des informations (de base est en false)
            connection.setDoOutput(true);
            // Utilisation de la librairie jackson
            // Lecture de la reponse JSON de l'API
            // Convertie l'objet users en CC au format JSON
            String jsonEmployee = mapper.writeValueAsString(requestbody);
            // Decocher la ligne du bas pour débug et voir les données json envoyées
            //System.out.println(jsonEmployee);
            // connection.getoutputStream permet d'envoyer les données
            try (OutputStream os = connection.getOutputStream()) {
                // Conversion du JSON en bytes (necessaire pour l'envoi)
                os.write(jsonEmployee.getBytes());
                // Force l'envoi immediat des données, ça limite la perte d'infirmation
                os.flush();
            }
            if (connection.getResponseCode() == 200 || connection.getResponseCode() == 201) {
                // Désérialisation de la réponse en Employee
                return mapper.readValue(connection.getInputStream(), responseClass);
            } else {
                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Method error", e);
        }
    }

    public <T> List<T> sendGetAllRequest(String apiURL, Class<T> type) {
        // Déclaration d'une liste générique
        // Cette liste va contenir des elements de type T, on pourra passer n'importe quelle classe
        // ex si T est Suppliers alors List<T> equivaut à List<Suppliers>
        List<T> resultList;
        try {
            URL url = new URL(apiURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() == 200 || connection.getResponseCode() == 201) {
                // Définit un type générique pour désérialiser la réponse en une liste d'objets de type T
                CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, type);
                // Lit le flux de réponse (InputStream) et convertit le JSON en une liste d'objets de type T
                resultList = mapper.readValue(connection.getInputStream(), listType);
            } else {
                throw new RuntimeException("Erreur HTTP" + connection.getResponseMessage());
            }
            // Les ressources sont libérées à la suite de la requete
            connection.disconnect();

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des données" +e.getMessage(), e);
        }
        return resultList;
    }

    public boolean deleteMethodById(String apiURL) {
        try {
            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            return conn.getResponseCode() == 200;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression des données" +e.getMessage(), e);
        }
    }
}
