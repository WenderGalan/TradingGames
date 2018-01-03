package tradinggames.com.tradinggames.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

import tradinggames.com.tradinggames.R;

/**
 * Created by Wender Galan Gamer on 10/12/2017.
 */

public class DetailAnuncio extends AppCompatActivity implements Runnable {

    private TextView titulo;
    private TextView descricao;
    private TextView preco;
    private TextView categoria;
    private TextView autor;
    private static ImageView imagem;
    private Bitmap bitmap;
    private Drawable d = new BitmapDrawable(bitmap);
    private String imagens [];
    private String imagemRecebida;
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_anuncio);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Trading Games");
        setSupportActionBar(toolbar);

        imagem = findViewById(R.id.thumbnail_detail_id);
        titulo = findViewById(R.id.textTitulo);
        descricao = findViewById(R.id.textDescricao);
        preco = findViewById(R.id.textPreco);
        categoria = findViewById(R.id.textCategoria);
        autor = findViewById(R.id.textAutor);

        Intent intent = getIntent();

        //recebe a imagem
        imagens = (String []) intent.getSerializableExtra("imagens");
        imagemRecebida = imagens[0];
        Log.i("TAG", "---------------imagem recebida---------------- " + imagemRecebida);
        Log.i("TAG", "---------------vai chamar a thread---------------- ");

        if (imagemRecebida != null){
            new Thread(this).start();
        }

        Double price = (Double) intent.getSerializableExtra("preco");
        titulo.setText((String) intent.getSerializableExtra("titulo"));
        descricao.setText((String) intent.getSerializableExtra("descricao"));
        preco.setText("R$ " + Double.toString(price));
        categoria.setText("Categoria: " + (String) intent.getSerializableExtra("categoria"));
        autor.setText((String) intent.getSerializableExtra("autor"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case R.id.item_sair:
                intent = new Intent(DetailAnuncio.this, Login.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.item_anuncios:
                intent = new Intent(DetailAnuncio.this, MainAnuncios.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.item_configuracoes:
                intent = new Intent(DetailAnuncio.this, UserActivity.class);
                startActivity(intent);
                return true;

            case R.id.item_meus_anuncios:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void run() {
        try {
            Log.i("TAG", "---------------CHEGOU NA THREAD---------------- ");
            // link para imagem
            bitmap = downloadImagem(imagemRecebida);
            Log.i("TAG", "---------------BITMAP---------------- " + bitmap);
        } catch (Throwable e) {
            //chamaria o dialog
            Log.e("Erro", e.getMessage(), e);
        } finally {
            //chamaria o dialog
            Log.i("TAG", "---------------THREAD FINALIZADA---------------- ");
            setaImagem();
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
}
