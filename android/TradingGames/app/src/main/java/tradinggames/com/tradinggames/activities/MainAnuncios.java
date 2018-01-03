package tradinggames.com.tradinggames.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Outline;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import tradinggames.com.tradinggames.R;
import tradinggames.com.tradinggames.adapter.AnuncioAdapter;
import tradinggames.com.tradinggames.models.Post;
import tradinggames.com.tradinggames.tasks.GetAnunciosTask;

/**
 * Create by Wender Galan
 */

public class MainAnuncios extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private Handler handler = new Handler();

    private Intent createAnuncioIntent = getIntent();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);



        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Trading Games");
        setSupportActionBar(toolbar);

        //Verificando se o aparelho está no V21 ou não
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            initLFloatingButtons();
        } else{
            //implementações abaixo
        }

        ListView lista = findViewById(R.id.listViewId);

       final ArrayList<Post> anuncios = getCoolPosts();

        //chama o adapter
        ArrayAdapter adapter = new AnuncioAdapter(this, anuncios);

        //seta o adapter
        lista.setAdapter(adapter);

        //chama cada item da lista
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainAnuncios.this, DetailAnuncio.class);

                //coloca as informações dos anuncios dentro do intent para passar a proxima activity
                intent.putExtra("titulo", anuncios.get(i).getPostTitle());
                intent.putExtra("descricao", anuncios.get(i).getPostDescription());
                intent.putExtra("preco", anuncios.get(i).getProductPrice());
                intent.putExtra("categoria", anuncios.get(i).getPostCategory());
                intent.putExtra("autor", anuncios.get(i).getPostAuthor());

                //inicia a activity de detalhes dos anuncios
                startActivity(intent);

            }
        });

    }

    ArrayList<Post> getCoolPosts(){
        try {
            AsyncTask coolTask = new GetAnunciosTask(getApplicationContext()).execute("");
            ArrayList<Post> posts = (ArrayList<Post>) coolTask.get();
            return  posts;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initLFloatingButtons() {

        final int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, getResources().getDisplayMetrics());

        final ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, size, size);
            }
        };

        ImageButton floatingButton = (ImageButton) findViewById(R.id.floatingButton);
       // Button floatingButton = (Button) findViewById(R.id.simpleButton);
        floatingButton.setOutlineProvider(viewOutlineProvider);
        floatingButton.setClipToOutline(true);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAnuncios.this, CreateAnuncio.class);
                intent.putExtra("postAuthor" ,createAnuncioIntent.getStringExtra("userId"));
                startActivity(intent);
            }
        });
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
                intent = new Intent(MainAnuncios.this, Login.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.item_anuncios:
                return true;

            case R.id.item_configuracoes:
                intent = new Intent(MainAnuncios.this, UserActivity.class);
                startActivity(intent);
                return true;

            case R.id.item_meus_anuncios:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
