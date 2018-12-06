package com.example.preprodactionmessenger.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.preprodactionmessenger.R;
import com.example.preprodactionmessenger.adapter.MessageAdapter;
import com.example.preprodactionmessenger.model.FriendlyMessage;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final String EMPTY_START_MESSAGE = "";
    public static final String REFERENCE_MESSAGES = "messages";
    public static final String REFERENCE_PHOTOS = "chat_photos";
    public static final String USERNAME = "username";
    private static final String TAG = "MainActivity";
    private static final int RC_SIGN_IN = 123;
    private static final int RC_PHOTO_PICKER = 2;

    @BindView(R.id.progress_bar)
    protected ProgressBar progressBar;
    @BindView(R.id.et_message)
    protected EditText etMessage;
    @BindView(R.id.rv_messages)
    protected RecyclerView rvMessenger;
    @BindView(R.id.image_btn_photo_picker)
    protected ImageButton imBtnPhotoPicker;
    @BindView(R.id.btn_send)
    protected Button btnSend;
    private List<FriendlyMessage> messengerList;
    private MessageAdapter adapter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference messageDatabaseReference;
    private String userName;
    private FriendlyMessage friendlyMessage;
    private ChildEventListener childEventListener;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseStorage firebaseStorage;

    private StorageReference chatPhotosStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userName = ANONYMOUS;
        initProgressBar();
        initializeInstances();
        initAdapterAndRecyclerView();
        addListenerToButtons();
        addTextChangedListener();
        readFromDatabase();
    }

    private void readFromDatabase() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    onSignedInInitialize(user.getDisplayName());
                    Toast.makeText(MainActivity.this, getString(R.string.sign_in), Toast.LENGTH_SHORT).show();
                } else {
                    onSignedOutCleanup();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.EmailBuilder().build()))
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }

    public void clear() {
        messengerList.clear();
        adapter.notifyDataSetChanged();
    }

    private void onSignedOutCleanup() {
        userName = ANONYMOUS;
        clear();
        detachDatabaseReadListener();
    }

    private void onSignedInInitialize(String userName) {
        this.userName = userName;
        attachDatabaseReadListener();
    }

    private void detachDatabaseReadListener() {
        if (childEventListener != null) {
            messageDatabaseReference.removeEventListener(childEventListener);
            childEventListener = null;
        }
    }

    private void attachDatabaseReadListener() {
        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    FriendlyMessage loadMessage = dataSnapshot.getValue(FriendlyMessage.class);
                    messengerList.add(loadMessage);
                    Log.d(TAG, "onClick: " + loadMessage);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            };
            messageDatabaseReference.addChildEventListener(childEventListener);
        }
    }

    @Override
    protected void onResume() {
        firebaseAuth.addAuthStateListener(authStateListener);
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
        detachDatabaseReadListener();
        clear();
        super.onPause();
    }

    private void initProgressBar() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    private void initializeInstances() {
        messengerList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        messageDatabaseReference = firebaseDatabase.getReference().child(REFERENCE_MESSAGES);
        chatPhotosStorageReference = firebaseStorage.getReference().child(REFERENCE_PHOTOS);
    }

    private void initAdapterAndRecyclerView() {
        adapter = new MessageAdapter(messengerList);
        rvMessenger.setAdapter(adapter);
        LinearLayoutManager linearVertical = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMessenger.setLayoutManager(linearVertical);
        adapter.notifyDataSetChanged();
    }

    private void addListenerToButtons() {
        imBtnPhotoPicker.setOnClickListener(this);
        btnSend.setOnClickListener(this);
    }

    private void addTextChangedListener() {
        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.toString().trim().length() > 0) {
                    btnSend.setEnabled(true);
                } else {
                    btnSend.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etMessage.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_btn_photo_picker:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, getString(R.string.photo_picker_title)), RC_PHOTO_PICKER);
                break;
            case R.id.btn_send:
                friendlyMessage = new FriendlyMessage(etMessage.getText().toString(), userName, null);
                messageDatabaseReference.push().setValue(friendlyMessage);
                etMessage.setText(EMPTY_START_MESSAGE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, getString(R.string.sign_in), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, getString(R.string.canceled_sign_in), Toast.LENGTH_LONG).show();
                finish();
            }
        } else if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            attachPhoto(data);
        }
    }

    private void attachPhoto(Intent data) {
        Uri selectedImageUri = data.getData();
        final StorageReference photoRef = chatPhotosStorageReference.child(selectedImageUri.getLastPathSegment());
        photoRef.putFile(selectedImageUri);
        chatPhotosStorageReference.putFile(selectedImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return chatPhotosStorageReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    FriendlyMessage preproductionMessenger = new FriendlyMessage(null, userName, downloadUri.toString());
                    messageDatabaseReference.push().setValue(preproductionMessenger);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                return true;
            case R.id.location:
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                intent.putExtra(USERNAME, userName);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}