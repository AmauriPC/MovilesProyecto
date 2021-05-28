package mx.aepc.proyectoparcial1moviles;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class StorageActivity {
    // Create a storage reference from our app
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    // Create a reference to "mountains.jpg"
    StorageReference mountainsRef = storageRef.child("mountains.jpg");

    // Create a reference to 'images/mountains.jpg'
    StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");

// While the file names are the same, the references point to different files

}
