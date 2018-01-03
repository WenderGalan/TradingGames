package tradinggames.com.tradinggames.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import tradinggames.com.tradinggames.R;
import tradinggames.com.tradinggames.models.Post;

/**
 * Created by Wender Galan Gamer on 10/12/2017.
 */

public class AnuncioAdapter extends ArrayAdapter<Post> implements Runnable{

    private final ArrayList<Post> elementos;
    private final Context context;
    private Bitmap bitmap;
    private Drawable d = new BitmapDrawable(bitmap);
    private String imagemRecebida = null;
    private ImageView imagem;
    private TextView titulo;
    private TextView descricaoCurta;
    private TextView preco;

    public AnuncioAdapter(Context context, ArrayList<Post> elementos) {
        super(context, R.layout.list_row, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_row, parent, false);

        imagem = rowView.findViewById(R.id.imageViewList);
        titulo = rowView.findViewById(R.id.title);
        descricaoCurta = rowView.findViewById(R.id.descricaoCurta);
        preco = rowView.findViewById(R.id.preco);


        //A IMAGEM VEM EM FORMA DE VETOR
        /*
        String [] imagens = elementos.get(position).getImagens();

        if (imagens[0] != null){
            imagemRecebida = imagens[0];
            //chama a Thread para fazer o downlaod da imagem
            new Thread(this).start();
        }
        */

        //Setando os atributos de cada linha
        imagem.setImageBitmap(bitmap);
        titulo.setText(elementos.get(position).getPostTitle());
        descricaoCurta.setText(elementos.get(position).getPostDescription());
        String aux =  Double.toString(elementos.get(position).getProductPrice());
        preco.setText(aux);

        return rowView;
    }

    public void run() {
        try {
            // link para imagem
            bitmap = downloadImagem(imagemRecebida);

        } catch (Throwable e) {
            //chamaria o dialog
            Log.e("Erro", e.getMessage(), e);
        } finally {
            //chamaria o dialog
        }
    }

    public final Bitmap downloadImagem(String url) {
        Log.i("TESTE", "Http.Download: " + url);
        try {
            URL u = new URL(url);
            InputStream in = u.openStream();
            Bitmap img = BitmapFactory.decodeStream(in);
            in.close();
            return img;
        } catch (Exception e) {
            Log.e("Erro", e.getMessage());
        }
        return null;
    }

    public void setaImagem(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imagem.setImageBitmap(bitmap);
            }
        });

    }

    public final void runOnUiThread(Runnable action) {
        throw new RuntimeException("Stub!");
    }

}
