package com.example.politiciianproject;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class StartAPPDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Welcome to Accountable Politician!")
                .setMessage(R.string.dialog_start_game)
                .setPositiveButton(R.string.dialog_reg_politician, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        goToLogin();
                    }
                })
                .setNegativeButton(R.string.dialog_visitor, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        goToCoverSearch();
                    }
                })
                .setNeutralButton(R.string.dialog_registry, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        goToRegistry();
                    }
                })
        ;
        // Create the AlertDialog object and return it.
        return builder.create();
    }

    private void goToRegistry(){
        Intent intent = new Intent(getActivity(), Registration.class);

        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    private void goToCoverSearch(){
        getActivity().getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frameLayout, FragmentCoverSearch.class, null)
                .commit();

    }
    private void goToLogin(){
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.frameLayout, FragmentLogin.class, null)
                    .commit();
    }

}
