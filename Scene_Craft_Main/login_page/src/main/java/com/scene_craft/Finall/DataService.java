package com.scene_craft.Finall;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class DataService {
    private static Firestore db;

    static {
        try {
            initialFirebase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initialFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("login_page\\src\\main\\resources\\serviceKey.json");

        @SuppressWarnings("deprecation")
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://scene-craft-default-rtdb.asia-southeast1.firebasedatabase.app")
                .build();

        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
    }

    public void addData(String collection, String document, Map<String, Object> data) throws ExecutionException, InterruptedException{
        DocumentReference docref = db.collection(collection).document(document);
        ApiFuture<WriteResult> result = docref.set(data);

        result.get();
    }

    public DocumentSnapshot getData(String collection, String document) throws ExecutionException, InterruptedException {
        try {
            DocumentReference docref = db.collection(collection).document(document);
        ApiFuture<DocumentSnapshot> future = docref.get();
        return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean authenticateUser(String username, String password) throws  ExecutionException, InterruptedException {
        DocumentSnapshot document = db.collection("users").document(username).get().get();
        if (document.exists()) {
            String storedPassword = document.getString("password");
            return password.equals(storedPassword);
        }
        return false;
    }

    public boolean isAdmin(String username) throws ExecutionException, InterruptedException {
        DocumentSnapshot document = db.collection("users").document(username).get().get();
        if (document.exists()) {
            String role = document.getString("role");
            System.out.println(role);
            return "admin".equals(role);
        }
        return false;
    }

    public void deleteProject(String collectionName, String leaderName) throws ExecutionException, InterruptedException {
        System.out.println(leaderName);
        ApiFuture<WriteResult> writeResult = db.collection(collectionName).document(leaderName).delete();
        writeResult.get();
    }

    public List<Map<String, Object>> getDataInDescendingOrder(String collectionName, String orderByField) 
            throws ExecutionException, InterruptedException {
        List<Map<String, Object>> documentsList = new ArrayList<>();

        // Create a query against the collection
        CollectionReference collection = db.collection(collectionName);
        Query query = collection.orderBy(orderByField, Query.Direction.DESCENDING);

        // Execute the query
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            documentsList.add(document.getData());
        }

        return documentsList;
    }

    public void updateData(String collectionName, String documentId, Map<String, Object> updatedData)
            throws ExecutionException, InterruptedException {
        // Reference to the Firestore collection
        CollectionReference collection = db.collection(collectionName);

        // Reference to the document to update
        DocumentReference docRef = collection.document(documentId);
        ApiFuture<WriteResult> future = docRef.set(updatedData, SetOptions.merge());

        // Wait for the update to complete
        future.get();
    }

    public List<String>  getDataForUser(String username) throws ExecutionException, InterruptedException {
        DocumentSnapshot document = db.collection("users").document(username).get().get();
        if (document.exists()) {
            String gdUser = document.getString("name");
            String gdaccountUse = document.getString("accountUse");
            String gdEmail = document.getString("email");
            ArrayList<String> al=new ArrayList<>();
            al.add(gdUser);
            al.add(gdaccountUse);
            al.add(gdEmail);
            return al;
        }
        return null;
    }

    public List<Map<String, Object>> getAllDocuments(String collectionName) throws ExecutionException, InterruptedException {
        List<Map<String, Object>> documentsList = new ArrayList<>();
        CollectionReference collection = db.collection(collectionName);
        ApiFuture<QuerySnapshot> querySnapshot = collection.get();
    
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            documentsList.add(document.getData());
        }
        return documentsList;
    }
}

