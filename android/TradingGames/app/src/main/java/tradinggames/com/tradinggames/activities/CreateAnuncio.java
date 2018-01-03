package tradinggames.com.tradinggames.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import tradinggames.com.tradinggames.R;
import tradinggames.com.tradinggames.models.FileModel;
import tradinggames.com.tradinggames.models.Post;
import tradinggames.com.tradinggames.tasks.CreateAnuncioTask;

public class CreateAnuncio extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private ImageView imagem[] = new ImageView[6];

    private EditText postTitle;
    private EditText postDescription;
    private EditText productPrice;
    private EditText postCategory;
    private EditText[] postTags = new EditText[3];
    private EditText contactNumber;

    private Button adicionarAnuncio;
    private Bitmap imagemSelecionada;
    private Bitmap[] imagens = new Bitmap[6];
    private int posicao = 0;
    private Intent intent;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_anuncio);

        intent = getIntent();

        final String postAuthor = intent.getStringExtra("postAuthor");


        Log.d("createAnuncio", "PostAuthor Id" + postAuthor);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Trading Games");
        setSupportActionBar(toolbar);

        imagem[0] = findViewById(R.id.image1);
        imagem[1] = findViewById(R.id.image2);
        imagem[2] = findViewById(R.id.image3);
        imagem[3] = findViewById(R.id.image4);
        imagem[4] = findViewById(R.id.image5);
        imagem[5] = findViewById(R.id.image6);
        postTags[0] = findViewById(R.id.edit_tag1);
        postTags[1] = findViewById(R.id.edit_tag2);
        postTags[2] = findViewById(R.id.edit_tag3);
        postTitle = findViewById(R.id.edit_titulo);
        postDescription = findViewById(R.id.edit_descricao);
        productPrice = findViewById(R.id.edit_preco);
        postCategory = findViewById(R.id.edit_categorias);
        contactNumber = findViewById(R.id.edit_contato);

        final SimpleMaskFormatter numero = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(contactNumber, numero);
        contactNumber.addTextChangedListener(mtw);

        adicionarAnuncio = findViewById(R.id.button_adicionar_anuncio);

        imagem[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicao = 0;
                selecionarFotos();
                imagemSelecionada = null;
            }
        });

        imagem[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicao = 1;
                selecionarFotos();
                imagemSelecionada = null;
            }
        });

        imagem[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicao = 2;
                selecionarFotos();
                imagemSelecionada = null;
            }
        });

        imagem[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicao = 3;
                selecionarFotos();
                imagemSelecionada = null;
            }
        });

        imagem[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicao = 4;
                selecionarFotos();
                imagemSelecionada = null;
            }
        });

        imagem[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicao = 5;
                selecionarFotos();
                imagemSelecionada = null;
            }
        });

        adicionarAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post novoAnuncio = new Post();

                FileModel fileModel = new FileModel();

                // novoAnuncio.setImagens(imagens);

                fileModel.setFiles(Arrays.asList(imagens));

                novoAnuncio.setContactNumber(contactNumber.getText().toString());
                novoAnuncio.setPostAuthor(postAuthor);

                String numeroSemFormatacao = contactNumber.getText().toString();
                numeroSemFormatacao = numeroSemFormatacao.replace("(", "");
                numeroSemFormatacao = numeroSemFormatacao.replace(")", "");
                numeroSemFormatacao = numeroSemFormatacao.replace(" ", "");
                numeroSemFormatacao = numeroSemFormatacao.replace("-", "");
                Log.i("Numero:", numeroSemFormatacao);
                novoAnuncio.setContactNumber(numeroSemFormatacao);

             //  Coloca os valores do anúncio
                novoAnuncio.setPostAuthor(postAuthor);
                novoAnuncio.setPostCategory(postCategory.getText().toString());
                novoAnuncio.setPostTitle(postTitle.getText().toString());
                novoAnuncio.setPostDescription(postDescription.getText().toString());
                novoAnuncio.setProductPrice(Double.parseDouble(productPrice.getText().toString()));
                novoAnuncio.setContactNumber("");

                String tags[] = new String[3];

                for (int i = 0; i < tags.length; i++) {
                    tags[i] = postTags[i].getText().toString();
                }
                novoAnuncio.setPostTags(tags);

                new CreateAnuncioTask(getApplicationContext()).execute(novoAnuncio);
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
                intent = new Intent(CreateAnuncio.this, Login.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.item_anuncios:
                intent = new Intent(CreateAnuncio.this, MainAnuncios.class);
                startActivity(intent);
                return true;

            case R.id.item_configuracoes:
                intent = new Intent(CreateAnuncio.this, UserActivity.class);
                startActivity(intent);
                return true;

            case R.id.item_meus_anuncios:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void selecionarFotos() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("ONACTIVITYRESULT", "Foto");

        //testar processo de retorno de dados
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            //recuperar local do recurso
            Uri localImagemSelecionada = data.getData();

            //recupera a imagem do local selecionado
            try {
                imagemSelecionada = MediaStore.Images.Media.getBitmap(getContentResolver(), localImagemSelecionada);
                imagem[posicao].setImageBitmap(imagemSelecionada);
                imagens[posicao] = imagemSelecionada;

                //comprimir no formato PNG
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imagemSelecionada.compress(Bitmap.CompressFormat.PNG, 75, stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}