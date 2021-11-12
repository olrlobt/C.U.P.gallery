package com.example.testgallery.fragments.mainFragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.testgallery.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ChangePassFragment extends BottomSheetDialogFragment {
    private View view;
    private Button btnConfirm;
    private Button btnCancel;
    EditText oldPass;
    EditText newPass;
    EditText confirmPass;
    String password;
    SharedPreferences settings;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_secret_changepass, container,false);
        settings = getActivity().getSharedPreferences("PREFS",0);
        password = settings.getString("password","");
        getParentFragmentManager().beginTransaction().add(this,"ChangePass_frag");
        mapping();
        event();
        return view;
    }

    public void event(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Cancel", Toast.LENGTH_SHORT).show();
                Fragment thisFrag = getParentFragmentManager().findFragmentByTag("ChangePass_frag");
                getParentFragmentManager().beginTransaction()
                        .remove(thisFrag)
                        .commit();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPassText = oldPass.getText().toString();
                String newPassText = newPass.getText().toString();
                String confirmPassText = confirmPass.getText().toString();
                if(!oldPassText.equals(password)){
                    Toast.makeText(getActivity(),"Wrong Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!newPassText.equals(confirmPassText)){
                    Toast.makeText(getActivity(),"ConfirmPassword doesn't match", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("password",newPassText);
                editor.apply();
                Toast.makeText(getActivity(),"Change Password Success", Toast.LENGTH_SHORT).show();
                Fragment thisFrag = getParentFragmentManager().findFragmentByTag("ChangePass_frag");
                getParentFragmentManager().beginTransaction()
                        .remove(thisFrag)
                        .commit();
            }
        });
    }
    public void mapping(){
        oldPass = view.findViewById(R.id.oldpass);
        newPass = view.findViewById(R.id.newpass);
        confirmPass = view.findViewById(R.id.confirmpass);
        btnConfirm = view.findViewById(R.id.btnChangePass);
        btnCancel = view.findViewById(R.id.btnCancel);
    }
}