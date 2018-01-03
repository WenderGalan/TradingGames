package tradinggames.com.tradinggames.util;

import android.text.TextUtils;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

public class Validator {

    //ESTE METODO RETORNA TRUE SE O CAMPO FOR NULO E FALSE CASO CONTENHA ALGO
    public boolean validateNotNull(View pView, String pMessage) {
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            Editable text = edText.getText();
            if (text != null) {
                String strText = text.toString();
                if (!TextUtils.isEmpty(strText)) {
                    return true;
                }
            }

        }
        return false;
    }

}
